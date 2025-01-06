package HackerQuizz.repository;

import HackerQuizz.model.AppUser;
import HackerQuizz.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    @Query("Select p FROM Progress p WHERE p.user = :user")
    List<Progress> findByUser(@Param("user") AppUser user);
}
