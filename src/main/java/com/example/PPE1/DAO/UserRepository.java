package com.example.PPE1.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.PPE1.entities.User;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u where u.userName = ?1")
    Optional<User> findUserWithName(String userName);
   
    @Query("SELECT u FROM User u WHERE u.userName=:userNameOrEmail OR u.email=:userNameOrEmail")
    Optional <User> findByUsernameOrEmail(String userNameOrEmail);
}
