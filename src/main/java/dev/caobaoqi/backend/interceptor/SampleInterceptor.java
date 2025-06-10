package dev.caobaoqi.backend.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class SampleInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("================================ SampleInterceptor [preHandle] start ===========================");
		log.info("request: {}", request.getRequestURI());
		log.info("================================ SampleInterceptor [preHandle]  end  ===========================");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.info("================================ SampleInterceptor [postHandle] start ===========================");
		log.info("response status: {}",response.getStatus());
		log.info("================================ SampleInterceptor [postHandle]  end  ===========================");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		log.info("================================ SampleInterceptor [afterCompletion] start ===========================");
		log.info("request id: {}", request.getRequestId());
		log.info("================================ SampleInterceptor [afterCompletion]  end  ===========================");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
