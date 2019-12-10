package com.mhsallam.imba.repositories;

import com.mhsallam.imba.models.entity.Admin2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Admin2Repository extends JpaRepository<Admin2, Long> {
    Admin2 findByName(String name);
    Admin2 findByCode(String code);
    List<Admin2> findAllByAdmin1Code(String code);
}