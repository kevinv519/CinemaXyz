package com.uca.capas.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="department")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEPARTMENT_ID_GENERATOR", sequenceName="DEPARTMENT_ID_DEP_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPARTMENT_ID_GENERATOR")
	@Column(name="id_dep", unique=true, nullable=false)
	private Integer id;

	@Column(name="created_by", nullable=false, length=32)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date", nullable=false)
	private Date createdDate;

	@NotBlank(message="El nombre del departamento es un campo obligatorio")
	@Column(name="name_dep", nullable=false, length=60)
	private String name;

	@NotNull(message="El estado del departamento es un campo obligatorio")
	@Column(name="status_dep", nullable=false)
	private boolean active;

	
	@Column(name="updated_by", nullable=false, length=32)
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date", nullable=false)
	private Date updatedDate;

	//bi-directional many-to-one association to Municipality
	@OneToMany(mappedBy="department")
	private List<Municipality> municipalities;

	public Department() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Municipality> getMunicipalities() {
		return this.municipalities;
	}

	public void setMunicipalities(List<Municipality> municipalities) {
		this.municipalities = municipalities;
	}

	public Municipality addMunicipality(Municipality municipality) {
		getMunicipalities().add(municipality);
		municipality.setDepartment(this);

		return municipality;
	}

	public Municipality removeMunicipality(Municipality municipality) {
		getMunicipalities().remove(municipality);
		municipality.setDepartment(null);

		return municipality;
	}

}