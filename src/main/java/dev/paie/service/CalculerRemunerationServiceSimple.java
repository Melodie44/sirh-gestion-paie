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

	@Autowired
	@Qualifier("bulletin1")
	private BulletinSalaire bulletin;
	
	private PaieUtils paieUtils;
	
	private ResultatCalculRemuneration res;
	
	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		
		BigDecimal heuresBase = bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase();
		BigDecimal tauxBase = bulletin.getRemunerationEmploye().getGrade().getTauxBase();
		BigDecimal salaireBase = heuresBase.multiply(tauxBase);
		BigDecimal salaireBrut = salaireBase.add(bulletin.getPrimeExceptionnelle());
		BigDecimal totRetSal = null;
		List<Cotisation> lstCotNonSal = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables();
		for(Cotisation cot : lstCotNonSal) {
			
			totRetSal = totRetSal.add(cot.getTauxSalarial().multiply(salaireBrut));
		}
		BigDecimal totCotPat = null;
		List<Cotisation> lstCotNonPat = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables();
		for(Cotisation cot : lstCotNonPat) {
			totCotPat = totCotPat.add(cot.getTauxPatronal().multiply(salaireBrut));
		}
		
		BigDecimal netImp = salaireBrut.subtract(totRetSal);
		
		BigDecimal somme = null;
		List<Cotisation> lstCotImpSal = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables();
		for(Cotisation cot : lstCotImpSal) {
			
			somme = somme.add(cot.getTauxSalarial().multiply(salaireBrut));
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
