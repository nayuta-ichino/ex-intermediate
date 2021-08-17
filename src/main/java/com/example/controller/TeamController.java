package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Service.TeamService;
import com.example.domain.Team;

@Controller
@RequestMapping("/exam1")
public class TeamController {
	@Autowired
	private TeamService teamService;

	/**
	 * チーム一覧を表示.
	 * 
	 * @param model requestスコープ
	 * @return トップページへ遷移
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Team> teamList = teamService.findAll();

		model.addAttribute("teamList", teamList);

		return "Exam1/teamsList";
	}

	/**
	 * チームの詳細を表示.
	 * 
	 * @param model requestパラメーター
	 * @param id    ID
	 * @return 詳細ページへ遷移
	 */
	@RequestMapping("/team-show")
	public String teamShow(Model model, Integer id) {
		Team team = teamService.load(id);

		model.addAttribute("team", team);

		return "Exam1/teamsDetail";
	}

}
