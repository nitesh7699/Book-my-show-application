package com.acciojob.bookmyshowapplication.Repository;

import com.acciojob.bookmyshowapplication.Models.Theater;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {


}
