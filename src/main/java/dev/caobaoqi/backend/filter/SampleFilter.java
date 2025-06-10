package dev.caobaoqi.backend.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serial;

@Slf4j
public class SampleFilter extends HttpFilter {

	@Serial
	private static final long serialVersionUID = 3736927164544898267L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("================================ SampleFilter [doFilter] start ===========================");
		log.info("request:{}", request.getRequestId());
		super.doFilter(request, response, chain);
		log.info("================================ SampleFilter [doFilter] end  ===========================");
	}

}
