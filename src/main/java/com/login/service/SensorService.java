package com.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.entities.sensor;
import com.login.repository.SensorRepository;

@Service
public class SensorService {
	@Autowired
	private final SensorRepository sensorRepository;
	
	public SensorService(SensorRepository sensorRepository) {
		this.sensorRepository = sensorRepository;
	}
	
	public List<sensor> buscarTodosSensor() {
		return sensorRepository.findAll();
	}
	
	public sensor buscarSensorPorId(Long idSens) {
		Optional<sensor> sensor = sensorRepository.findById(idSens);
		return sensor.orElse(null);
	}
	
	public sensor salvarSensor(sensor Sensor) {
		return sensorRepository.save(Sensor);
	}
	
	public sensor atualizarSensor(Long idSens, sensor atSensor) {
		Optional<sensor> exeSensor = sensorRepository.findById(idSens);
		if (exeSensor.isPresent()) {
			atSensor.setIdSens(idSens);
			return sensorRepository.save(atSensor);
		} else {
			return null;
		}
	}
	
	public Boolean apagarSensor(Long idSens) {
		Optional<sensor> exeSensor = sensorRepository.findById(idSens);
		if (exeSensor.isPresent()) {
			sensorRepository.deleteById(idSens);
			return true;
		} else {
			return false;
		}
	}
	
}
