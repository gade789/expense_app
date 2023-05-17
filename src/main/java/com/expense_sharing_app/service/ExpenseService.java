package com.expense_sharing_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense_sharing_app.model.Expense;
import com.expense_sharing_app.model.ExpenseType;
import com.expense_sharing_app.model.User;
import com.expense_sharing_app.repository.ExpenseRepository;
import com.expense_sharing_app.repository.UserRepository;

@Service
public class ExpenseService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ExpenseRepository expenseRepository;

	public void addUser(User user) {
		userRepository.save(user);
	}

	public void addExpense(Expense expense) {
		expenseRepository.save(expense);
		calculateExpenseShares(expense);
	}

	private void calculateExpenseShares(Expense expense) {
		if (expense.getExpenseType() == ExpenseType.EXACT) {
			double totalAmount = expense.getExpenseShares().stream().mapToDouble(Double::doubleValue).sum();
			List<String> users = expense.getUsers();
			List<Double> expenseShares = expense.getExpenseShares();

			for (int i = 0; i < users.size(); i++) {
				String user = users.get(i);
				double amount = expenseShares.get(i);
				updateBalance(expense.getPaidBy(), user, amount);
				updateBalance(user, expense.getPaidBy(), -amount);
			}
		} else if (expense.getExpenseType() == ExpenseType.EQUAL) {
			double totalAmount = expense.getAmount();
			double amountPerUser = totalAmount / expense.getTotalUsers();

			for (String user : expense.getUsers()) {
				updateBalance(expense.getPaidBy(), user, amountPerUser);
				updateBalance(user, expense.getPaidBy(), -amountPerUser);
			}
		}
	}

	private void updateBalance(String user1, String user2, double amount) {
		User user = userRepository.getById(user1);
		user.getBalances().put(user2, user.getBalances().getOrDefault(user2, 0.0) + amount);
		userRepository.save(user);
	}

	public List<String> showBalances(String userId) {
		List<String> result = new ArrayList<>();
		User user = userRepository.getById(userId);

		for (Map.Entry<String, Double> entry : user.getBalances().entrySet()) {
			String otherUser = entry.getKey();
			double balance = entry.getValue();

			if (balance > 0) {
				result.add(userId + " owes " + otherUser + ": " + String.format("%.2f", balance));
			} else if (balance < 0) {
				result.add(otherUser + " owes " + userId + ": " + String.format("%.2f", -balance));
			}
		}

		return result;
	}
}
