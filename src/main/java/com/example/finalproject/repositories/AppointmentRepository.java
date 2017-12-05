package com.example.finalproject.repositories;

import com.example.finalproject.entity.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long>
{
}
