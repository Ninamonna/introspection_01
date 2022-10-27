package org.example.introspection.repo;

import org.example.introspection.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> { //получение полного списка пользователей

    User findByUsername(String username);

    User findByActivationCode(String code);
}
