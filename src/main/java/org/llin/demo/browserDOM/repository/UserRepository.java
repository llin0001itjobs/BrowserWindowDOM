package org.llin.demo.browserDOM.repository;

import java.util.Optional;

import org.llin.demo.browserDOM.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
	Optional<User> findByVerificationToken(String token);
}
