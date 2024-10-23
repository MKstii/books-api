package com.utmn.books_api.auth.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Entity(name = "app_user") //TODO ещещ один вид пользователя с ролью админа/менеджера и еще какой-нибудь хренью
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
//    @Convert(converter = UserRoleConverter.class)
    private UserRole role;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        var r = new SimpleGrantedAuthority(this.role.name());
        return Collections.singleton(r);
    }
}
