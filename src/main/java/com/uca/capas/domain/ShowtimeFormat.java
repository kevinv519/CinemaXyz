package com.uca.capas.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="showtime_format")
public class ShowtimeFormat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SHOWTIME_FORMAT_ID_GENERATOR", sequenceName="SHOWTIME_FORMAT_ID_STF_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SHOWTIME_FORMAT_ID_GENERATOR")
	@Column(name="id_stf", unique=true, nullable=false)
	private Integer id;

	@Column(name="created_by", nullable=false, length=32)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date", nullable=false)
	private Date createdDate;

	@NotBlank(message="El nombre es un campo obligatorio")
	@Column(name="name_stf", nullable=false, length=50)
	private String name;

	@Min(0)
	@NotNull(message="El precio es un campo obligatorio")
	@Column(name="price_stf", nullable=false, precision=8, scale=4)
	private BigDecimal price;

	@NotNull(message="El estado es un campo obligatorio")
	@Column(name="status_stf", nullable=false)
	private boolean active;

	@Column(name="updated_by", nullable=false, length=32)
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date", nullable=false)
	private Date updatedDate;

	//bi-directional many-to-one association to Showtime
	@OneToMany(mappedBy="showtimeFormat")
	private List<Showtime> showtimes;

	public ShowtimeFormat() {
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

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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
		showtime.setShowtimeFormat(this);

		return showtime;
	}

	public Showtime removeShowtime(Showtime showtime) {
		getShowtimes().remove(showtime);
		showtime.setShowtimeFormat(null);

		return showtime;
	}

}