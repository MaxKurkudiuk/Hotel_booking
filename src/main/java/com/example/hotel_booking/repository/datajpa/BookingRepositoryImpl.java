package com.example.hotel_booking.repository.datajpa;

import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class BookingRepositoryImpl implements BookingRepository {
    private final CrudBookingRepository crudBookingRepo;
    private final CrudUserRepository crudUserRepo;
    private final CrudRoomRepository crudRoomRepo;

    @Autowired
    public BookingRepositoryImpl(CrudBookingRepository bookingRepo, CrudUserRepository crudUserRepo, CrudRoomRepository crudRoomRepo) {
        this.crudBookingRepo = bookingRepo;
        this.crudUserRepo = crudUserRepo;
        this.crudRoomRepo = crudRoomRepo;
    }

    @Override
    public Booking save(Booking booking, int roomId, int userId) {
        if (!booking.isNew() && get(booking.getId(), userId) == null) {
            return null;
        }
        if (crudRoomRepo.findById(roomId).orElse(null) == null) {
            return null;
        }
        booking.setUser(crudUserRepo.getOne(userId));
        booking.setRoom(crudRoomRepo.getOne(roomId));
        return crudBookingRepo.save(booking);
    }

    @Override
    public Booking get(int id, int userId) {
        return crudBookingRepo.findById(id).filter(booking -> booking.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Booking> getAllByUser(int userId) {
        return crudBookingRepo.getAll(userId);
    }

    @Override
    public List<Booking> getAllBetweenWithRooms(LocalDate startDate, LocalDate endDate) {
        return crudBookingRepo.getAllBetweenWithRooms(startDate, endDate);
    }

    @Override
    public List<Booking> getAll() {
        return crudBookingRepo.findAll();
    }
}
