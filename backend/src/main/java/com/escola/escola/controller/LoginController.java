package com.escola.escola.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.escola.escola.config.security.MD5Cryptography;
import com.escola.escola.config.security.TokenSecuriry;
import com.escola.escola.form.LoginPostRequest;
import com.escola.escola.model.Usuario;
import com.escola.escola.repository.IUsuarioRepository;

import io.swagger.annotations.ApiOperation;
@Controller
@Transactional
public class LoginController {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	private static final String ENDPOINT = "/api/login";
	
	@ApiOperation("Serviço para autenticação de Usuário")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<String> post(@RequestBody LoginPostRequest request){
		try {
			//pesquisar no banco de dados o usuario atraves do login e senha
			Usuario usuario = usuarioRepository.findByLoginAndSenha(request.getLogin(), 
										  MD5Cryptography.encrypt(request.getSenha()));
			//verificar se o usuario foi encontrado
			if(usuario != null) {
				return ResponseEntity.status(HttpStatus.OK).body(TokenSecuriry.generateToken(usuario.getLogin()));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}