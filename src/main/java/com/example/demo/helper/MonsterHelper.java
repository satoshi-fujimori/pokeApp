package com.example.demo.helper;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.demo.entity.Monster;
import com.example.demo.entity.MonsterApiResponse;
import com.example.demo.entity.MonsterApiResponse.Types;

public class MonsterHelper {
	public static Monster convertApiResponseToEntity(MonsterApiResponse response, String localizedName) {
		Monster monster = new Monster();
		monster.setNo(response.getId());
		monster.setName(localizedName);
		monster.setHeight(response.getHeight());
		monster.setWeight(response.getWeight());
		monster.setSprites(response.getSprites().getFrontDefault());
		String typesString = List.of((Types[]) response.getTypes()).stream().map(type -> type.getType().getName())
				.collect(Collectors.joining(","));
		monster.setTypes(typesString);
		return monster;
	}

	public static int generateNewMonsterNumber(List<Integer> caughtMonsterNumberList) {
		List<Integer> fullNumberList = IntStream.rangeClosed(1, 1025).boxed().collect(Collectors.toList());
		List<Integer> uncaughtMonsterNumberList = fullNumberList.stream()
				.filter(n -> !caughtMonsterNumberList.contains(n)).collect(Collectors.toList());
		Random random = new Random();
		int rondomIndex = random.nextInt(uncaughtMonsterNumberList.size());
		return uncaughtMonsterNumberList.get(rondomIndex);
	}
}
