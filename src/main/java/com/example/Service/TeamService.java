package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Repository.TeamRepository;
import com.example.domain.Team;

/**
 * 従業員情報を操作するService.
 * 
 * @author nayuta
 */

@Service
@Transactional
public class TeamService {
	// TeamRepositoryをインスタンス化
	@Autowired
	private TeamRepository teamRepository;

	/**
	 * チームを全件検索するメソッド.
	 * 
	 * @return 従業員一覧の情報
	 */
	public List<Team> findAll() {
		return teamRepository.findAll();
	}

	/**
	 * チームの1件検索を行いメソッド.
	 * 
	 * @param id ID
	 * @return team 1件分のチームの詳細情報
	 */
	public Team load(Integer id) {
		return teamRepository.load(id);
	}

}
