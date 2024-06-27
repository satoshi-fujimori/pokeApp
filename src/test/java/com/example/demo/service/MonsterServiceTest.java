package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.api.MonsterApi;
import com.example.demo.entity.Monster;
import com.example.demo.repository.MonsterRepository;

@SpringBootTest
@Transactional
public class MonsterServiceTest {

	@Autowired
	MonsterService monsterService;

	@Autowired
	MonsterApi monsterApi;

	@Autowired
	MonsterRepository monsterRepository;

	@Test
	void test_selectAll() {
		assertThat(monsterService.findAll().size()).isEqualTo(2);
	}

	@Test
	void test_selectById() {
		Monster monster = monsterService.findById(1);
		assertThat(monster.getNo()).isEqualTo(1);
		assertThat(monster.getName()).isEqualTo("フシギダネ");
		assertThat(monster.getHeight()).isEqualTo(7);
		assertThat(monster.getWeight()).isEqualTo(69);
	}

	@Test
	void test_create() {
		int newMonsterNumber = monsterService.create();

		Monster createdMonster = monsterRepository.selectById(3);
		assertThat(createdMonster).isNotNull();
		assertThat(createdMonster.getNo()).isEqualTo(newMonsterNumber);
		assertThat(createdMonster.getName()).isNotBlank();
		assertThat(createdMonster.getHeight()).isGreaterThan(0);
		assertThat(createdMonster.getWeight()).isGreaterThan(0);
		assertThat(createdMonster.getSprites()).isNotBlank();
		assertThat(createdMonster.getTypes()).isNotBlank();

		List<Integer> caughtMonsterNumberList = monsterRepository.selectAll().stream().map(monster -> monster.getNo())
				.collect(Collectors.toList());
		assertThat(caughtMonsterNumberList.contains(newMonsterNumber));
	}

	@Test
	void test_delete() {
		int initialRecordCount = monsterRepository.selectAll().size();
		monsterService.destroy(1);
		int currentRecordCount = monsterRepository.selectAll().size();
		assertThat(initialRecordCount).isEqualTo(currentRecordCount + 1);
	}
}
