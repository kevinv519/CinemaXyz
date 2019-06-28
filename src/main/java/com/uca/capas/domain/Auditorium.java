package com.uca.capas.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="auditorium")
public class Auditorium implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AUDITORIUM_ID_GENERATOR", sequenceName="AUDITORIUM_ID_AUD_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUDITORIUM_ID_GENERATOR")
	@Column(name="id_aud", unique=true, nullable=false)
	private Integer id;

	@Positive
	@Column(name="clean_time_min_aud")
	private Integer cleanTimeMin;

	@Column(name="created_by", nullable=false, length=32)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date", nullable=false)
	private Date createdDate;

	@NotBlank(message = "Las características de la sala deben ser indicadas")
	@Column(name="description_aud", length=200)
	private String description;

	@Min(0)
	@Column(name="extra_price_aud", nullable=false, precision=8, scale=4)
	private BigDecimal extraPrice;

	@NotBlank(message = "El nombre del empleado es un campo obligatorio")
	@Column(name="name_aud", nullable=false, length=80)
	private String name;

	@NotNull(message = "La cantidad de asientos es un campo obligatorio")
	@Min(15)
	@Column(name="seats_aud", nullable=false)
	private Integer seats;

	@NotNull(message = "El estado de la sala es un campo obligatorio")
	@Column(name="status_aud", nullable=false)
	private boolean active;

	@Column(name="updated_by", nullable=false, length=32)
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date", nullable=false)
	private Date updatedDate;

	//bi-directional many-to-one association to Showtime
	@OneToMany(mappedBy="auditorium")
	private List<Showtime> showtimes;

	public Auditorium() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCleanTimeMin() {
		return this.cleanTimeMin;
	}

	public void setCleanTimeMin(Integer cleanTimeMin) {
		this.cleanTimeMin = cleanTimeMin;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getExtraPrice() {
		return this.extraPrice;
	}

	public void setExtraPrice(BigDecimal extraPrice) {
		this.extraPrice = extraPrice;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSeats() {
		return this.seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
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

	public List<Showtime> getShowtimes() {
		return this.showtimes;
	}

	public void setShowtimes(List<Showtime> showtimes) {
		this.showtimes = showtimes;
	}

	public Showtime addShowtime(Showtime showtime) {
		getShowtimes().add(showtime);
		showtime.setAuditorium(this);

		return showtime;
	}

	public Showtime removeShowtime(Showtime showtime) {
		getShowtimes().remove(showtime);
		showtime.setAuditorium(null);

		return showtime;
	}

	public String getActiveDelegate() {
		return active? "Disponible" : "No disponible";
	}
}