package com.ssafy.group5.dto;

import lombok.Data;

@Data
public class UserRating {
	private String userId;
	private int attractionId;
	private int star;
}
