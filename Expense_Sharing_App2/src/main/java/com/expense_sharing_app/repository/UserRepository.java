package com.expense_sharing_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense_sharing_app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
