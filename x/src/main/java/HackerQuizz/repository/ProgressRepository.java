package HackerQuizz.repository;

import HackerQuizz.model.AppUser;
import HackerQuizz.model.Progress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Integer> {
    Progress findByUser(AppUser user);
    @Transactional
    List<Progress> findAllByUserId(Long userId);

}
