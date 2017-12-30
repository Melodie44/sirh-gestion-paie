package dev.paie.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PROFIL_REMUNERATION")
public class ProfilRemuneration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="CODE")
	private String code;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="PROFILREM_COTNIMP",
			joinColumns=@JoinColumn(name="ID_PROFILREM", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="ID_COTISATION", referencedColumnName="ID"))
	private List<Cotisation> cotisationsNonImposables;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="PROFILREM_COTIMP",
			joinColumns=@JoinColumn(name="ID_PROFILREM", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="ID_COTISATION", referencedColumnName="ID"))
	private List<Cotisation> cotisationsImposables;
	
	@OneToMany(mappedBy="profilRemuneration")
	private List<Avantage> avantages;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Cotisation> getCotisationsNonImposables() {
		return cotisationsNonImposables;
	}

	public void setCotisationsNonImposables(List<Cotisation> cotisationsNonImposables) {
		this.cotisationsNonImposables = cotisationsNonImposables;
	}

	public List<Cotisation> getCotisationsImposables() {
		return cotisationsImposables;
	}

	public void setCotisationsImposables(List<Cotisation> cotisationsImposables) {
		this.cotisationsImposables = cotisationsImposables;
	}

	public List<Avantage> getAvantages() {
		return avantages;
	}

	public void setAvantages(List<Avantage> avantages) {
		this.avantages = avantages;
	}

}
