package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "groups")
public class Groups {
	private long id;
	private Specialty specialty;
	private Courses courses;
	private String groupName;
	private Set<User> user = new HashSet<User>();

	public Groups() {

	}

	public Groups(long id) {
		this.id = id;
	}

	public Groups(long id, Specialty specialty, Courses courses, String groupName) {
		this.id = id;
		this.specialty = specialty;
		this.courses = courses;
		this.groupName = groupName;

	}

	@Id
	@Column(name = "id_groups")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "id_specialty")
	public Specialty getSpecialty() {
		return this.specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	@ManyToOne
	@JoinColumn(name = "id_courses")
	public Courses getCourses() {
		return this.courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	@Column(name = "groups_name")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@OneToMany(mappedBy = "groups", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

}
