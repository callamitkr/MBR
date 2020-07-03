package com.capgemini.mbr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="PROJECT_STATUS")
public class ProjectStatus {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="status_id")
	private Long statusId;
	@Column(name="status")
	private String status;
	
	public ProjectStatus() {	}
	public ProjectStatus(String status) {
		this.status = status;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProjectStatus{" +
				"statusId=" + statusId +
				", status='" + status + '\'' +
				'}';
	}
}
