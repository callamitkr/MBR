package com.capgemini.mbr.report.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REPORTS")
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "report_Id")
	private Long reportId;
	private String keyHighlights;
	@Column(name = "barclaysFeedback")
	private String barclaysFeedback;
	@Column(name = "issueRoadblock")
	private String issueRoadblock;
	@Column(name = "createdBy")
	private String createdBy;
	@Column(name = "createdDate")
	private LocalDate createdDate;
	@Column(name = "updatedDate")
	private LocalDate updatedDate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne(optional = false)
	@JoinColumn(name = "status_id")
	private ProjectStatus projectStatus;

	public Report() {

	}

	public Report(String keyHighlights, String barclaysFeedback, String issueRoadblock, String createdBy,
			LocalDate createdDate, LocalDate updatedDate) {
		this.keyHighlights = keyHighlights;
		this.barclaysFeedback = barclaysFeedback;
		this.issueRoadblock = issueRoadblock;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getKeyHighlights() {
		return keyHighlights;
	}

	public void setKeyHighlights(String keyHighlights) {
		this.keyHighlights = keyHighlights;
	}

	public String getBarclaysFeedback() {
		return barclaysFeedback;
	}

	public void setBarclaysFeedback(String barclaysFeedback) {
		this.barclaysFeedback = barclaysFeedback;
	}

	public String getIssueRoadblock() {
		return issueRoadblock;
	}

	public void setIssueRoadBlock(String issueRoadblock) {
		this.issueRoadblock = issueRoadblock;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectStatus getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}

	@Override
	public String toString() {
		return "Report{" +
				"reportId=" + reportId +
				", keyHighlights='" + keyHighlights + '\'' +
				", barclaysFeedback='" + barclaysFeedback + '\'' +
				", issueRoadblock='" + issueRoadblock + '\'' +
				", createdBy='" + createdBy + '\'' +
				", createdDate=" + createdDate +
				", updatedDate=" + updatedDate +
				", projects=" + project +
				", projectStatus=" + projectStatus +
				'}';
	}
}
