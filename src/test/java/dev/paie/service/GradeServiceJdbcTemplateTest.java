package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.JeuxDeDonneesConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;
import dev.paie.spring.DataSourceMySQLConfig;


//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class, JeuxDeDonneesConfig.class, DataSourceMySQLConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(GradeServiceJdbcTemplateTest.class);

	@Autowired
	private Grade grade;
	
	@Autowired
	private GradeService gradeService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
	// TODO sauvegarder un nouveau grade
		//gradeService.sauvegarder(grade);
		
	// TODO vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		gradeService.lister().forEach(g -> LOG.info(g.toString()));
		
	// TODO modifier un grade
		Grade gradeUp = new Grade();
		gradeUp.setId(1);
		gradeUp.setCode("DEVJU");
		gradeUp.setNbHeuresBase(grade.getNbHeuresBase());
		gradeUp.setTauxBase(grade.getTauxBase());
		gradeService.mettreAJour(gradeUp);
		LOG.info("grade " + gradeUp.getId() + " mis à jour");
		
	// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		gradeService.lister().forEach(gr -> LOG.info(gr.toString()));
	}
}
