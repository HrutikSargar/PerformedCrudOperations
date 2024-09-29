package com.jsp.PerformedCrudOperations.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.PerformedCrudOperations.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
