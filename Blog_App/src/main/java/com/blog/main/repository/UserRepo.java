package com.blog.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.main.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
