package com.mhsallam.imba.repositories;

import com.mhsallam.imba.models.entity.IdpHh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdpHhRepository extends JpaRepository<IdpHh, Long> {
    IdpHh findByRegisteredBy(Long id);
    IdpHh findByOriginAdmin1Code(String code);
    IdpHh findByDisplacementAdmin1Code(String code);
}
