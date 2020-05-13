package com.example.carnet.services;

import com.example.carnet.model.Role;
import com.example.carnet.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(@Qualifier("roleRepository")RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role getRoleByName(String name) {
        Role r = new Role();
        r.setRoleName(name);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("email", ignoreCase())
                .withIgnorePaths("user_id")
                .withIgnoreNullValues();
        Example<Role> exampleRole = Example.of(r, exampleMatcher);
        Optional<Role> maybeRole = roleRepository.findOne(exampleRole);
        return maybeRole.orElse(null);
    }
}
