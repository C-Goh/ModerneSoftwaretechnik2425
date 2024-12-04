package com.softwaretechnik.spielekiste.quiz.infrastructure.persistence;

import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.quiz.domain.entity.QuizEntity;
import com.softwaretechnik.spielekiste.quiz.domain.repository.QuizRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizRepositoryImpl implements QuizRepository {
    private static final Logger LOGGER = Logger.getLogger(QuizRepositoryImpl.class.getName());

    @Override
    public QuizEntity startQuiz(int quizId) {
        final String getQuestionsSQL = "SELECT * FROM quiz_questions WHERE quiz_id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement getQuestionsStmt = connection.prepareStatement(getQuestionsSQL)) {
            getQuestionsStmt.setInt(1, quizId);
            final ResultSet resultSet = getQuestionsStmt.executeQuery();
            final List<QuizEntity.Question> questions = new ArrayList<>();
            while (resultSet.next()) {
                final int id = resultSet.getInt("id");
                final String question = resultSet.getString("question");
                final String[] answerOptions = resultSet.getString("answer_options").split(",");
                final String correctAnswer = resultSet.getString("correct_answer");
                questions.add(new QuizEntity.Question(id, question, Arrays.asList(answerOptions), correctAnswer));
            }
            return new QuizEntity(quizId, questions);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error starting quiz", e);
        }
        return null;
    }

    @Override
    public QuizEntity.Question getCurrentQuestion(int quizId, int questionId) {
        final String getCurrentQuestionSQL = "SELECT * FROM quiz_questions WHERE quiz_id = ? AND id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getCurrentQuestionSQL)) {
            preparedStatement.setInt(1, quizId);
            preparedStatement.setInt(2, questionId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final String question = resultSet.getString("question");
                final String[] answerOptions = resultSet.getString("answer_options").split(",");
                final String correctAnswer = resultSet.getString("correct_answer");
                return new QuizEntity.Question(questionId, question, Arrays.asList(answerOptions), correctAnswer);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting current question", e);
        }
        return null;
    }

    @Override
    public boolean checkAnswer(int quizId, int questionId, int userId, String answer) {
        final String checkAnswerSQL = "SELECT correct_answer FROM quiz_questions WHERE quiz_id = ? AND id = ? AND user_id = ?";
        final String updateUserAnswerSQL = "UPDATE quiz_questions SET user_answer = ? WHERE quiz_id = ? AND id = ? AND user_id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement checkAnswerStmt = connection.prepareStatement(checkAnswerSQL);
             PreparedStatement updateUserAnswerStmt = connection.prepareStatement(updateUserAnswerSQL)) {
            checkAnswerStmt.setInt(1, quizId);
            checkAnswerStmt.setInt(2, questionId);
            checkAnswerStmt.setInt(3, userId);
            final ResultSet resultSet = checkAnswerStmt.executeQuery();
            if (resultSet.next()) {
                final String correctAnswer = resultSet.getString("correct_answer");
                updateUserAnswerStmt.setString(1, answer);
                updateUserAnswerStmt.setInt(2, quizId);
                updateUserAnswerStmt.setInt(3, questionId);
                updateUserAnswerStmt.setInt(4, userId);
                updateUserAnswerStmt.executeUpdate();
                return correctAnswer.equalsIgnoreCase(answer);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking answer", e);
        }
        return false;
    }

    @Override
    public String getFinalResult(int quizId, int userId) {
        final String getResultSQL = "SELECT COUNT(*) AS total_questions, SUM(CASE WHEN correct_answer = user_answer THEN 1 ELSE 0 END) AS correct_answers FROM quiz_questions WHERE quiz_id = ? AND user_id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getResultSQL)) {
            preparedStatement.setInt(1, quizId);
            preparedStatement.setInt(2, userId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final int totalQuestions = resultSet.getInt("total_questions");
                final int correctAnswers = resultSet.getInt("correct_answers");
                final int percentage = (int) Math.round(((double) correctAnswers / totalQuestions) * 100);
                return "Final result: " + correctAnswers + "/" + totalQuestions + " (" + percentage + "%)";
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting final result", e);
        }
        return "Error getting final result";
    }

    @Override
    public int getFinalPoints(int quizId, int userId) {
        final String getResultSQL = "SELECT COUNT(*) AS total_questions, SUM(CASE WHEN correct_answer = user_answer THEN 1 ELSE 0 END) AS correct_answers FROM quiz_questions WHERE quiz_id = ? AND user_id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getResultSQL)) {
            preparedStatement.setInt(1, quizId);
            preparedStatement.setInt(2, userId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final int totalQuestions = resultSet.getInt("total_questions");
                final int correctAnswers = resultSet.getInt("correct_answers");
                final double percentage = Math.round(((double) correctAnswers / totalQuestions) * 100);
                return correctAnswers;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting final result", e);
        }
        return 0;
    }
}