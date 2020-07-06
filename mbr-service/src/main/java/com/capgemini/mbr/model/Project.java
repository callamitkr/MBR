package com.capgemini.mbr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="PROJECT")
public class Project {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectId;
	@Column(name="projectName")
	private String projectName;
	@Column(name="projectDesc")
	private String projectDesc;
	@Column(name="barclaysPm")
	private String barclaysPm;
	
	@ManyToOne(optional = false)
	@JoinColumn(name ="buId")
	private Bu bu;
	@ManyToOne(optional = false)
	@JoinColumn(name ="phaseId")
	private Phase phase;
	
	public Project(){
		
	}

	public Project(String projectName, String projectDesc, String barclaysPm) {
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.barclaysPm = barclaysPm;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getBarclaysPm() {
		return barclaysPm;
	}

	public void setBarclaysPm(String barclaysPm) {
		this.barclaysPm = barclaysPm;
	}

	public Bu getBu() {
		return bu;
	}

	public void setBu(Bu bu) {
		this.bu = bu;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", projectDesc=" + projectDesc
				+ ", barclaysPm=" + barclaysPm + ", bu=" + bu + ", phase=" + phase + "]";
	}
	
	
}
