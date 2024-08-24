package app.klix.aggregator.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class Request {

	@NotNull
	@Pattern(regexp = "\\+[0-9]{11,15}")
	private String phone;

	@NotBlank
	@Email
	private String email;

	@NotNull
	@PositiveOrZero
	private BigDecimal monthlyIncome;

	@NotNull
	@PositiveOrZero
	private BigDecimal monthlyExpenses;

	@NotNull
	@PositiveOrZero
	private BigDecimal monthlyCreditLiabilities;

	@NotNull
	private MaritalStatus maritalStatus;

	@NotNull
	@PositiveOrZero
	private Integer dependents;

	@NotNull
	private Boolean agreeToDataSharing;

	@NotNull
	private Boolean agreeToBeScored;

	@NotNull
	@Positive
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

	public BigDecimal getMonthlyCreditLiabilities() {
		return monthlyCreditLiabilities;
	}

	public void setMonthlyCreditLiabilities(BigDecimal monthlyCreditLiabilities) {
		this.monthlyCreditLiabilities = monthlyCreditLiabilities;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getDependents() {
		return dependents;
	}

	public void setDependents(Integer dependents) {
		this.dependents = dependents;
	}

	public Boolean getAgreeToDataSharing() {
		return agreeToDataSharing;
	}

	public void setAgreeToDataSharing(Boolean agreeToDataSharing) {
		this.agreeToDataSharing = agreeToDataSharing;
	}

	public Boolean getAgreeToBeScored() {
		return agreeToBeScored;
	}

	public void setAgreeToBeScored(Boolean agreeToBeScored) {
		this.agreeToBeScored = agreeToBeScored;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}