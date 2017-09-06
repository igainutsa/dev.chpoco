package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Courses {
	private long id;
	private String coursesName;
	private Set<Groups> groups = new HashSet<Groups>();

	public Courses() {
	}

	public Courses(long id) {
		this.id = id;
	}

	public Courses(long id, String coursesName) {
		this.id = id;
		this.coursesName = coursesName;
	}

	@Id
	@Column(name = "id_courses")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "courses_name")
	public String getCoursesName() {
		return coursesName;
	}

	public void setCoursesName(String coursesName) {
		this.coursesName = coursesName;
	}

	@OneToMany(mappedBy = "courses", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Groups> getGroups() {
		return groups;
	}

	public void setGroups(Set<Groups> groups) {
		this.groups = groups;
	}

}
