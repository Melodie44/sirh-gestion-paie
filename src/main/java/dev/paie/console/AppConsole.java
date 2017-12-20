package dev.paie.console;

import java.math.BigDecimal;
import java.util.Scanner;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;
import dev.paie.service.CotisationService;
import dev.paie.util.PaieUtils;

public class AppConsole {
	
	static AnnotationConfigApplicationContext context;
	
	static Scanner scanner = new Scanner(System.in);
	
	private static final Logger LOG = LoggerFactory.getLogger(AppConsole.class);
	
	@Autowired
	static CotisationService cotisationService;
	
	@Autowired
	static PaieUtils paieUtils;

	public static void main(String[] args) {
		
		context = new AnnotationConfigApplicationContext(ServicesConfig.class);
		
		paieUtils = context.getBean(PaieUtils.class);
		
		LOG.info("** Gestion des cotisations **");
		LOG.info("1. Lister des cotisations");
		LOG.info("2. Créer une cotisation");
		LOG.info("3. Supprimer une cotisation");
		
		String action = scanner.nextLine();
		switch(action) {
			
			case "1" : {
				
				cotisationService.lister().forEach(c -> LOG.info(c.toString()));
				
				break;
			}
			
			case "2" : {
				
				Cotisation cot = new Cotisation();
				
				LOG.info("Entrer le code");
				String code = scanner.nextLine();
				cot.setCode(code);
				
				LOG.info("Entrer le libellé");
				String libelle = scanner.nextLine();
				cot.setLibelle(libelle);
				
				LOG.info("Entrer le taux salarial");
				BigDecimal tauxSalarial = new BigDecimal(scanner.nextLine());
				cot.setTauxSalarial(new BigDecimal(paieUtils.formaterBigDecimal(tauxSalarial)));
				
				LOG.info("Entrer le taux patronal");
				String tauxPatronal = scanner.nextLine();
				cot.setTauxPatronal(new BigDecimal(paieUtils.formaterBigDecimal(new BigDecimal(tauxPatronal))));
				
				cotisationService.sauvegarder(cot);
				
				break;
			}
			
			case "3" :{
				
				Cotisation cot = new Cotisation();
				
				LOG.info("Entrer le code");
				String code = scanner.nextLine();
				cot.setCode(code);
				
				cotisationService.supprimer(code);
			}
		}
	}

}
