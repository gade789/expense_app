package com.expense_sharing_app.model;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	private String userId;
	private String name;
	private String email;
	private String mobileNumber;

	@ElementCollection
	@MapKeyColumn(name = "user_id")
	private Map<String, Double> balances = new HashMap<>();

	public User() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Map<String, Double> getBalances() {
		return balances;
	}

	public void setBalances(Map<String, Double> balances) {
		this.balances = balances;
	}

	public User(String userId, String name, String email, String mobileNumber, Map<String, Double> balances) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.balances = balances;
	}

}
