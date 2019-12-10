package com.mhsallam.imba.repositories;

import com.mhsallam.imba.models.entity.Admin1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Admin1Repository extends JpaRepository<Admin1, Long> {
    Admin1 findByName(String name);
    Admin1 findByCode(String code);
}