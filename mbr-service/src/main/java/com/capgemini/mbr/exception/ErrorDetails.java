package com.capgemini.mbr.exception;

import java.util.List;

public class ErrorDetails {
	private Long timestamp;
    private String error;
    private List<String> description;

    public ErrorDetails(Long timestamp, String error, List<String> description) {
         super();
         this.timestamp = timestamp;
         this.error = error;
         this.description = description;
    }

    public Long getTimestamp() {
         return timestamp;
    }
	public String getError() {
		return error;
	}
	public List<String> getDescription() {return description; }

}
