package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.api.MonsterApi;
import com.example.demo.entity.Monster;
import com.example.demo.entity.MonsterApiResponse;
import com.example.demo.helper.MonsterHelper;
import com.example.demo.repository.MonsterRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MonsterService {
	private final MonsterRepository monsterRepository;
	private final MonsterApi monsterApi;

	public List<Monster> findAll() {
		return monsterRepository.selectAll();
	}

	public Monster findById(int id) {
		return monsterRepository.selectById(id);
	}

	public int create() {
		List<Integer> caughtMonsterNumberList = monsterRepository.selectAll().stream().map(monster -> monster.getNo())
				.collect(Collectors.toList());
		int no = MonsterHelper.generateNewMonsterNumber(caughtMonsterNumberList);
		MonsterApiResponse monsterApiResponse = monsterApi.getMonsterApiResponse(no);
		String localizedName = monsterApi.getLocalizedName(no);
		Monster monster = MonsterHelper.convertApiResponseToEntity(monsterApiResponse, localizedName);
		monsterRepository.insert(monster);
		return no;
	}

	public void destroy(int id) {
		monsterRepository.delete(id);
	}
}
