package com.avish.Craftly.repository;

import com.avish.Craftly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
