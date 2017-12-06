package com.example.finalproject.repositories;

import com.example.finalproject.entity.Trainer;
import org.springframework.data.repository.CrudRepository;

public interface TrainerRepository extends CrudRepository<Trainer, Long>
{
    //Need to Pass Multiple Parameters to Get Working
    Iterable<Trainer> findAllByFirstNameOrLastNameOrContactNumberOrEmailOrRatingOrGenderContainingIgnoreCase(String firstName, String lastName, String contactNumber, String email, String rating, String gender);
}
