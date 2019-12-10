package com.mhsallam.imba.controllers;

import com.mhsallam.imba.common.AppEnumProperties;
import com.mhsallam.imba.error.ConflictException;
import com.mhsallam.imba.error.NotFoundException;
import com.mhsallam.imba.models.entity.Org;
import com.mhsallam.imba.services.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/org")
public class OrgController {

    @Autowired
    private OrgService orgService;

    @Autowired
    private AppEnumProperties appEnumProperties;

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping({"", "/list"})
    public ResponseEntity<Page<Org>> getAll(Pageable pageable) {
        Page<Org> result = orgService.findAll(pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Org> getById(@PathVariable Long id) {
        Org result = orgService.find(id, true);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
//    @GetMapping("/search")
//    public ResponseEntity<Page<Org>> search(Pageable pageable) {
//        Page<Org> result = orgService.findAll(pageable);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Org> create(@RequestBody Org org) throws ConflictException, Exception {
        Org result = orgService.create(org);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Org> update(@RequestBody Org org) throws ConflictException, Exception {
        Org result = orgService.update(org);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
        orgService.delete(id);
        return ResponseEntity.ok().build();
    }
}
