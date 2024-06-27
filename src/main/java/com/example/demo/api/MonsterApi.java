package com.example.demo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.MonsterApiResponse;
import com.example.demo.entity.MonsterNameApiResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MonsterApi {
	private final RestTemplate restTemplate;
	private final String baseURL = "https://pokeapi.co/api/v2/pokemon/";
	private final String nameURL = "https://pokeapi.co/api/v2/pokemon-species/";

	public MonsterApiResponse getMonsterApiResponse(int no) {
		MonsterApiResponse monsterApiResponse = restTemplate.getForObject(baseURL + no + "/", MonsterApiResponse.class);
		return monsterApiResponse;
	}

	public String getLocalizedName(int no) {
		MonsterNameApiResponse response = restTemplate.getForObject(nameURL + no + "/", MonsterNameApiResponse.class);
		Optional<String> japaneseName = List.of(response.getNames()).stream()
				.filter(nameMap -> "ja-Hrkt".equalsIgnoreCase(nameMap.getLanguage().getLanguageName()))
				.map(nameMap -> nameMap.getLocalizedName()).findAny();

		return japaneseName.orElse("Japanese name not found");
	}
}
