package com.smp.coremodule.repo;

import com.smp.coremodule.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
