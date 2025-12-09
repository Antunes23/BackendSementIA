package com.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.login.entities.sensor;
public interface SensorRepository extends JpaRepository<sensor, Long>{

}
