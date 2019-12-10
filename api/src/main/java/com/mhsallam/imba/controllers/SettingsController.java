package com.mhsallam.imba.controllers;

import com.mhsallam.imba.common.AppEnumProperties;
import com.mhsallam.imba.models.entity.Admin1;
import com.mhsallam.imba.models.entity.Admin2;
import com.mhsallam.imba.services.AdminAreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/settings")
public class SettingsController {

    @Autowired
    private AppEnumProperties appEnumProperties;

    @Autowired
    private AdminAreasService adminAreasService;

    @GetMapping(value = "/types/org")
    public ResponseEntity<Map<String, Integer>> getOrganisationTypes() {
        Map<String, Integer> result = appEnumProperties.getOrganisationTypes();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/types/identity")
    public ResponseEntity<Map<String, Integer>> getIdentityTypes() {
        Map<String, Integer> result = appEnumProperties.getIdentityTypes();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/admin1")
    public ResponseEntity<List<Admin1>> getAdmin1s() {
        List<Admin1> result = adminAreasService.findAllAdmin1();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/admin1/{code}")
    public ResponseEntity<Admin1> getAdmin1(@PathVariable String code) {
        Admin1 result = adminAreasService.findAdmin1ByCode(code);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/admin1/{code}/admin2")
    public ResponseEntity<List<Admin2>> getAdmin2s(@PathVariable String code) {
        List<Admin2> result = adminAreasService.findAllAdmin2sByAdmin1Code(code);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/admin2/{code}")
    public ResponseEntity<Admin2> getAdmin2(@PathVariable String code) {
        Admin2 result = adminAreasService.findAdmin2ByCode(code);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
