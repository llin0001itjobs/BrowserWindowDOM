package org.llin.demo.browserDOM.repository;

import org.llin.demo.browserDOM.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
    User findByUsername(String username);
    User findByVerificationToken(String token);
}
