package com.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.entities.login;
import com.login.repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;


    public login authenticate(String username, String password) {
        login user = loginRepository.findByName(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public login saveUser(login login) {
        return loginRepository.save(login);
    }
    
    public LoginService(LoginRepository loginRepository) {
    	this.loginRepository = loginRepository;
    }
    
    public List<login> buscarTodosLogin() {
    	return loginRepository.findAll();
    }
    
    public login buscarLoginPorId(Long idUser) {
    	Optional<login> login = loginRepository.findById(idUser);
    	return login.orElse(null);
    }
    
    public login atualizarLogin(Long idUser, login atLogin) {
    	Optional<login> exeLogin = loginRepository.findById(idUser);
    	if (exeLogin.isPresent()) {
    		atLogin.setIdUser(idUser);
    		return loginRepository.save(atLogin);
    	} else {
    		return null;
    	}
    }
    
    public Boolean apagarLogin(Long idUser) {
        Optional<login> exeLogin = loginRepository.findById(idUser);

        if (exeLogin.isPresent()) {
            loginRepository.deleteById(idUser);  // 🔥 aqui realmente apaga
            return true;
        } else {
            return false;
        }
    }

}
