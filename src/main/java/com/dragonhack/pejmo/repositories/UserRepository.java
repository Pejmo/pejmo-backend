package com.dragonhack.pejmo.repositories;

import com.dragonhack.pejmo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
