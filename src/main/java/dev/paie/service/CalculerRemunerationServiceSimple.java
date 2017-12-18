package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService{
	
	private PaieUtils paieUtils;
	
	private ResultatCalculRemuneration res;
	
	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		
		BigDecimal heuresBase = new BigDecimal(bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase().toString());
		BigDecimal tauxBase = new BigDecimal(bulletin.getRemunerationEmploye().getGrade().getTauxBase().toString());
		BigDecimal salaireBase = new BigDecimal(heuresBase.toString()).multiply(new BigDecimal(tauxBase.toString()));
		BigDecimal salaireBrut = salaireBase.add(new BigDecimal(bulletin.getPrimeExceptionnelle().toString()));
		BigDecimal totRetSal = new BigDecimal("0");
		List<Cotisation> lstCotNonSal = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables();
		for(Cotisation cot : lstCotNonSal) {
			
			if(cot.getTauxSalarial() != null) {
				totRetSal = totRetSal.add(new BigDecimal(cot.getTauxSalarial().toString()).multiply(new BigDecimal(salaireBrut.toString())));
			}
		}
		BigDecimal totCotPat = new BigDecimal("0");
		List<Cotisation> lstCotNonPat = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables();
		for(Cotisation cot : lstCotNonPat) {
			
			if(cot.getTauxPatronal() != null) {
				totCotPat = totCotPat.add(new BigDecimal(cot.getTauxPatronal().toString()).multiply(new BigDecimal(salaireBrut.toString())));
			}
		}
		
		BigDecimal netImp = salaireBrut.subtract(new BigDecimal(totRetSal.toString()));
		
		BigDecimal somme = new BigDecimal("0");
		List<Cotisation> lstCotImpSal = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables();
		for(Cotisation cot : lstCotImpSal) {
			
			if(cot.getTauxSalarial() != null) {
				somme = somme.add(new BigDecimal(cot.getTauxSalarial().toString()).multiply(new BigDecimal(salaireBrut.toString())));
			}
		}
		BigDecimal netAPayer = netImp.subtract(somme);
		
		res.setSalaireDeBase(paieUtils.formaterBigDecimal(salaireBase));
		res.setSalaireBrut(paieUtils.formaterBigDecimal(salaireBrut));
		res.setTotalRetenueSalarial(paieUtils.formaterBigDecimal(totRetSal));
		res.setTotalCotisationsPatronales(paieUtils.formaterBigDecimal(totCotPat));
		res.setNetImposable(paieUtils.formaterBigDecimal(netImp));
		res.setNetAPayer(paieUtils.formaterBigDecimal(netAPayer));
		
		return res;

	}

}
