package kz.aibat.TaskManager.service;

import kz.aibat.TaskManager.dto.UserDTO;
import kz.aibat.TaskManager.mapper.UserMapper;
import kz.aibat.TaskManager.model.AuthUser;
import kz.aibat.TaskManager.model.GrantAccess;
import kz.aibat.TaskManager.repository.AuthUserRepository;
import kz.aibat.TaskManager.repository.GrantAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;
    private final GrantAccessRepository grantAccessRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser user = authUserRepository.findByEmail(username);

        if(user != null) return user;
        throw new UsernameNotFoundException("User not found");
    }

    public AuthUser createUser(AuthUser authUser){
        GrantAccess userAccess = grantAccessRepository.findByAccessValue("ROLE_USER");
        List<GrantAccess> grantAccessList = new ArrayList<>();
        grantAccessList.add(userAccess);
        authUser.setGrantAccessList(grantAccessList);

        return authUserRepository.save(authUser);
    }

    public AuthUser getUserByEmail(String email){
        return authUserRepository.findByEmail(email);
    }

    public AuthUser updateUser(AuthUser authUser){
        return authUserRepository.save(authUser);
    }

    public List<AuthUser> getAllUsers(){
        return authUserRepository.findAll();
    }

    public List<UserDTO> getUserList(){
        List<AuthUser> authUserList = getAllUsers();
        return userMapper.authUserListToDTOList(authUserList);
    }

    public AuthUser getUserModelById(Long id){
        return authUserRepository.findById(id).orElse(null);
    }

    public UserDTO getUserById(Long id){
        AuthUser userModel = getUserModelById(id);
        return userMapper.authUserToDTO(userModel);
    }
}
