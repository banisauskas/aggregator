package app.klix.aggregator.services;

import app.klix.aggregator.data.Aggregate;
import app.klix.aggregator.data.Application;
import app.klix.aggregator.data.ApplicationStatus;
import app.klix.aggregator.data.Offer;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class AggregateRowMapper implements RowMapper<Aggregate> {

	private static final ApplicationStatus[] STATUSES = ApplicationStatus.values();

	@Override
	public Aggregate mapRow(ResultSet rs, int rowNum) throws SQLException {
		Aggregate aggregate = new Aggregate();
		aggregate.setFastApplication(parseApplication(rs, 1));
		aggregate.setSolidApplication(parseApplication(rs, 8));
		return aggregate;
	}

	private Application parseApplication(ResultSet rs, int idx) throws SQLException {
		String id = rs.getString(idx++);

		// 'id' cannot be null because marked 'NOT NULL', but
		// here it can be null when 'LEFT JOIN' did not find match
		if (id == null) {
			return null;
		}

		Application app = new Application();
		app.setId(UUID.fromString(id));
		app.setStatus(STATUSES[rs.getInt(idx++)]);

		BigDecimal monthlyPaymentAmount = rs.getBigDecimal(idx++);
		BigDecimal totalRepaymentAmount = rs.getBigDecimal(idx++);
		Integer numberOfPayments = rs.getObject(idx++, Integer.class);
		BigDecimal annualPercentageRate = rs.getBigDecimal(idx++);
		String firstRepaymentDate = rs.getString(idx++);

		if (monthlyPaymentAmount != null || totalRepaymentAmount != null || numberOfPayments != null
				|| annualPercentageRate != null || firstRepaymentDate != null) {

			Offer offer = new Offer();
			app.setOffer(offer);

			offer.setMonthlyPaymentAmount(monthlyPaymentAmount);
			offer.setTotalRepaymentAmount(totalRepaymentAmount);
			offer.setNumberOfPayments(numberOfPayments);
			offer.setAnnualPercentageRate(annualPercentageRate);

			if (firstRepaymentDate != null) {
				offer.setFirstRepaymentDate(LocalDate.parse(firstRepaymentDate));
			}
		}

		return app;
	}
}