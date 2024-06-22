package com.example.demo.repository;

import java.util.List;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Monster;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class MonsterRepository {
	private final JdbcTemplate jdbcTemplate;

	private final String baseSelectQueryString = """
			SELECT id,no,name,height,weight,sprites,types,created_at
			FROM monsters
			""";

	public List<Monster> selectAll() {
		return jdbcTemplate.query(baseSelectQueryString, new DataClassRowMapper<>(Monster.class));
	}

	public Monster selectById(int id) {
		return jdbcTemplate.queryForObject(baseSelectQueryString + "WHERE id=?",
				new DataClassRowMapper<>(Monster.class), id);
	}

	public void insert(Monster monster) {
		jdbcTemplate.update(
				"INSERT INTO monsters (no,name,height,weight,sprites,types,created_at) VALUES (?,?,?,?,?,?,CURRENT_TIMESTAMP)",
				monster.getNo(), monster.getName(), monster.getHeight(), monster.getWeight(), monster.getSprites(),
				monster.getTypes());
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM monsters WHERE id=?", id);
	}
}
