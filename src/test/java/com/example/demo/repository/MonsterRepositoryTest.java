package com.example.demo.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import com.example.demo.entity.Monster;

@JdbcTest
@Sql("RecordRepositoryTest.sql")
public class MonsterRepositoryTest {

	@Autowired
	JdbcTemplate jdbcTemplate;

	MonsterRepository monsterRepository;

	@BeforeEach
	void setup() {
		monsterRepository = new MonsterRepository(jdbcTemplate);
	}

	@Test
	void test_selectAll() {
		List<Monster> monsterList = monsterRepository.selectAll();
		assertThat(monsterList.size()).isEqualTo(2);
	}

	@Test
	void test_selectById() {
		Monster monster = monsterRepository.selectById(1);
		assertThat(monster.getName()).isEqualTo("ふしぎだね");
		assertThat(monster.getNo()).isEqualTo(1);
		assertThat(monster.getHeight()).isEqualTo(10);
		assertThat(monster.getWeight()).isEqualTo(10);
	}

	@Test
	void test_insert() {
		Monster monster = new Monster();
		monster.setNo(3);
		monster.setName("test_name");
		monster.setHeight(100);
		monster.setWeight(100);
		monster.setSprites("http://");
		monster.setTypes("fire");
		monsterRepository.insert(monster);

		Monster createdMonster = monsterRepository.selectById(3);
		assertThat(createdMonster.getNo()).isEqualTo(monster.getNo());
		assertThat(createdMonster.getName()).isEqualTo(monster.getName());
		assertThat(createdMonster.getHeight()).isEqualTo(monster.getHeight());
		assertThat(createdMonster.getWeight()).isEqualTo(monster.getWeight());
	}

	@Test
	void test_delete() {
		int id = 1;
		assertThat(monsterRepository.selectById(id)).isNotNull();
		monsterRepository.delete(id);
		assertThrows(Exception.class, () -> {
			monsterRepository.selectById(id);
		});
	}
}
