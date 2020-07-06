package com.capgemini.mbr.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="PHASE")
public class Phase {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "phase_id")
	private Long phaseId;
	@Column(name="phase")
	private String phase;
	public Phase() {}
	public Phase(String phase) {
		this.phase = phase;
	}
	public Long getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}

	@Override
	public String toString() {
		return "Phase{" +
				"phaseId=" + phaseId +
				", phase='" + phase + '\'' +
				'}';
	}
}
