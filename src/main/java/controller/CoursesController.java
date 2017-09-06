package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.CoursesManager;

@Controller
public class CoursesController {

	@Autowired
	protected CoursesManager coursesManager;

	@RequestMapping("/courses")
	public String courses(@RequestParam(value = "courses", required = false, defaultValue = "error") List<Object[]> courses, Model model) {

		List<Object[]> coursesList = coursesManager.getCoursesAll();

		model.addAttribute("courses", coursesList);
		return "courses";
	}

}
