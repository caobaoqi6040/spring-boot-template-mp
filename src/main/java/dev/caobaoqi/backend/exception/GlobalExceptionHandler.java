package dev.caobaoqi.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ProblemDetail> handleBusinessException(BusinessException ex, HttpServletRequest request) {
		log.error("-------------------- handleBusinessException -------------------------", ex);
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemDetail detail = this.detailBuild(ex, status, request.getRequestURL());
		return ResponseEntity.status(status).body(detail);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		log.error("-------------------- handleMethodArgumentNotValidException -------------------------", ex);
		HashMap<String, String> map = ex.getFieldErrors().stream()
			.collect(HashMap::new, (m, error) -> m.put(error.getField(), error.getDefaultMessage()), HashMap::putAll);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleException(Exception ex, HttpServletRequest request) {
		log.error("-------------------- handleException -------------------------", ex);
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemDetail detail = this.detailBuild(ex, status, request.getRequestURL());
		return ResponseEntity.status(status).body(detail);
	}

	private ProblemDetail detailBuild(Exception ex, HttpStatus status, StringBuffer url) {
		ProblemDetail detail = ProblemDetail.forStatus(status);
		detail.setType(URI.create(url.toString()));
		detail.setTitle(ex.getClass().getSimpleName());
		detail.setDetail(status.getReasonPhrase());
		detail.setProperty("timestamp", LocalDateTime.now());
		return detail;
	}
}
