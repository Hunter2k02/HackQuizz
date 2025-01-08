package HackerQuizz.repository;

import HackerQuizz.model.AppUser;
import HackerQuizz.model.Progress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    @Query("Select p FROM Progress p WHERE p.user = :user")
    List<Progress> findByUser(@Param("user") AppUser user);
    @Query("Select p FROM Progress p WHERE p.user = :user and p.generalQuizTopic=:quizName")
    Progress findByUserAndGeneralQuizTopic(@Param("user") AppUser user, @Param("quizName") String name);
    @Modifying
    @Transactional
    @Query("UPDATE Progress p SET p.completedNumberOfModules=:value where p.id=:progressId")
    void updateNumberOfCompletedModulesById(@Param("progressId") long id, @Param("value") int value);
    @Modifying
    @Transactional
    @Query("DELETE Progress p WHERE p.user=:user")
    void deleteByUser(@Param("user") AppUser user);
}
