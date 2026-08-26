package com.exelynt.resourcebookingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exelynt.resourcebookingsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	 Optional<User> findByEmail(String email);
}
