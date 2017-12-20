package dev.paie.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@Service
@Transactional
public class CotisationServiceJpa implements CotisationService{

	@PersistenceContext 
	private EntityManager em;

	@Override
	public void sauvegarder(Cotisation nouvelleCotisation) {
		
		
		if(lister().contains(nouvelleCotisation)) {
			System.out.println("La cotisation existe déjà");
		}else {
			em.persist(nouvelleCotisation);
			
		}

	}

	@Override
	public void mettreAJour(Cotisation cotisation) {
		
		Cotisation c = em.find(Cotisation.class, cotisation.getId());
		if (c != null) {
			c.setCode(cotisation.getCode());
			c.setLibelle(cotisation.getLibelle());
			c.setTauxPatronal(cotisation.getTauxPatronal());
			c.setTauxSalarial(cotisation.getTauxSalarial());
			em.merge(c);
		}
		
	}

	@Override
	public List<Cotisation> lister() {
		
		TypedQuery<Cotisation> query = em.createQuery("SELECT c FROM Cotisation c", Cotisation.class);
		
		return query.getResultList();
	}
	
	@Override
	public void supprimer(String code) {
		
		Cotisation cot = (Cotisation) lister().stream().filter(c -> c.getCode() == code);
		
		if(cot == null) {
			System.out.println("La cotisation à supprimer n'existe pas");
		}else {
			em.remove(cot);
			
		}
	}
}
