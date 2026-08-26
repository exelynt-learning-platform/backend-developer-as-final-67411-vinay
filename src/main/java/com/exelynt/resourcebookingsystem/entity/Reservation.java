package com.exelynt.resourcebookingsystem.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.exelynt.resourcebookingsystem.enums.ReservationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 private BigDecimal price;
	 @Enumerated(EnumType.STRING)
	 private ReservationStatus status;
	 private LocalDateTime reservationDateTime;
	 
	 @ManyToOne
	 @JoinColumn(name = "user_id", nullable = false)
	 private User user;
	 
	 @ManyToOne
	 @JoinColumn(name = "resource_id", nullable = false)
	 private Resource resource;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public LocalDateTime getReservationDateTime() {
		return reservationDateTime;
	}

	public void setReservationDateTime(LocalDateTime reservationDateTime) {
		this.reservationDateTime = reservationDateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public int getId() {
		return id;
	}
	 
	 
}
