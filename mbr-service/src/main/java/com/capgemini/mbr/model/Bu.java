package com.capgemini.mbr.model;

import com.sun.xml.bind.v2.model.core.ID;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(name ="BU")
@DynamicUpdate
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
