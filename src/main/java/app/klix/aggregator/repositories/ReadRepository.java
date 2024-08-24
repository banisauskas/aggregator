package app.klix.aggregator.repositories;

import app.klix.aggregator.data.Aggregate;
import app.klix.aggregator.services.AggregateRowMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReadRepository {

	private static final String SELECT =
		"SELECT " +
			"f.id AS fast_id, f.status AS fast_status, " +
			"f.monthly_payment_amount AS fast_monthly_payment_amount, " +
			"f.total_repayment_amount AS fast_total_repayment_amount, " +
			"f.number_of_payments AS fast_number_of_payments, " +
			"f.annual_percentage_rate AS fast_annual_percentage_rate, " +
			"f.first_repayment_date AS fast_first_repayment_date, " +
			"s.id AS solid_id, s.status AS solid_status, " +
			"s.monthly_payment_amount AS solid_monthly_payment_amount, " +
			"s.total_repayment_amount AS solid_total_repayment_amount, " +
			"s.number_of_payments AS solid_number_of_payments, " +
			"s.annual_percentage_rate AS solid_annual_percentage_rate, " +
			"s.first_repayment_date AS solid_first_repayment_date " +
		"FROM aggregate a " +
			"LEFT JOIN application f ON a.fast_id = f.id " +
			"LEFT JOIN application s ON a.solid_id = s.id " +
		"WHERE " +
			"a.id = ?";

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private AggregateRowMapper rowMapper;

	public Aggregate findAggregate(long id) {
		// will not find more than 1, because id is unique
		List<Aggregate> aggregates = jdbc.query(SELECT, rowMapper, id);

		if (aggregates.isEmpty()) {
			return null;
		}

		aggregates.get(0).setId(id);
		return aggregates.get(0);
	}
}