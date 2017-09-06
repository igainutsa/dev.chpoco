package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.CoursesManager;
import dao.GroupsManager;
import dao.SpecialtyManager;
import dao.UserManager;
import model.User;

@Controller
public class UserController {

	@Autowired
	protected UserManager userManager;

	@Autowired
	protected GroupsManager groupsManager;

	@Autowired
	protected CoursesManager coursesManager;

	@Autowired
	protected SpecialtyManager specialtyManager;

	@RequestMapping(value = "/edit/{id}")
	public String editUser(@RequestParam(value = "userInfo", required = false, defaultValue = "error") List<String> userInfo, @RequestParam(value = "groups", required = false, defaultValue = "error") List<Object[]> groups,
			@RequestParam(value = "courses", required = false, defaultValue = "error") List<Object[]> courses, @RequestParam(value = "specialty", required = false, defaultValue = "error") List<Object[]> specialty, @PathVariable("id") long id,
			Model model) {

		List<Object[]> groupsList = groupsManager.getGroupsAll();
		List<Object[]> specialtyList = specialtyManager.getSpecialtyAll();
		List<Object[]> coursesList = coursesManager.getCoursesAll();

		User user = userManager.getUser(id);

		userInfo.add(user.getUserName());
		userInfo.add(user.getUserSurname());
		userInfo.add(user.getGroups().getGroupName());
		userInfo.add(user.getGroups().getCourses().getCoursesName());
		userInfo.add(user.getGroups().getSpecialty().getSpecialtyName());

		model.addAttribute("id", id);
		model.addAttribute("groups", groupsList);
		model.addAttribute("courses", coursesList);
		model.addAttribute("specialty", specialtyList);
		model.addAttribute("userInfo", userInfo);
		return "edit";

	}

}
