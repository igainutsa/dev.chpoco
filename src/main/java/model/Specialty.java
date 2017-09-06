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
@Table(name = "specialty")
public class Specialty {
	private long id;
	private String specialtyName;
	private Set<Groups> groups = new HashSet<Groups>();

	public Specialty() {
	}

	public Specialty(long id) {
		this.id = id;
	}

	public Specialty(long id, String specialtyName) {
		this.id = id;
		this.specialtyName = specialtyName;
	}

	@Id
	@Column(name = "id_specialty")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "specialty_name")
	public String getSpecialtyName() {
		return specialtyName;
	}

	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

	@OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Groups> getGroups() {
		return groups;
	}

	public void setGroups(Set<Groups> groups) {
		this.groups = groups;
	}

}
