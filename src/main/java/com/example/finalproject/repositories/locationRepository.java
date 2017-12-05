package com.example.finalproject.repositories;

import com.example.finalproject.entity.Location;
import org.springframework.data.repository.CrudRepository;

public interface locationRepository extends CrudRepository<Location, Long>
{
}
