package HackerQuizz.repository;

import HackerQuizz.model.Quiz;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    Quiz findById(int quizId);
    @Modifying
    @Transactional
    @Query("UPDATE Quiz q  SET q.completed=:complete where q.id=:quizId ")
    void updateById(@Param("quizId") Long quizId,@Param("complete") boolean complete);
    @Query("SELECT q FROM Quiz q WHERE q.progress.id=:progressId")
    List<Quiz> findByProgress(@Param("progressId") long progressId);
}
