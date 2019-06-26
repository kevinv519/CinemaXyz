package com.uca.capas.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="showtime")
public class Showtime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SHOWTIME_ID_GENERATOR", sequenceName="SHOWTIME_ID_ST_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SHOWTIME_ID_GENERATOR")
	@Column(name="id_st", unique=true, nullable=false)
	private Integer id;

	@Min(0)
	@NotNull(message="La cantidad de asientos es un campo obligatorio")
	@Column(name="availabe_seats_st", nullable=false)
	private Integer availabeSeats;

	@Column(name="created_by", nullable=false, length=32)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date", nullable=false)
	private Date createdDate;

	@NotNull(message="Debe indicarse si la función es en inglés")
	@Column(name="in_english", nullable=false)
	private boolean inEnglish;

	@Min(0)
	@NotNull(message="El precio de la función debe indicarse")
	@Column(name="price_st", nullable=false, precision=8, scale=4)
	private BigDecimal price;

	@NotNull(message="El horario de la función es un campo obligatorio")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="schedule_st", nullable=false)
	private Date schedule;

	@NotNull(message="El estado de la función es un campo obligatorio")
	@Column(name="status_st", nullable=false)
	private boolean active;

	@Column(name="updated_by", nullable=false, length=32)
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date", nullable=false)
	private Date updatedDate;

	//bi-directional many-to-one association to Auditorium
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="auditorium_id", nullable=false)
	private Auditorium auditorium;

	//bi-directional many-to-one association to Film
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="film_id", nullable=false)
	private Film film;

	//bi-directional many-to-one association to ShowtimeFormat
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="stf_id", nullable=false)
	private ShowtimeFormat showtimeFormat;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="showtime")
	private List<Ticket> tickets;

	public Showtime() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAvailabeSeats() {
		return this.availabeSeats;
	}

	public void setAvailabeSeats(Integer availabeSeats) {
		this.availabeSeats = availabeSeats;
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

	public boolean getInEnglish() {
		return this.inEnglish;
	}

	public void setInEnglish(boolean inEnglish) {
		this.inEnglish = inEnglish;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getSchedule() {
		return this.schedule;
	}

	public void setSchedule(Date schedule) {
		this.schedule = schedule;
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

	public Auditorium getAuditorium() {
		return this.auditorium;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public ShowtimeFormat getShowtimeFormat() {
		return this.showtimeFormat;
	}

	public void setShowtimeFormat(ShowtimeFormat showtimeFormat) {
		this.showtimeFormat = showtimeFormat;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setShowtime(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setShowtime(null);

		return ticket;
	}

}