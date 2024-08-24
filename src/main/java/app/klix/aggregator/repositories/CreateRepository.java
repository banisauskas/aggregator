package app.klix.aggregator.repositories;

import app.klix.aggregator.data.Aggregate;
import app.klix.aggregator.data.Application;
import app.klix.aggregator.data.Offer;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CreateRepository {

	private static final String INSERT_AGGREGATE =
		"INSERT INTO aggregate (fast_id, solid_id) " +
		"VALUES (?, ?)";

	private static final String INSERT_APPLICATION =
		"INSERT INTO application (" +
			"id, status, monthly_payment_amount, total_repayment_amount, " +
			"number_of_payments, annual_percentage_rate, first_repayment_date) " +
		"VALUES (?, ?, ?, ?, ?, ?, ?)";

	@Autowired
	private JdbcTemplate jdbc;

	public long saveAggregate(Aggregate aggregate) {
		// FastBank
		Application fastApp = aggregate.getFastApplication();
		String fastId = fastApp != null ? fastApp.getId().toString() : null;

		if (fastApp != null) {
			saveApplication(fastApp);
		}

		// SolidBank
		Application solidApp = aggregate.getSolidApplication();
		String solidId = solidApp != null ? solidApp.getId().toString() : null;

		if (solidApp != null) {
			saveApplication(solidApp);
		}

		// Aggregate
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(conn -> {
			PreparedStatement stmt = conn.prepareStatement(INSERT_AGGREGATE, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, fastId);
			stmt.setString(2, solidId);
			return stmt;
		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

	private void saveApplication(Application application) {
		String id = application.getId().toString();
		int status = application.getStatus().ordinal();

		BigDecimal monthlyPaymentAmount = null;
		BigDecimal totalRepaymentAmount = null;
		Integer numberOfPayments = null;
		BigDecimal annualPercentageRate = null;
		String firstRepaymentDate = null;
		Offer offer = application.getOffer();

		if (offer != null) {
			monthlyPaymentAmount = offer.getMonthlyPaymentAmount();
			totalRepaymentAmount = offer.getTotalRepaymentAmount();
			numberOfPayments = offer.getNumberOfPayments();
			annualPercentageRate = offer.getAnnualPercentageRate();

			if (offer.getFirstRepaymentDate() != null) {
				firstRepaymentDate = offer.getFirstRepaymentDate().toString();
			}
		}

		jdbc.update(INSERT_APPLICATION, id, status, monthlyPaymentAmount,
			totalRepaymentAmount, numberOfPayments, annualPercentageRate, firstRepaymentDate);
	}
}