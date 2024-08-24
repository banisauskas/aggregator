package app.klix.aggregator.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Offer {
	private BigDecimal monthlyPaymentAmount;
	private BigDecimal totalRepaymentAmount;
	private Integer numberOfPayments;
	private BigDecimal annualPercentageRate;
	private LocalDate firstRepaymentDate;

	public BigDecimal getMonthlyPaymentAmount() {
		return monthlyPaymentAmount;
	}

	public void setMonthlyPaymentAmount(BigDecimal monthlyPaymentAmount) {
		this.monthlyPaymentAmount = monthlyPaymentAmount;
	}

	public BigDecimal getTotalRepaymentAmount() {
		return totalRepaymentAmount;
	}

	public void setTotalRepaymentAmount(BigDecimal totalRepaymentAmount) {
		this.totalRepaymentAmount = totalRepaymentAmount;
	}

	public Integer getNumberOfPayments() {
		return numberOfPayments;
	}

	public void setNumberOfPayments(Integer numberOfPayments) {
		this.numberOfPayments = numberOfPayments;
	}

	public BigDecimal getAnnualPercentageRate() {
		return annualPercentageRate;
	}

	public void setAnnualPercentageRate(BigDecimal annualPercentageRate) {
		this.annualPercentageRate = annualPercentageRate;
	}

	public LocalDate getFirstRepaymentDate() {
		return firstRepaymentDate;
	}

	public void setFirstRepaymentDate(LocalDate firstRepaymentDate) {
		this.firstRepaymentDate = firstRepaymentDate;
	}
}