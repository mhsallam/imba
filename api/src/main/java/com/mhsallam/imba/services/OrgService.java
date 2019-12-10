package com.mhsallam.imba.services;

import com.mhsallam.imba.error.ConflictException;
import com.mhsallam.imba.error.NotFoundException;
import com.mhsallam.imba.models.entity.Org;
import com.mhsallam.imba.repositories.OrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrgService {
   @Autowired
   private OrgRepository orgRepository;
//
//   @Autowired
//   private AppEnumProperties appEnumProperties;

   public Page<Org> findAll(Pageable pageable) {
      return orgRepository.findAll(pageable);
   }

   public Org find(String name, boolean throwable) throws NotFoundException {
      if(throwable) {
         return orgRepository.findByName(name)
                 .orElseGet(() -> { throw new NotFoundException("Organisation not found"); });
      } else {
         return orgRepository.findByName(name).get();
      }
   }

   public Org find(Long id, boolean throwable) throws NotFoundException {
      if(throwable) {
         return orgRepository.findById(id)
                 .orElseGet(() -> { throw new NotFoundException("Organisation not found"); });
      } else {
         return orgRepository.findById(id).get();
      }
   }

   public Org create(Org dto) throws ConflictException, Exception {
      if(dto == null) {
         throw new Exception("Wrong inputs");
      }

      orgRepository.findByName(dto.getName())
              .ifPresent(o -> { throw new ConflictException("An organisation with the same name [" + o.getName() + "] is already exist"); });

      Org org = new Org();
      org.setName(dto.getName());
      org.setAcronym(dto.getAcronym());
//      int orgType = appEnumProperties.getOrganisationTypes().get(dto.getType());
      org.setType(dto.getType());

      return orgRepository.save(org);
   }

   public Org update(Org dto) throws NotFoundException, Exception {
      if(dto == null) {
         throw new Exception("Wrong inputs");
      }

      Org org = orgRepository.findById(dto.getId())
              .orElseThrow(() -> new NotFoundException("Organisation not found"));

      org.setName(dto.getName());
      org.setAcronym(dto.getAcronym());
//      int orgType = appEnumProperties.getOrganisationTypes().get(dto.getType());
      org.setType(dto.getType());

      return orgRepository.save(org);
   }

   public void delete(Long id) throws NotFoundException{
      Org org = orgRepository.findById(id)
              .orElseThrow(() -> new NotFoundException("Organisation not found"));
      orgRepository.delete(org);
   }
}
