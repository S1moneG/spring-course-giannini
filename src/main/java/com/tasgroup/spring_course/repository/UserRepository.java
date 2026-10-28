package com.tasgroup.spring_course.repository;

import com.tasgroup.spring_course.repository.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    /*
    In teoria per questo tipo di filtro non è necessario @Query
    Ma per ora essendo che è presente solo una tabella uso questa semplice query per testare @Query

    Per nativeQuery aggiungere nativeQuery = true come parametro extra dentro @Query e scrivere la query con linguaggio "nativo",
    sconsigliato visto che in caso si cambi DB bisogna poi modificare anche la query stessa molto spesso
    */
    @Query(
            value = "SELECT u FROM UserEntity u WHERE " +
                    "u.username = :username AND " +
                    "u.role = :role"
    )
    Optional<UserEntity> findByUsernameRole(String username, String role);


}
