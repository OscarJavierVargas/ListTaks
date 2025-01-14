package com.agilesoft.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agilesoft.task.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	 Optional<UserEntity> findByUsername(String username);

}
