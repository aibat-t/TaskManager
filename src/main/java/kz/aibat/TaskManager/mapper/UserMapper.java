package kz.aibat.TaskManager.mapper;

import kz.aibat.TaskManager.dto.UserDTO;
import kz.aibat.TaskManager.model.AuthUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO authUserToDTO(AuthUser authUser);
    List<UserDTO> authUserListToDTOList(List<AuthUser> authUserList);
}
