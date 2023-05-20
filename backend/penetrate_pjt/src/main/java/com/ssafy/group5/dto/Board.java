package com.ssafy.group5.dto;

import lombok.Data;

@Data
public class Board {
	private int articleno;
	private String userid;
	private String subject;
	private String content;
	private int hit;
	private String regtime;
}