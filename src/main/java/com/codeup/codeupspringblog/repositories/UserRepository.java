package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from User u where u.username = ?1")
    User findByUsername(String username);


}
