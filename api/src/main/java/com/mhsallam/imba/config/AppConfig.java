package com.mhsallam.imba.config;

import com.mhsallam.imba.models.dto.UserDto;
import com.mhsallam.imba.models.entity.User;
import com.mhsallam.imba.util.ObjectMappingUtil;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
   @Autowired
   public ObjectMappingUtil objectMappingUtil;

   @Bean
   TypeMap<User, UserDto> getTypeMap() {
      TypeMap<User, UserDto> typeMap = objectMappingUtil.createTypeMap(User.class, UserDto.class);
      typeMap.addMappings(m -> {
         m.map(User::getUsername, UserDto::setEmail);
         m.skip(UserDto::setPassword);
      });

      return typeMap;
   }
}
