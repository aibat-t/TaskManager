package kz.aibat.TaskManager.service;

import kz.aibat.TaskManager.model.AuthUser;
import kz.aibat.TaskManager.repository.AuthUserRepository;
import kz.aibat.TaskManager.repository.GrantAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;
    private final GrantAccessRepository grantAccessRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser user = authUserRepository.findByEmail(username);

        if(user != null) return user;
        throw new UsernameNotFoundException("User not found");
    }
}
