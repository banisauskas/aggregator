package app.klix.aggregator.repositories;

import app.klix.aggregator.data.Application;
import app.klix.aggregator.data.Offer;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UpdateRepository {

	private static final String UPDATE =
		"UPDATE application SET " +
			"status = ?, monthly_payment_amount = ?, " +
			"total_repayment_amount = ?, number_of_payments = ?, " +
			"annual_percentage_rate = ?, first_repayment_date = ? " +
		"WHERE id = ?";

	@Autowired
	private JdbcTemplate jdbc;

	public void updateApplication(Application application) {
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

		jdbc.update(UPDATE, status, monthlyPaymentAmount, totalRepaymentAmount,
			numberOfPayments, annualPercentageRate, firstRepaymentDate, id);
	}
}