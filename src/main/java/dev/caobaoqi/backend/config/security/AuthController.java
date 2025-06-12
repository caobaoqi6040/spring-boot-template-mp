package dev.caobaoqi.backend.config.security;

import dev.caobaoqi.backend.config.security.jwt.Jwt;
import dev.caobaoqi.backend.user.domain.request.UserLoginRequestVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final Jwt jwt;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/sign-in")
	public ResponseEntity<String> signIn(@Valid @RequestBody UserLoginRequestVo vo) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
			vo.email(), vo.password()
		));
		String token = jwt.create((UserDetails) authenticate.getPrincipal(), vo.email());
		return ResponseEntity.ok(token);
	}

	@GetMapping("/sign-out")
	public ResponseEntity<Void> signOut(HttpServletRequest request) {
		boolean deleted = jwt.invalidate(jwt.extract(request));
		if (deleted) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Void> handlerBadCredentialsException(BadCredentialsException ex) {
		log.info("badCredentialsException with {}", ex.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
