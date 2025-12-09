package com.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.login.entities.login;
import com.login.service.LoginService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*") // ⬅️ Adicione esta anotação aqui
@RequestMapping("/users")
public class LoginController {

    @Autowired
    private LoginService loginService;

   
    @PostMapping("/auth")
    public ResponseEntity<login> authenticate(@RequestBody login loginDetails) {
        login authenticatedUser = loginService.authenticate(loginDetails.getName(), loginDetails.getPassword());

        if (authenticatedUser != null) {
            
            authenticatedUser.setPassword(null);
            return ResponseEntity.ok(authenticatedUser);
        }
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    
    @PostMapping("/register")
    public login registerUser(@RequestBody login login) {
        return loginService.saveUser(login);
    }
    
	@GetMapping("/{idUser}")
	public ResponseEntity<login> buscarLoginId(@PathVariable Long idUser) {
		login login = loginService.buscarLoginPorId(idUser);
		if (login != null) {
			return ResponseEntity.ok(login);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<login>> buscarTodosLogin() {
		List<login> login = loginService.buscarTodosLogin();
		return ResponseEntity.ok(login);
	}
	
	@PutMapping("/{idUser}")
	public ResponseEntity<login> alterarLogin(@PathVariable Long idUser, @RequestBody @Valid login login) {
		login atualizarLogin = loginService.atualizarLogin(idUser, login);
		if(atualizarLogin != null) {
			return ResponseEntity.ok(atualizarLogin);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{idUser}")
	public ResponseEntity<login> apagarLogin(@PathVariable Long idUser) {
		boolean apagarLogin = loginService.apagarLogin(idUser);
		if(apagarLogin) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();	}
	}
   
}
