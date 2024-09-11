package com.agilesoft.task.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
//@Data
@Table(name = "user")
public class UserEntity {

	
	 public UserEntity() {
	    }
	

	public UserEntity(Long userId, String username, String password, String name) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
    private Long userId;

    @Column(unique = true)
    private String username;

    private String password;

    private String name;

    @OneToMany(mappedBy = "userEntity")
    private List<TaskEntity> tasks;

	

	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
    
}

