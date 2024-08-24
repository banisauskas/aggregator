package app.klix.aggregator.data;

import java.math.BigDecimal;

public class FastRequest {
	/** Accepted RegExp '\+371[0-9]{8}' */
	private String phoneNumber;
	private String email;
	private BigDecimal monthlyIncomeAmount;
	private BigDecimal monthlyCreditLiabilities;
	private int dependents;
	private boolean agreeToDataSharing;
	private BigDecimal amount;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getMonthlyIncomeAmount() {
		return monthlyIncomeAmount;
	}

	public void setMonthlyIncomeAmount(BigDecimal monthlyIncomeAmount) {
		this.monthlyIncomeAmount = monthlyIncomeAmount;
	}

	public BigDecimal getMonthlyCreditLiabilities() {
		return monthlyCreditLiabilities;
	}

	public void setMonthlyCreditLiabilities(BigDecimal monthlyCreditLiabilities) {
		this.monthlyCreditLiabilities = monthlyCreditLiabilities;
	}

	public int getDependents() {
		return dependents;
	}

	public void setDependents(int dependents) {
		this.dependents = dependents;
	}

	public boolean isAgreeToDataSharing() {
		return agreeToDataSharing;
	}

	public void setAgreeToDataSharing(boolean agreeToDataSharing) {
		this.agreeToDataSharing = agreeToDataSharing;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}