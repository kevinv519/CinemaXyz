package com.uca.capas.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SESSION_ATT_LOG = "loggedUser";
	
	public static final Integer DISABLED = 0;
	public static final Integer ENABLED = 1;
	public static final Integer PENDING = 2;
	public static final Integer LOGGED_IN = 3;

	@Id
	@SequenceGenerator(name="USERS_ID_GENERATOR", sequenceName="USERS_ID_U_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_ID_GENERATOR")
	@Column(name="id_u", unique=true, nullable=false)
	private Integer id;

	@Column(name="account_balance", nullable=false, precision=10, scale=4)
	private BigDecimal accountBalance;

	@Column(name="address_u", length=256)
	private String address;

	@Column(name="approved_by", length=32)
	private String approvedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="approved_date")
	private Date approvedDate;

	@Temporal(TemporalType.DATE)
	@Column(name="birthdate_u", nullable=false)
	private Date birthdate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date", nullable=false)
	private Date createdDate;

	@Column(name="email_u", nullable=false, length=256)
	private String email;

	@Column(name="firstname_u", nullable=false, length=50)
	private String firstname;

	@Column(name="is_active_u", nullable=false)
	private Integer status;

	@Column(name="is_admin_u", nullable=false)
	private boolean isAdmin;

	@Column(name="lastname_u", nullable=false, length=50)
	private String lastname;

	@Column(name="password_u", nullable=false, length=64)
	private String password;

	@Column(name="updated_by", nullable=false, length=32)
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date", nullable=false)
	private Date updatedDate;

	@Column(name="username_u", nullable=false, length=32)
	private String username;

	//bi-directional many-to-one association to LogAction
	@OneToMany(mappedBy="user1")
	private List<LogAction> logActions1;

	//bi-directional many-to-one association to LogAction
	@OneToMany(mappedBy="user2")
	private List<LogAction> logActions2;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="user")
	private List<Ticket> tickets;

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="country_of_birth_id", nullable=false)
	private Country country;

	//bi-directional many-to-one association to Municipality
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="municipality_id", nullable=false)
	private Municipality municipality;

	public User() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return this.approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<LogAction> getLogActions1() {
		return this.logActions1;
	}

	public void setLogActions1(List<LogAction> logActions1) {
		this.logActions1 = logActions1;
	}

	public LogAction addLogActions1(LogAction logActions1) {
		getLogActions1().add(logActions1);
		logActions1.setUser1(this);

		return logActions1;
	}

	public LogAction removeLogActions1(LogAction logActions1) {
		getLogActions1().remove(logActions1);
		logActions1.setUser1(null);

		return logActions1;
	}

	public List<LogAction> getLogActions2() {
		return this.logActions2;
	}

	public void setLogActions2(List<LogAction> logActions2) {
		this.logActions2 = logActions2;
	}

	public LogAction addLogActions2(LogAction logActions2) {
		getLogActions2().add(logActions2);
		logActions2.setUser2(this);

		return logActions2;
	}

	public LogAction removeLogActions2(LogAction logActions2) {
		getLogActions2().remove(logActions2);
		logActions2.setUser2(null);

		return logActions2;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setUser(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setUser(null);

		return ticket;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Municipality getMunicipality() {
		return this.municipality;
	}

	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", accountBalance=" + accountBalance + ", address=" + address + ", approvedBy="
				+ approvedBy + ", approvedDate=" + approvedDate + ", birthdate=" + birthdate + ", createdDate="
				+ createdDate + ", email=" + email + ", firstname=" + firstname + ", status=" + status + ", isAdmin="
				+ isAdmin + ", lastname=" + lastname + ", password=" + password + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", username=" + username + "]";
	}

	
}