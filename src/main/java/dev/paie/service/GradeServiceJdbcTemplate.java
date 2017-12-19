package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;
import dev.paie.spring.DataSourceMySQLConfig;
import dev.paie.util.PaieUtils;

@Service
public class GradeServiceJdbcTemplate implements GradeService {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PaieUtils paieUtils;
	
	@Autowired
	private DataSourceMySQLConfig dataSource;

	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void sauvegarder(Grade nouveauGrade) {
		
		String sql = "INSERT INTO GRADE (CODE,NB_HEURES_BASE,TAUX_BASE) VALUES(?,?,?)";
		BigDecimal tauxBase = new BigDecimal(paieUtils.formaterBigDecimal(nouveauGrade.getTauxBase()));
		this.jdbcTemplate.update(sql, nouveauGrade.getCode(), nouveauGrade.getNbHeuresBase(), tauxBase );
		
	}

	@Override
	public void mettreAJour(Grade grade) {
		
		String sql = "UPDATE GRADE SET CODE = ?, NB_HEURES_BASE = ?, TAUX_BASE = ? WHERE ID = ?";
		this.jdbcTemplate.update(sql, grade.getCode(), grade.getNbHeuresBase(), grade.getTauxBase(), grade.getId());
		
	}

	@Override
	public List<Grade> lister() {

		String sql = "SELECT * FROM GRADE";
		return this.jdbcTemplate.query(sql, new GradeMapper());
	}

}
