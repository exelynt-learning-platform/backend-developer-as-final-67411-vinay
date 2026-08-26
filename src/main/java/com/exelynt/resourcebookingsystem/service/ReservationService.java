package com.exelynt.resourcebookingsystem.service;

import java.util.List;

import com.exelynt.resourcebookingsystem.entity.Reservation;

public interface ReservationService {

    Reservation createReservation(Reservation reservation);

    List<Reservation> getAllReservations();

    Reservation getReservationById(int id);

    Reservation updateReservation(int id, Reservation reservation);

    void deleteReservation(int id);
}