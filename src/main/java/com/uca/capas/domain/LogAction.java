package com.uca.capas.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="log_actions")
public class LogAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOG_ACTIONS_ID_GENERATOR", sequenceName="LOG_ACTIONS_ID_LOG_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOG_ACTIONS_ID_GENERATOR")
	@Column(name="id_log", unique=true, nullable=false)
	private Integer id;

	@Column(name="description_log", length=300)
	private String description;

	@Column(name="reason_log", length=300)
	private String reason;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="admin_id", nullable=false)
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user2;

	public LogAction() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}