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

@Controller
public class GroupsController {

	@Autowired
	protected GroupsManager groupsManager;

	@Autowired
	protected CoursesManager coursesManager;

	@Autowired
	protected SpecialtyManager specialtyManager;

	@Autowired
	protected UserManager userManager;

	private long idd;

	@RequestMapping("/groups")
	public String groups(@RequestParam(value = "groups", required = false, defaultValue = "error") List<Object[]> groups, @RequestParam(value = "courses", required = false, defaultValue = "error") List<Object[]> courses,
			@RequestParam(value = "specialty", required = false, defaultValue = "error") List<Object[]> specialty, Model model) {

		List<Object[]> groupsList = groupsManager.getGroupsAll();
		List<Object[]> specialtyList = specialtyManager.getSpecialtyAll();
		List<Object[]> coursesList = coursesManager.getCoursesAll();

		model.addAttribute("groups", groupsList);
		model.addAttribute("courses", coursesList);
		model.addAttribute("specialty", specialtyList);
		return "groups";
	}

	@RequestMapping(value = "/groups/{id}")
	public String groupsId(@RequestParam(value = "user", required = false, defaultValue = "error") List<Object[]> user, @RequestParam(value = "idGroup", required = false, defaultValue = "0") long idGroup, @PathVariable("id") long id, Model model) {

		List<Object[]> groupsList = groupsManager.getGroupsName("" + id);
		for (Object[] course : groupsList) {
			idd = ((Number) course[0]).longValue();
		}

		List<Object[]> userList = userManager.getUserGroup(idd);

		model.addAttribute("id", id);
		model.addAttribute("idGroup", idd);
		model.addAttribute("user", userList);
		return "groupsId";

	}

}
