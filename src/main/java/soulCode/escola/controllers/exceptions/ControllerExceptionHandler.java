package soulCode.escola.controllers.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import soulCode.escola.services.exceptions.DataIntegrityViolationCustomException;
import soulCode.escola.services.exceptions.ObjectNotFoundCustomException;

@ControllerAdvice
public class ControllerExceptionHandler {

	//método vai retornar uma ResponseEntity nos padrões estabelecidos pela classe StandardError (que tem timestamp, status e msgErro)
	@ExceptionHandler(ObjectNotFoundCustomException.class)
	public ResponseEntity<StandardError> objectNotFoundException(
			ObjectNotFoundCustomException e,
			ServletRequest request) {
		
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String agoraFormatado = agora.format(formatador);
		
		StandardError error = new StandardError(
				agoraFormatado, 
				HttpStatus.NOT_FOUND.value(),
				e.getMessage()); 
				
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationCustomException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(
			DataIntegrityViolationCustomException e,
			ServletRequest request) {
		
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String agoraFormatado = agora.format(formatador);
		
		StandardError error = new StandardError(
				agoraFormatado, 
				HttpStatus.BAD_REQUEST.value(),
				e.getMessage()); 
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
