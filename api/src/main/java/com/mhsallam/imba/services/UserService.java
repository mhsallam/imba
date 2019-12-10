package com.mhsallam.imba.services;

import com.mhsallam.imba.bootstrap.DataBootstraper;
import com.mhsallam.imba.error.ConflictException;
import com.mhsallam.imba.error.NotFoundException;
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

    public User register(User user, boolean isAdmin) throws ConflictException {

        User exist = userRepository.findByEmail(user.getUsername());
        if (exist != null) {
            throw new ConflictException("There is an user with that same email: " + user.getUsername());
        }

        Role userRole = roleRepository.findByName("ROLE_USER");

        logger.info("Adding ROLE_USER..");
        List<Role> roles = new ArrayList<Role>();
        roles.add(userRole);

        if(isAdmin) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            logger.info("Adding ROLE_ADMIN..");
            roles.add(adminRole);
        }

        user.setRoles(roles);
        return this.save(user);
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
