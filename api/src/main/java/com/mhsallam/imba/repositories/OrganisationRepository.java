package com.mhsallam.imba.repositories;

import com.mhsallam.imba.models.entity.Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganisationRepository extends JpaRepository<Org, Long> {
    Optional<Org> findByName(String name);
}
