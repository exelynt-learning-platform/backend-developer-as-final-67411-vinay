package com.exelynt.resourcebookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exelynt.resourcebookingsystem.entity.Reservation;

public interface ReservationRepository
        extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByUserId(int userId);
}