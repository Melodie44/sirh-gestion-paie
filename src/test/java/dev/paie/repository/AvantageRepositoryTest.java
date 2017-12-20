package dev.paie.repository;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.JeuxDeDonneesConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;
import dev.paie.service.GradeServiceJdbcTemplateTest;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class, JeuxDeDonneesConfig.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
//test
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {

	private static final Logger LOG = LoggerFactory.getLogger(AvantageRepositoryTest.class);
	
	@Autowired
	private AvantageRepository avantageRepository;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
	// sauvegarder un nouvel avantage
		Avantage a = new Avantage();
		a.setCode("AV01");
		a.setMontant(new BigDecimal("100"));
		a.setNom("Avantage 1");
		avantageRepository.save(a);
		LOG.info("Avantage " + a.getId() + " créé");
		
	// vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
		LOG.info(avantageRepository.findOne(1).toString());
		
	// modifier un avantage
		Avantage a2 = new Avantage();
		a2.setId(1);
		a2.setCode("AV01");
		a2.setMontant(new BigDecimal("200"));
		a2.setNom("Avantage 1");
		avantageRepository.save(a2);
		LOG.info(a2.getId() + " modifié");
		
	// vérifier que les modifications sont bien prises en compte via la méthode findOne
		LOG.info(avantageRepository.findOne(1).toString());
		
	// trouver un avantage par son code
		LOG.info(avantageRepository.findByCode("AV01").toString());
	}
}
