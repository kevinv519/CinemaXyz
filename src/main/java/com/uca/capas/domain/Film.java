package com.uca.capas.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="film")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FILM_ID_GENERATOR", sequenceName="FILM_ID_FILM_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FILM_ID_GENERATOR")
	@Column(name="id_film", unique=true, nullable=false)
	private Integer id;

	@Column(name="created_by", nullable=false, length=32)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date", nullable=false)
	private Date createdDate;

	@NotBlank(message="El nombre de los directores es un campo obligatorio")
	@Column(name="directors_film", length=150)
	private String directors;

	@NotBlank(message="El género de la película es un campo obligatorio")
	@Column(name="genres_film", length=200)
	private String genres;

	@Column(name="poster_film", length=100)
	private String posterUrl;

	@NotBlank(message="El rating de la película es un campo obligatorio")
	@Column(name="rating_film", nullable=false, length=10)
	private String rating;

	@Min(0)
	@NotNull(message="La duración de la película es un campo obligatorio")
	@Column(name="runtime_min_film", nullable=false)
	private Integer runtimeMin;

	@NotNull(message="El estado de la película es un campo obligatorio")
	@Column(name="status_film", nullable=false)
	private boolean active;

	@NotBlank(message="La sinopsis de la película es un campo obligatorio")
	@Column(name="synopsis_film", length=500)
	private String synopsis;

	@NotNull(message="El título de la película es un campo obligatorio")
	@Column(name="title_film", nullable=false, length=100)
	private String title;

	@Column(name="trailer_film", length=150)
	private String trailerUrl;

	@Column(name="updated_by", nullable=false, length=32)
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date", nullable=false)
	private Date updatedDate;

	//bi-directional many-to-one association to Showtime
	@OneToMany(mappedBy="film")
	private List<Showtime> showtimes;

	public Film() {
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

	public String getDirectors() {
		return this.directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getGenres() {
		return this.genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getPosterUrl() {
		return this.posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Integer getRuntimeMin() {
		return this.runtimeMin;
	}

	public void setRuntimeMin(Integer runtimeMin) {
		this.runtimeMin = runtimeMin;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getSynopsis() {
		return this.synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTrailerUrl() {
		return this.trailerUrl;
	}

	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
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
		showtime.setFilm(this);

		return showtime;
	}

	public Showtime removeShowtime(Showtime showtime) {
		getShowtimes().remove(showtime);
		showtime.setFilm(null);

		return showtime;
	}
	
	public String getActiveDelegate() {
		return active? "Activo" : "Inactivo";
	}
	
	public String getRuntimeDelegate() {
		return String.format("%d min", runtimeMin);
	}

}