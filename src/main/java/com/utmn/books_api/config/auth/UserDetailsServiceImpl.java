package com.utmn.books_api.config.auth;

import com.utmn.books_api.auth.model.entity.AppUser;
import com.utmn.books_api.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser appUser = userRepository.findByLogin(username)
                .orElseThrow(
                        () -> new AuthenticationServiceException("Пользователь не найден")
                );

        return new User(
                appUser.getLogin(),
                appUser.getPassword(),
                appUser.getAuthorities()
        );
    }
}
