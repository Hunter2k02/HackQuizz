package HackerQuizz.repository;

import HackerQuizz.model.Question;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Transactional
    @Query("SELECT q FROM Question q WHERE q.quiz.id = :quizId")
    List<Question> findAllByUserId(@Param("quizId") Long id);
}
