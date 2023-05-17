package com.expense_sharing_app.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "expenses")
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String paidBy;
	private int totalUsers;

	@ElementCollection
	@CollectionTable(name = "expense_users", joinColumns = @JoinColumn(name = "expense_id"))
	private List<String> users;

	@Enumerated(EnumType.STRING)
	private ExpenseType expenseType;

	@ElementCollection
	@CollectionTable(name = "expense_shares", joinColumns = @JoinColumn(name = "expense_id"))
	private List<Double> expenseShares;

	private double amount;

	public Expense() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public int getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public List<Double> getExpenseShares() {
		return expenseShares;
	}

	public void setExpenseShares(List<Double> expenseShares) {
		this.expenseShares = expenseShares;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Expense(Long id, String paidBy, int totalUsers, List<String> users, ExpenseType expenseType,
			List<Double> expenseShares, double amount) {
		super();
		this.id = id;
		this.paidBy = paidBy;
		this.totalUsers = totalUsers;
		this.users = users;
		this.expenseType = expenseType;
		this.expenseShares = expenseShares;
		this.amount = amount;
	}

}
