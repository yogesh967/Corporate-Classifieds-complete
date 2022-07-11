package com.cts.pointsmicroservice.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {

	Date timeStamp;
	String message;
	String status;

	public MessageResponse(String message, String string) {
		super();
		this.message = message;
		this.timeStamp = new Date();
		this.status = string;
	}
}
