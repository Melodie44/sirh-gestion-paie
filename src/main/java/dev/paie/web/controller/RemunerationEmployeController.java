package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired 
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private ProfilRemunerationRepository profilRemuRepository;
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private RemunerationEmployeRepository remuEmployeRepository;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		
		List<Entreprise> entreprises = entrepriseRepository.findAll();
		mv.addObject("entreprises", entreprises);
		
		List<ProfilRemuneration> profilsRemu = profilRemuRepository.findAll();
		mv.addObject("profilsRemu", profilsRemu);
		
		List<Grade> grades = gradeRepository.findAll();
		mv.addObject("grades", grades);
		
		RemunerationEmploye remuEmploye = new RemunerationEmploye();
		mv.addObject("employe", remuEmploye);
		
		mv.addObject("prefixMatricule", "M00");
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public String post(@ModelAttribute RemunerationEmploye employe) {
		
		
		
		remuEmployeRepository.save(employe);
		
		return "success";
	}
}
