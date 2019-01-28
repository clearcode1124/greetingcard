package com.clearcode.greetingcard.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Greeting implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String message;

}
