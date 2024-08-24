package app.klix.aggregator.data;

import java.math.BigDecimal;

public class SolidRequest {
	/** Accepted RegExp '\+[0-9]{11,15}' */
	private String phone;
	private String email;
	private BigDecimal monthlyIncome;
	private BigDecimal monthlyExpenses;
	private MaritalStatus maritalStatus;
	private boolean agreeToBeScored;
	private BigDecimal amount;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(BigDecimal monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public BigDecimal getMonthlyExpenses() {
		return monthlyExpenses;
	}

	public void setMonthlyExpenses(BigDecimal monthlyExpenses) {
		this.monthlyExpenses = monthlyExpenses;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public boolean isAgreeToBeScored() {
		return agreeToBeScored;
	}

	public void setAgreeToBeScored(boolean agreeToBeScored) {
		this.agreeToBeScored = agreeToBeScored;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}