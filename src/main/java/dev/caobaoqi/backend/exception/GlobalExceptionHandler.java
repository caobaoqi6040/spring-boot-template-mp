package dev.caobaoqi.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleGeneralException(Exception e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemDetail detail = this.detailBuild(e, status, request.getRequestURL());
		return ResponseEntity.status(status).body(detail);
	}

	private ProblemDetail detailBuild(Exception ex, HttpStatus status, StringBuffer url) {
		ProblemDetail detail = ProblemDetail.forStatus(status);
		detail.setType(URI.create(url.toString()));
		detail.setTitle(ex.getClass().getSimpleName());
		detail.setDetail(ex.getLocalizedMessage());
		detail.setProperty("timestamp", LocalDateTime.now());
		return detail;
	}
}
