package kz.aibat.TaskManager.dto;

import kz.aibat.TaskManager.model.Theme;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private List<UserDTO> authUserList;
    private UserDTO author;
    private List<ThemeDTO> themeList;
}
