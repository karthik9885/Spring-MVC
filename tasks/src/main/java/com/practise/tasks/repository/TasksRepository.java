package com.practise.tasks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practise.tasks.entity.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer>{

	public List<Tasks> findByUserName(String userName);
}
