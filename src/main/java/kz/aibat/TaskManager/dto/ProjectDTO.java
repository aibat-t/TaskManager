package kz.aibat.TaskManager.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private List<UserDTO> authUserList;
    private UserDTO author;
}
