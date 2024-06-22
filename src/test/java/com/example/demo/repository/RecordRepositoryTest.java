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
import com.example.demo.entity.Record;

@JdbcTest
@Sql("RecordRepositoryTest.sql")
public class RecordRepositoryTest {

	@Autowired
	JdbcTemplate jdbcTemplate;

	RecordRepository recordRepository;

	@BeforeEach
	void setup() {
		recordRepository = new RecordRepository(jdbcTemplate);
	}

	@Test
	void test_selectAll() {
		List<Record> recordList = recordRepository.selectAll();
		assertThat(recordList.size()).isEqualTo(2);
	}

	@Test
	void test_selectById() {
		Record record = recordRepository.selectById(1);
		Monster monster = record.getMonster();
		assertThat(monster.getName()).isEqualTo("ふしぎだね");
		assertThat(monster.getHeight()).isEqualTo(10);
	}

	@Test
	void test_insert() {
		Record record = new Record();
		Monster monster = new Monster();
		monster.setId(1);
		record.setMonster(monster);
		recordRepository.insert(record);

		Record createdRecord = recordRepository.selectById(3);
		Monster createdMonster = createdRecord.getMonster();
		assertThat(createdMonster.getId()).isEqualTo(1);
		assertThat(createdMonster.getName()).isEqualTo("ふしぎだね");
	}

	@Test
	void test_delete() {
		int id = 1;
		assertThat(recordRepository.selectById(id)).isNotNull();
		recordRepository.delete(id);
		assertThrows(Exception.class, () -> {
			recordRepository.selectById(id);
		});
	}
}
