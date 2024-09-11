package com.agilesoft.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agilesoft.task.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long>{
	


    List<TaskEntity> findByUserEntity_UserId(Long userId);
	
//		    List<TaskEntity> findByUserEntity_User_id(Long user_id);
    	//	List<TaskEntity> findByUserEntity_User_id(Long user_id);
		

	

}
