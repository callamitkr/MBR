package com.capgemini.mbr.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="BU")
public class Bu {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bu_id")
	private Long buId;
	@Column(name="bu")
	private String bu;
	
	public Bu() {}
	
	public Bu( String bu) {
		this.bu = bu;
	}
	public Long getBuId() {
		return buId;
	}
	@OneToMany
	public void setBuId(Long buId) {
		this.buId = buId;
	}
	public String getBu() {
		return bu;
	}
	public void setBu(String bu) {
		this.bu = bu;
	}

	@Override
	public String toString() {
		return "Bu{" +
				"buId=" + buId +
				", bu='" + bu + '\'' +
				'}';
	}
}
