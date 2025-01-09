package HackerQuizz.repository;

import HackerQuizz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    Quiz findById(int quizId);
    @Query("SELECT q from Quiz q WHERE q.quizName=:name")
    Quiz findByName(@Param("name") String name);
}
