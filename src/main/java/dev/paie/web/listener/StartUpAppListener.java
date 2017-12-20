package dev.paie.web.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import dev.paie.service.InitialiserDonneesService;

public class StartUpAppListener {

	@Autowired
	private InitialiserDonneesService initService;

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {
		initService.initialiser();
	}
}
