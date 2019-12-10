package com.mhsallam.imba.services;

import com.mhsallam.imba.bootstrap.DataBootstraper;
import com.mhsallam.imba.error.ConflictException;
import com.mhsallam.imba.error.NotFoundException;
import com.mhsallam.imba.models.dto.UserDto;
import com.mhsallam.imba.models.entity.Role;
import com.mhsallam.imba.models.entity.User;
import com.mhsallam.imba.repositories.RoleRepository;
import com.mhsallam.imba.repositories.UserRepository;
import com.mhsallam.imba.security.TokenUtil;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final Log logger = LogFactory.getLog(DataBootstraper.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TokenUtil tokenUtil;

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            NotFoundException ex =  new NotFoundException("User not found");
            ex.getAttributes().put("code", "50008");
            throw ex;
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.isEnabled(), true, true,
                true, getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));

        return authorities;
    }

    public User register(UserDto dto, boolean isEnabled) throws ConflictException {

        User exist = userRepository.findByEmail(dto.getEmail());
        if (exist != null) {
            throw new ConflictException("There is a user with that same email: " + dto.getEmail());
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setAvatar(dto.getAvatar());
        user.setCreated(new Date());
        user.setEnabled(isEnabled);

        List<String> dtoRoles = dto.getRoles();
        List<Role> roles = new ArrayList<Role>();
        if(dtoRoles != null) {
            dtoRoles.forEach(r -> {
                Role adminRole = roleRepository.findByName(r);
                roles.add(adminRole);
            });
        }
        user.setRoles(roles);
        return this.save(user);
    }


    public User update(UserDto dto) throws NotFoundException, Exception {
        if(dto == null) {
            throw new Exception("Wrong inputs");
        }

        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        List<Role> userRoles = new ArrayList<Role>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        userRoles.add(userRole);
        Collection<String> dtoRoles = dto.getRoles();
        if(dtoRoles != null) {
            dtoRoles.forEach(r -> {
                if(r == "ROLE_ADMIN") {
                    Role adminRole = roleRepository.findByName("ROLE_ADMIN");
                    userRoles.add(adminRole);
                }
            });
        }
        user.setRoles(userRoles);

        return userRepository.save(user);
    }

    public void delete(Long id) throws NotFoundException{
        User org = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Organisation not found"));
        userRepository.delete(org);
    }


    public String refreshToken(String oldToken) {
        if (oldToken != null && StringUtils.isNotEmpty(oldToken)) {
            final String authToken = oldToken.substring("Bearer ".length());
            return tokenUtil.refreshToken(authToken);
        }
        return null;
    }

    public User save(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        try {
            return this.userRepository.save(user);
        } catch (Exception ex) {
            logger.error(ex);
        }
        return null;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
