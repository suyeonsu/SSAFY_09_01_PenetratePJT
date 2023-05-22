package com.ssafy.group5.dto;

import lombok.Data;

@Data
public class Rating {
	private int attractionId;
	private int star1;
	private int star2;
	private int star3;
	private int star4;
	private int star5;
	private double totalScore;
}
