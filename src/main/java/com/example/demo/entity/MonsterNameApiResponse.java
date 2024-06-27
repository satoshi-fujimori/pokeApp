package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MonsterNameApiResponse {
	@JsonProperty("names")
	private Names[] names;
	
	@Data
	public static class Names{
		@JsonProperty("language")
		private Language language;
		@JsonProperty("name")
		private String localizedName;
		
		@Data
		public static class Language{
			@JsonProperty("name")
			private String languageName;
		}
	}
}
