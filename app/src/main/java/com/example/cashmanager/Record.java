package com.example.cashmanager;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/05/22
 */
public class Record {

	private String name;
	private String date;
	private String balanceChange;
	private String balanceResult;
	private String description;

	public Record(String name, String date, String balanceChange, String balanceResult, String description) {
		this.name = name;
		this.date = date;
		this.balanceChange = balanceChange;
		this.balanceResult = balanceResult;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBalanceChange() {
		return balanceChange;
	}

	public void setBalanceChange(String balanceChange) {
		this.balanceChange = balanceChange;
	}

	public String getBalanceResult() {
		return balanceResult;
	}

	public void setBalanceResult(String balanceResult) {
		this.balanceResult = balanceResult;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
