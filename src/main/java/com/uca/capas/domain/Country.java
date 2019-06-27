package com.uca.capas.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="country")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COUNTRY_ID_GENERATOR", sequenceName="COUNTRY_ID_COUNTRY_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COUNTRY_ID_GENERATOR")
	@Column(name="id_country", unique=true, nullable=false)
	private Integer id;

	@Column(name="created_by", nullable=false, length=32)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date", nullable=false)
	private Date createdDate;

	@Pattern(regexp = "[A-Z]{2}$", message = "El código ISO debe tener un formato válido")
	@NotBlank(message="El código ISO es un campo obligatorio")
	@Column(length=2)
	private String iso;

	@Pattern(regexp = "[A-Z]{3}$", message = "El código de país debe tener un formato válido")
	@NotBlank(message="El código de país es un campo obligatorio")
	@Column(length=3)
	private String iso3;

	@Pattern(regexp = "[A-Z]+$", message = "El código de país debe estar en mayúsculas")
	@NotBlank(message="El nombre es un campo obligatorio")
	@Column(name="name_country", nullable=false, length=80)
	private String name;

	@NotBlank(message="El nombre es un campo obligatorio")
	@Column(name="nicename_country", nullable=false, length=80)
	private String nicename;

	@NotNull(message="El estado del país es un campo obligatorio")
	@Column(name="status_country", nullable=false)
	private boolean active;

	@Column(name="updated_by", nullable=false, length=32)
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date", nullable=false)
	private Date updatedDate;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="country")
	private List<User> users;

	public Country() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getIso() {
		return this.iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public String getIso3() {
		return this.iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNicename() {
		return this.nicename;
	}

	public void setNicename(String nicename) {
		this.nicename = nicename;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setCountry(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setCountry(null);

		return user;
	}

}