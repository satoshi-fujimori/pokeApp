package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MonsterApiResponse {
	private int id;
	private String name;
	private int height;
	private int weight;
	@JsonProperty("sprites")
	private Sprites sprites;
	@JsonProperty("types")
	private Types[] types;

	@Data
	public static class Sprites {
		@JsonProperty("front_default")
		private String frontDefault;
	}

	@Data
	public static class Types {
		@JsonProperty("slot")
		private int slot;
		@JsonProperty("type")
		private TypeDetail type;

		@Data
		public static class TypeDetail {
			@JsonProperty("name")
			private String name;
		}
	}
}
