package kz.aibat.TaskManager.repository;

import kz.aibat.TaskManager.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    List<Theme> findByProjectId(Long projectId);
}
