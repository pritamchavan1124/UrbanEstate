package com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exc)
//	{
//		System.out.println("In methodargument exception "+exc);
//		List<FieldError> list = exc.getFieldErrors();
//		Map<String, String> map = list.stream().collect(collec)
//		
//		
//	}
	
	@ExceptionHandler(resourceNotFoundException.class)
	public ResponseEntity<String> handlePropertyPolicyException(resourceNotFoundException p){
		return new ResponseEntity<String>(p.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	// catch-all : RuntimeExc
//		@ExceptionHandler(RuntimeException.class)
//		public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
//			System.out.println("in catch-all run time exc" + e);
//			return ResponseEntity.
//					status(HttpStatus.INTERNAL_SERVER_ERROR).
//					body(e.printStackTrace());
//		}
//		
//		// catch-all : Exception
//		@ExceptionHandler(Exception.class)
//		public ResponseEntity<?> handleException(Exception e) {
//			System.out.println("in catch-all exc " + e);
//			return ResponseEntity.
//					status(HttpStatus.INTERNAL_SERVER_ERROR).
//					body(e.getMessage());	
//			}
}
