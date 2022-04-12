package kz.aibat.TaskManager.mapper;

import kz.aibat.TaskManager.dto.ThemeDTO;
import kz.aibat.TaskManager.model.Theme;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ThemeMapper {
    ThemeDTO themeToDTO(Theme theme);
    List<ThemeDTO> themeListToDTOList(List<Theme> themeList);
}
