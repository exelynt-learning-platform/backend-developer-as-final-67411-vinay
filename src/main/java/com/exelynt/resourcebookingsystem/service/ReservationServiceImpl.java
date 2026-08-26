package com.exelynt.resourcebookingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.exelynt.resourcebookingsystem.entity.Reservation;
import com.exelynt.resourcebookingsystem.entity.User;
import com.exelynt.resourcebookingsystem.repository.ReservationRepository;
import com.exelynt.resourcebookingsystem.repository.UserRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Reservation createReservation(Reservation reservation) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User loggedInUser = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // User identity comes from JWT/SecurityContext
        reservation.setUser(loggedInUser);

        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User loggedInUser = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // ADMIN can see all reservations
        if (loggedInUser.getRole().name().equals("ADMIN")) {
            return reservationRepository.findAll();
        }

        // USER should only see their own reservations
        return reservationRepository.findByUserId(loggedInUser.getId());
    }

    @Override
    public Reservation getReservationById(int id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User loggedInUser = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Reservation reservation =
                reservationRepository.findById(id).orElse(null);

        if (reservation == null) {
            return null;
        }

        // ADMIN can view any reservation
        if (loggedInUser.getRole().name().equals("ADMIN")) {
            return reservation;
        }

        // USER can view only their own reservation
        if (reservation.getUser().getId() == loggedInUser.getId()) {
            return reservation;
        }

        return null;
    }

    @Override
    public Reservation updateReservation(
            int id,
            Reservation reservation) {

        Reservation existing =
                reservationRepository.findById(id).orElse(null);

        if (existing != null) {

            existing.setPrice(reservation.getPrice());
            existing.setStatus(reservation.getStatus());
            existing.setReservationDateTime(
                    reservation.getReservationDateTime());
            existing.setResource(reservation.getResource());

            return reservationRepository.save(existing);
        }

        return null;
    }

    @Override
    public void deleteReservation(int id) {
        reservationRepository.deleteById(id);
    }
}