package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Monster {
	private int id;
	private int no;
	private String name;
	private int height;
	private int weight;
	private String sprites;
	private String types;
	private LocalDateTime createdAt;
}
