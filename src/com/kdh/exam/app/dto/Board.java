package com.kdh.exam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Board {
	private int id;
	private String regDate;
	private String updateDate;
	private String code;
	private String name;
}
