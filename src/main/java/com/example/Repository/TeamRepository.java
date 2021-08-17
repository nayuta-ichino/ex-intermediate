package com.example.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Team;

/**
 * Teamsテーブルを操作するリポジトリ.
 * 
 * @author nayuta
 */

@Repository
public class TeamRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	// ラムダ式で定義
	private static final RowMapper<Team> TEAM_ROW_MAPPER = (rs, i) -> {
		Team team = new Team();

		team.setId(rs.getInt("id"));
		team.setLeagueName(rs.getString("league_name"));
		team.setTeamName(rs.getString("team_name"));
		team.setHeadquarters(rs.getString("headquarters"));
		team.setInauguration(rs.getDate("inauguration"));
		team.setHistory(rs.getString("history"));

		return team;
	};

	/**
	 * チームを全件検索するメソッド.
	 * 
	 * @return teamList チームの一覧情報
	 */
	public List<Team> findAll() {
		// sql文を作成
		String findAllSql = "SELECT id, league_name, team_name, headquarters, inauguration, history FROM teams ORDER BY inauguration ASC;";

		// 実行
		List<Team> teamList = template.query(findAllSql, TEAM_ROW_MAPPER);

		return teamList;
	}

	/**
	 * チームの1件検索を行いメソッド.
	 * 
	 * @param id ID
	 * @return team 1件分のチームの詳細情報
	 */
	public Team load(Integer id) {
		// sql文を作成
		String loadSql = "SELECT team_name, headquarters, inauguration, history FROM teams WHERE id = :id;";

		// :idにデータを格納
		SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

		// 実行
		Team team = template.queryForObject(loadSql, params, TEAM_ROW_MAPPER);

		return team;
	}

}
