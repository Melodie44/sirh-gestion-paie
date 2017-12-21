package dev.paie.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

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

@Configuration
@ImportResource({"classpath:entreprises.xml", "classpath:grades.xml", "classpath:profils-remuneration.xml"})
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
	private List<Entreprise> entreprises;
	@Autowired
	private List<Grade> grades;
	@Autowired
	private List<Cotisation> cotisations;
	@Autowired
	private List<ProfilRemuneration> profilsRemunerations;
	
	@Override
	public void initialiser() {
		
		entreprises.forEach(e -> entrepriseRepository.save(e));
		grades.forEach(g -> gradeRepository.save(g));
		cotisations.forEach(c -> cotisationRepository.save(c));
		profilsRemunerations.forEach(p -> profilRemunerationRepository.save(p));
		
		for(int i=1; i<13; i++) {
			
			Periode periode = new Periode();
			NumberFormat formatter = new DecimalFormat("00");
			
			
			LocalDate dateDebut = LocalDate.parse(LocalDate.now().getYear()+"-"+formatter.format(i)+"-"+"01");
			LocalDate dateMonth = LocalDate.parse(LocalDate.now().getYear()+"-"+formatter.format(i)+"-01");
			LocalDate dateFin = LocalDate.parse(LocalDate.now().getYear()+"-"+formatter.format(i)+"-"+dateMonth.lengthOfMonth());
			
			periode.setDateDebut(dateDebut);
			periode.setDateFin(dateFin);
			
			periodeRepository.save(periode);
		}
	}

}
