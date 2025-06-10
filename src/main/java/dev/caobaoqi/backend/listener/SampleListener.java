package dev.caobaoqi.backend.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("================================ SampleListener [contextInitialized] start ===========================");
		log.info("ServletContextEvent: {}", sce);
		ServletContextListener.super.contextInitialized(sce);
		log.info("================================ SampleListener [contextInitialized] end  ===========================");
	}
}
