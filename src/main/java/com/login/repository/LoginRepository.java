package com.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.login.entities.login;

public interface LoginRepository extends JpaRepository<login, Long>{
	
	login findByName(String name);
}
