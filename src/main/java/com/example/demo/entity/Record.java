package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Record {
	private int id;
	private Monster monster;
	private LocalDateTime createAt;
}
