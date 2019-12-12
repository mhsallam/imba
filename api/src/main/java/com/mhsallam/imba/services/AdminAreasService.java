package com.mhsallam.imba.services;

import com.mhsallam.imba.util.EnumPropUtil;
import com.mhsallam.imba.error.ConflictException;
import com.mhsallam.imba.models.entity.Admin1;
import com.mhsallam.imba.models.entity.Admin2;
import com.mhsallam.imba.repositories.Admin1Repository;
import com.mhsallam.imba.repositories.Admin2Repository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminAreasService {
    private final Log logger = LogFactory.getLog(AdminAreasService.class);

    @Autowired
    private Admin1Repository admin1Repository;
    @Autowired
    private Admin2Repository admin2Repository;
    @Autowired
    private EnumPropUtil enumPropUtil;

    public Admin1 findAdmin1ByName(String name) {
        return admin1Repository.findByName(name);
    }
    public Admin1 findAdmin1ByCode(String code) {
        return admin1Repository.findByCode(code);
    }

    public Admin2 findAdmin2ByName(String name) {
        return admin2Repository.findByName(name);
    }
    public Admin2 findAdmin2ByCode(String code) {
        return admin2Repository.findByCode(code);
    }

    public List<Admin1> findAllAdmin1() {
        return admin1Repository.findAll();
    }
    public List<Admin2> findAllAdmin2sByAdmin1Code(String code) {
        return admin2Repository.findAllByAdmin1Code(code);
    }

    public Admin1 addAdmin1(Admin1 admin1) throws ConflictException, Exception {
        if(admin1 == null) {
            throw new Exception("Wrong inputs");
        }

        Admin1 exist = admin1Repository.findByName(admin1.getName());
        if(exist != null) {
                throw new ConflictException("An admin1 with the same name is already exist");
        }
        return admin1Repository.save(admin1);
    }

    public Admin2 addAdmin2(Admin2 admin2) throws ConflictException, Exception {
        if(admin2 == null) {
            throw new Exception("Wrong inputs");
        }

        Admin2 exist = admin2Repository.findByCode(admin2.getCode());
        if(exist != null) {
            throw new ConflictException("An admin2 area with the same code is already exist");
        }

        return admin2Repository.save(admin2);
    }
}
