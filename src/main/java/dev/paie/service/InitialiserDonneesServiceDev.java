package dev.paie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	private ProfilRemuneration profilRemuneration;
	
	@Autowired
	@Qualifier("ep01")
	private Cotisation cotisation;
	
	@Autowired
	private Entreprise entreprise;
	
	@Autowired
	private Grade grade;
	
	@Autowired
	private Periode periode;
	
	@Override
	public void initialiser() {
		
		profilRemunerationRepository.save(profilRemuneration);
		
		cotisationRepository.save(cotisation);
		
		entrepriseRepository.save(entreprise);
		
		gradeRepository.save(grade);
		
		periodeRepository.save(periode);
	}

}
