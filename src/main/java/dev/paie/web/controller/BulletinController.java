package dev.paie.web.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.entite.Utilisateur;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationServiceSimple;

@Controller
@RequestMapping("/bulletins")
public class BulletinController {
	
	@Autowired
	private RemunerationEmployeRepository remuEmployeRepository;
	
	@Autowired
	private PeriodeRepository periodeRepository;
	
	@Autowired
	private BulletinSalaireRepository bulletinRepository;
	
	@Autowired
	private CalculerRemunerationServiceSimple calculerRemService;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		
		List<RemunerationEmploye> remusEmployes = remuEmployeRepository.findAll();
		mv.addObject("remusEmployes", remusEmployes);
		
		List<Periode> periodes = periodeRepository.findAll();
		mv.addObject("periodes", periodes);
		
		BulletinSalaire bulletin = new BulletinSalaire();
		mv.addObject("bulletin", bulletin);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView post(@ModelAttribute("bulletin") BulletinSalaire bulletin){
		
		bulletin.setDateHeureCreation(LocalDateTime.now());
		
		bulletinRepository.save(bulletin);
		
		return creerBulletin();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public ModelAndView listerBulletins() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletins");
		
		List<BulletinSalaire> bulletins = bulletinRepository.findAll();
		mv.addObject("bulletins", bulletins);
		
		List<ResultatCalculRemuneration> resultats = new ArrayList<ResultatCalculRemuneration>();
		for(BulletinSalaire bulletin : bulletins) {
			resultats.add(calculerRemService.calculer(bulletin));
		}
		mv.addObject("resultats", resultats);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/visualiser/{bulletinId}")
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public ModelAndView visualiserBulletin(@PathVariable int bulletinId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/visualiserBulletin");
		
		BulletinSalaire bulletin = new BulletinSalaire();
		bulletin = bulletinRepository.findOne(bulletinId);
		
		ResultatCalculRemuneration res = new ResultatCalculRemuneration();
		res = calculerRemService.calculer(bulletin);
		
		mv.addObject("bulletin", bulletin);
		mv.addObject("resultat", res);
		
		return mv;
	}
}
