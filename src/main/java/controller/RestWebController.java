package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.CoursesManager;
import dao.GroupsManager;
import dao.UserManager;
import message.Response;
import model.Courses;
import model.Groups;
import model.User;

@RestController
public class RestWebController {

	@Autowired
	protected CoursesManager coursesManager;

	@Autowired
	protected GroupsManager groupsManager;

	@Autowired
	protected UserManager userManager;

	@RequestMapping(value = "courses/postcourses", method = RequestMethod.POST)
	public Response postCustomer(@RequestBody Courses courses) {

		coursesManager.addCourses(courses.getCoursesName());
		Response response = new Response("Done", courses);

		return response;
	}

	@RequestMapping(value = "groups/postgroups", method = RequestMethod.POST)
	public Response postGroups(@RequestBody Groups groups) {

		groupsManager.addGroups(groups.getCourses(), groups.getSpecialty(), groups.getGroupName());
		Response response = new Response("Done", groups);

		return response;
	}

	@RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
	public Response deleteUser(@RequestBody User user) {

		userManager.deleteUser(user.getId());
		Response response = new Response("Done", user);

		return response;
	}

	@RequestMapping(value = "/addusergroups", method = RequestMethod.POST)
	@ResponseBody
	public Response addUserGroups(@RequestParam String userName, @RequestParam long idGroup, @RequestParam String userSurname) {

		long id = userManager.addUser(userName, userSurname, idGroup);

		Response response = new Response("Done", id);

		return response;
	}

	@RequestMapping(value = "/resetgroups", method = RequestMethod.POST)
	@ResponseBody
	public Response resetGroups(@RequestParam long idGroups, @RequestParam(value = "groupInfo", required = false, defaultValue = "error") List<String> groupInfo) {

		Groups groups = groupsManager.getGroups(idGroups);

		groupInfo.add(String.valueOf(groups.getCourses().getId()));
		groupInfo.add(String.valueOf(groups.getSpecialty().getId()));

		Response response = new Response("Done", groupInfo);

		return response;
	}

	@RequestMapping(value = "/resetcourses", method = RequestMethod.POST)
	@ResponseBody
	public Response resetCourses(@RequestParam long idCourses, @RequestParam long idSpecialty, @RequestParam(value = "coursesInfo", required = false, defaultValue = "error") List<Object[]> coursesInfo) {

		coursesInfo = groupsManager.getGroupsCourses(idCourses, idSpecialty);

		Response response = new Response("Done", coursesInfo);

		return response;
	}

	@RequestMapping(value = "/edituser", method = RequestMethod.POST)
	@ResponseBody
	public Response editUser(@RequestParam String userName, @RequestParam long idUser, @RequestParam long idGroup, @RequestParam String userSurname) {

		userManager.editUser(idUser, userName, userSurname, idGroup);

		Response response = new Response("Done", "NORM");

		return response;
	}

}
