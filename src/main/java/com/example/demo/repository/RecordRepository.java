package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Monster;
import com.example.demo.entity.Record;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class RecordRepository {
    private final JdbcTemplate jdbcTemplate;

    private final String baseSelectQueryString = """
            SELECT r.id as id, r.monster_id, r.created_at as created_at,
            m.id as monster_id, m.no as no, m.name as name,
            m.height as height, m.weight as weight, m.sprites as sprites, m.types as types
            FROM records r
            JOIN monsters m ON r.monster_id = m.id
            """;

    public List<Record> selectAll() {
        return jdbcTemplate.query(baseSelectQueryString, new RecordRowMapper());
    }

    public Record selectById(int id) {
        return jdbcTemplate.queryForObject(baseSelectQueryString + " WHERE r.id=?", new RecordRowMapper(), id);
    }

    public void insert(Record record) {
        jdbcTemplate.update("INSERT INTO records (monster_id, created_at) VALUES (?, CURRENT_TIMESTAMP)",
                record.getMonster().getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM records WHERE id=?", id);
    }

    static class RecordRowMapper implements RowMapper<Record> {
        @Override
        public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
            Record record = new Record();
            record.setId(rs.getInt("id"));
            record.setCreateAt(rs.getTimestamp("created_at").toLocalDateTime());

            Monster monster = new Monster();
            monster.setId(rs.getInt("monster_id"));
            monster.setNo(rs.getInt("no"));
            monster.setName(rs.getString("name"));
            monster.setHeight(rs.getInt("height"));
            monster.setWeight(rs.getInt("weight"));
            monster.setSprites(rs.getString("sprites"));
            monster.setTypes(rs.getString("types"));
            record.setMonster(monster);

            return record;
        }
    }
}
