CREATE TABLE application (
	id                     CHAR(36) NOT NULL,
	status                 TINYINT NOT NULL,
	monthly_payment_amount NUMERIC(20,2),
	total_repayment_amount NUMERIC(20,2),
	number_of_payments     INTEGER,
	annual_percentage_rate NUMERIC(20,5),
	first_repayment_date   DATE,

	PRIMARY KEY (id)
);

CREATE TABLE aggregate (
	id       BIGINT AUTO_INCREMENT,
	fast_id  CHAR(36),
	solid_id CHAR(36),

	PRIMARY KEY (id),
	FOREIGN KEY (fast_id) REFERENCES application(id),
	FOREIGN KEY (solid_id) REFERENCES application(id)
);