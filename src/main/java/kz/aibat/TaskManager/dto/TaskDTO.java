package kz.aibat.TaskManager.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class TaskDTO {
    private Long id;
    private String name;
    private String description;
    private Date deadLine;
    private UserDTO user;
}
