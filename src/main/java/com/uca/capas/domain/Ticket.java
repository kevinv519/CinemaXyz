package com.uca.capas.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="ticket")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TICKET_ID_GENERATOR", sequenceName="TICKET_ID_TICKET_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TICKET_ID_GENERATOR")
	@Column(name="id_ticket", unique=true, nullable=false)
	private Integer id;

	@Column(name="net_total_ticket", nullable=false, precision=10, scale=4)
	private BigDecimal netTotal;

	@Column(name="payment_amount_ticket", nullable=false, precision=10, scale=4)
	private BigDecimal paymentAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="purchased_date_ticket", nullable=false)
	private Date purchasedDate;

	@Column(name="reserved_seats_ticket")
	private Integer reservedSeats;

	@Column(name="subtotal_ticket", nullable=false, precision=10, scale=4)
	private BigDecimal subtotal;

	//bi-directional many-to-one association to Showtime
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="showtime_id", nullable=false)
	private Showtime showtime;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	public Ticket() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getNetTotal() {
		return this.netTotal;
	}

	public void setNetTotal(BigDecimal netTotal) {
		this.netTotal = netTotal;
	}

	public BigDecimal getPaymentAmount() {
		return this.paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Date getPurchasedDate() {
		return this.purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public Integer getReservedSeats() {
		return this.reservedSeats;
	}

	public void setReservedSeats(Integer reservedSeats) {
		this.reservedSeats = reservedSeats;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public Showtime getShowtime() {
		return this.showtime;
	}

	public void setShowtime(Showtime showtime) {
		this.showtime = showtime;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}