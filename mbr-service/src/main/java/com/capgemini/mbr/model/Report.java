package com.capgemini.mbr.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name ="REPORTS")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Report {
	@Id
    @GeneratedValue
	private Long reportId;
	@Column(name="projectName")
	@NotEmpty(message = "{notempty.projectName}")
	private String projectName;
	@Column(name="projectDesc")
	@NotEmpty(message = "{notempty.projectDesc}")
	private String projectDesc;
	@Column(name="barclaysPm")
	@NotEmpty(message = "{notempty.barclaysPm}")
    private String barclaysPm;
	@Column(name="bu")
	@NotEmpty(message = "{notempty.bu}")
	private String bu;
	@Column(name="phase")
	@NotEmpty(message = "{notempty.paase}")
	private String phase;
	@Column(name="keyMilestone")
	@NotEmpty(message = "{nonempty.keyMilestone}")
	private String keyMilestone;
	@Column(name="keyHighlights")
	@NotEmpty(message = "{nonempty.keyHighlights}")
	private String keyHighlights;
	@Column(name="barclaysFeedback")
	@NotEmpty(message = "{nonempty.barclaysFeedback}")
	private String 	barclaysFeedback;
	@Column(name="issueRoadblock")
	@NotEmpty(message = "{nonempty.issueRoadblock}")
	private String issueRoadblock;
	@Column(name="createdBy")
	@NotEmpty(message = "{nonempty.createdBy}")
	private String createdBy;
	@Column(name="createdDate")
	private LocalDate createdDate;
	@Column(name="updatedDate")
	private LocalDate updatedDate;

	public Report(){

	}

	public Report(Long reportId, String projectName, String projectDesc, String barclaysPm, String bu, String phase, String keyMilestone, String keyHighlights, String barclaysFeedback, String issueRoadblock, String createdBy, LocalDate createdDate,LocalDate updatedDate) {
		this.reportId = reportId;
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.barclaysPm = barclaysPm;
		this.bu = bu;
		this.phase = phase;
		this.keyMilestone = keyMilestone;
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
	public String getBu() {
		return bu;
	}
	public void setBu(String bu) {
		this.bu = bu;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getBarclaysPm() {
		return barclaysPm;
	}

	public void setBarclaysPlPm(String barclaysPm) {
		this.barclaysPm = barclaysPm;
	}

	public String getKeyMilestone() {
		return keyMilestone;
	}

	public void setKeyMileStoneGag(String keyMilestone) {
		this.keyMilestone = keyMilestone;
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

	@Override
	public String toString() {
		return "Report [reportId=" + reportId + ", projectName=" + projectName + ", projectDesc=" + projectDesc
				+ ", barclaysPm=" + barclaysPm + ", bu=" + bu + ", phase=" + phase + ", keyMilestone=" + keyMilestone
				+ ", keyHighlights=" + keyHighlights + ", barclaysFeedback=" + barclaysFeedback + ", issueRoadblock="
				+ issueRoadblock + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + "]";
	}

}
