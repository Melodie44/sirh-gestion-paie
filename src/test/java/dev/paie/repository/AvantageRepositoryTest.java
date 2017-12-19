package dev.paie.repository;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.JeuxDeDonneesConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class, JeuxDeDonneesConfig.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
//test
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {

	@Autowired
	private AvantageRepository avantageRepository;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
	// TODO sauvegarder un nouvel avantage
		Avantage a = new Avantage();
		a.setCode("AV01");
		a.setMontant(new BigDecimal("100"));
		a.setNom("Avantage 1");
		avantageRepository.save(a);
	// TODO vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
		avantageRepository.findOne(1);
	// TODO modifier un avantage
		Avantage a2 = new Avantage();
		a.setCode("AV01");
		a.setMontant(new BigDecimal("100"));
		a.setNom("Avantage 1");
		avantageRepository.save(a2);
	// TODO vérifier que les modifications sont bien prises en compte via la méthode findOne
		avantageRepository.findOne(1);
	}
}
