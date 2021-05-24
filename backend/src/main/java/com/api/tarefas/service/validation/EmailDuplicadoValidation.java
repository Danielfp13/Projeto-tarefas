package com.api.tarefas.service.validation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.tarefas.domain.Usuario;
import com.api.tarefas.repositories.UsuarioRepository;

public class EmailDuplicadoValidation implements ConstraintValidator<EmailDupicado, String> {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public void initialize(EmailDupicado constraintAnnotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {

		Optional<Usuario> usuario = repository.findByEmail(email);
		if (!usuario.isEmpty()) {
			return false;
		}
		return true;
	}

}
