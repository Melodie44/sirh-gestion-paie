package dev.paie.service;

import java.util.List;

import dev.paie.entite.Cotisation;

public interface CotisationService {

	void sauvegarder(Cotisation nouvelleCotisation);
	void mettreAJour(Cotisation cotisation);
	void supprimer(String code);
	List<Cotisation> lister();
}
