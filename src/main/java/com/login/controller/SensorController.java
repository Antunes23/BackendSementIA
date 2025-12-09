package com.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.entities.sensor;
import com.login.service.SensorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sensor")
public class SensorController {

	@Autowired
	private final SensorService sensorService;
	
	public SensorController(SensorService sensorService) {
		this.sensorService = sensorService;
	}
	
	@PostMapping("/")
	public ResponseEntity<sensor> criarSensor(@RequestBody @Valid sensor sensor) {
		sensor novoSensor = sensorService.salvarSensor(sensor);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoSensor);
	}
	
	@GetMapping("/{idSens}")
	public ResponseEntity<sensor> buscarSensorId(@PathVariable Long idSens) {
		sensor sensor = sensorService.buscarSensorPorId(idSens);
		if (sensor != null) {
			return ResponseEntity.ok(sensor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<sensor>> buscarTodosSensor() {
		List<sensor> sensor = sensorService.buscarTodosSensor();
		return ResponseEntity.ok(sensor);
	}
	
	
	@PutMapping("/{idSens}")
	public ResponseEntity<sensor> alterarSensor(@PathVariable Long idSens, @RequestBody @Valid sensor sensor) {
		sensor atualizaSensor = sensorService.atualizarSensor(idSens, sensor);
		if (atualizaSensor != null) {
			return ResponseEntity.ok(atualizaSensor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{idSens}")
	public ResponseEntity<sensor> apagarSensor(@PathVariable Long idSens) {
		boolean apagarSensor = sensorService.apagarSensor(idSens);
		if (apagarSensor) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
