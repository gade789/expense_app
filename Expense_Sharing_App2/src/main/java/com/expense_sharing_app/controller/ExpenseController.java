package com.expense_sharing_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expense_sharing_app.model.Expense;
import com.expense_sharing_app.model.User;
import com.expense_sharing_app.service.ExpenseService;

@RestController
public class ExpenseController {
	@Autowired
	private ExpenseService expenseService;

	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		expenseService.addUser(user);

		return ResponseEntity.ok("user added successfully");
	}

	@PostMapping("/addExpense")
	public ResponseEntity<String> addExpense(@RequestBody Expense expense) {
		expenseService.addExpense(expense);

		return ResponseEntity.ok("expense added successfully");
	}

	@GetMapping("/showBalances/{userId}")
	public ResponseEntity<List<String>> showBalances(@PathVariable String userId) {
		return ResponseEntity.ok(expenseService.showBalances(userId));
	}
}
