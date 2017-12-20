package dev.paie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.repository.CotisationRepository;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.ProfilRemunerationRepository;

@Service
@ImportResource({"classpath:jdd-config.xml", "classpath:entreprises.xml", "classpath:grades.xml", "classpath:profils-remuneration.xml"})
public class InitialiserDonneesServiceDev implements InitialiserDonneesService{

	@Autowired
	private CotisationRepository cotisationRepository;
	@Autowired
	private ProfilRemunerationRepository profilRemunerationRepository;
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private PeriodeRepository periodeRepository;
	
	@Autowired
	private List<ProfilRemuneration> profilsRemunerations;
	@Autowired
	private Cotisation cotisation;
	@Autowired
	private Entreprise entreprise;
	@Autowired
	private Grade grade;
	@Autowired
	private Periode periode;

	@Override
	public void initialiser() {
		
		profilRemunerationRepository.save(profilsRemunerations.iterator().next());
		cotisationRepository.save(cotisation);
		entrepriseRepository.save(entreprise);
		gradeRepository.save(grade);
		periodeRepository.save(periode);
	}

}
