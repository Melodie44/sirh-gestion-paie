package dev.paie.service;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.JeuxDeDonneesConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { JeuxDeDonneesConfig.class, ServicesConfig.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
//test
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(GradeServiceJdbcTemplateTest.class);
	
	@Autowired
	@Qualifier("ep01")
	private Cotisation cotisation;

	@Autowired
	private CotisationService cotisationService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		
	// TODO sauvegarder une nouvelle cotisation
		//cotisationService.sauvegarder(cotisation);
		
	// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation via la méthode lister
		cotisationService.lister().forEach(c -> LOG.info(c.toString()));
	
	// TODO modifier une cotisation
		Cotisation cot = new Cotisation();
		cot.setId(1);
		cot.setCode("EP02");
		cot.setLibelle(cotisation.getLibelle());
		cot.setTauxPatronal(new BigDecimal("0.19"));
		cot.setTauxSalarial(new BigDecimal("0.03"));
		cotisationService.mettreAJour(cot);
		LOG.info("cotisation " + cot.getId() + " mis à jour");
		
	// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		cotisationService.lister().forEach(c -> LOG.info(c.toString()));
	
	}
}
