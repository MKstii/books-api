package com.utmn.books_api.auth.model.entity;

import com.utmn.books_api.auth.model.converter.UserRoleConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity(name = "app_user")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    //TODO подтверждение почты?
    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @Column(name = "roles")
    @Convert(converter = UserRoleConverter.class)
    private Set<UserRole> roles;

    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority r = new SimpleGrantedAuthority(this.role);
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.name()))
                .collect(Collectors.toSet());
    }
}
