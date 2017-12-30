package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService{
	
	@Autowired
	private PaieUtils paieUtils;
	
	@Autowired
	private ResultatCalculRemuneration res;
	
	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		
		BigDecimal heuresBase = bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase();
		BigDecimal tauxBase = bulletin.getRemunerationEmploye().getGrade().getTauxBase();
		BigDecimal salaireBase = heuresBase.multiply(tauxBase);
		BigDecimal salaireBrut = salaireBase.add(bulletin.getPrimeExceptionnelle());
		BigDecimal totRetSal = new BigDecimal("0");
		List<Cotisation> lstCotNonSal = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables();
		for(Cotisation cot : lstCotNonSal) {
			
			if(cot.getTauxSalarial() != null) {
				totRetSal = totRetSal.add(cot.getTauxSalarial().multiply(salaireBrut));
			}
		}
		BigDecimal totCotPat = new BigDecimal("0");
		List<Cotisation> lstCotNonPat = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables();
		for(Cotisation cot : lstCotNonPat) {
			
			if(cot.getTauxPatronal() != null) {
				totCotPat = totCotPat.add(cot.getTauxPatronal().multiply(salaireBrut));
			}
		}
		
		BigDecimal netImp = salaireBrut.subtract(totRetSal);
		
		BigDecimal somme = new BigDecimal("0");
		List<Cotisation> lstCotImpSal = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables();
		for(Cotisation cot : lstCotImpSal) {
			
			if(cot.getTauxSalarial() != null) {
				somme = somme.add(cot.getTauxSalarial().multiply(salaireBrut));
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
