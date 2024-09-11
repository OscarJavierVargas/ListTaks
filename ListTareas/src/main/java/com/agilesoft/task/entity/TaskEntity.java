package com.agilesoft.task.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Data
@Table(name = "tasks")
public class TaskEntity {
	
	
	

    public TaskEntity() {
		super();
	}

	public TaskEntity(Long id, String name, String description, Boolean resolved, LocalDateTime createdAt,
			LocalDateTime updatedAt, UserEntity userEntity) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.resolved = resolved;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userEntity = userEntity;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Boolean resolved = false;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getResolved() {
		return resolved;
	}

	public void setResolved(Boolean resolved) {
		this.resolved = resolved;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

    
    
}
