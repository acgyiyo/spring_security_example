package com.pluralsight.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pluralsight.model.Goal;
import com.pluralsight.model.GoalReport;
import com.pluralsight.service.GoalService;

@Controller
@SessionAttributes("goal")
public class GoalController {

//	@Autowired
	private GoalService goalService;

//	con esto estamos diciendo que cualquiera con el rol de admin puede ver un goal report
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "addGoal", method = RequestMethod.GET)
	public String addGoal(Model model, HttpSession session) {
		Goal goal = (Goal) session.getAttribute("goal");

		if (goal == null) {
			goal = new Goal();
			goal.setMinutes(10);
		}
		model.addAttribute("goal", goal);

		return "addGoal";
	}

//	con esto estamos diciendo que cualquiera con el rol de admin y que tenga permiso de crear goal puede hacer post de addGoal el '#' indica que es un objeto
	@PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#goal, 'createGoal')")
	@RequestMapping(value = "addGoal", method = RequestMethod.POST)
	public String updateGoal(@Valid @ModelAttribute("goal") Goal goal, BindingResult result) {

		System.out.println("result has errors: " + result.hasErrors());

		System.out.println("Goal set: " + goal.getMinutes());

		if (result.hasErrors()) {
			return "addGoal";
		} else {
//			goalService.save(goal);
		}

		return "redirect:index.jsp";
	}

	@RequestMapping(value = "getGoals", method = RequestMethod.GET)
	public String getGoal(Model model) {
		List<Goal> goals = goalService.findAllGoals();

		model.addAttribute("goals", goals);

		return "getGoals";
	}


	@RequestMapping(value = "goalReport", method = RequestMethod.GET)
	public String getGoalReports(Model model) {
		List<GoalReport> goalReports = goalService.findGoalReports();

		model.addAttribute("goalReports", goalReports);

		return "goalReport";
	}

}
