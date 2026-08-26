package com.exelynt.resourcebookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exelynt.resourcebookingsystem.entity.Reservation;
import com.exelynt.resourcebookingsystem.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping
    public Reservation createReservation(
            @RequestBody Reservation reservation) {

        return reservationService.createReservation(reservation);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public List<Reservation> getAllReservations() {

        return reservationService.getAllReservations();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public Reservation getReservationById(
            @PathVariable int id) {

        return reservationService.getReservationById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Reservation updateReservation(
            @PathVariable int id,
            @RequestBody Reservation reservation) {

        return reservationService.updateReservation(id, reservation);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable int id) {

        reservationService.deleteReservation(id);
    }
}