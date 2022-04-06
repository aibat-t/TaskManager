package kz.aibat.TaskManager.repository;

import kz.aibat.TaskManager.model.GrantAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrantAccessRepository extends JpaRepository<GrantAccess, Long> {

    GrantAccess findByAccessValue(String accessValue);
}
