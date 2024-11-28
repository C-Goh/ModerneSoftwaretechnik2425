package com.softwaretechnik.spielekiste.quiz.infrastructure.persistence;

import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.quiz.domain.entity.QuizEntity;
import com.softwaretechnik.spielekiste.quiz.domain.repository.QuizRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizRepositoryImpl implements QuizRepository {
    private static final Logger LOGGER = Logger.getLogger(QuizRepositoryImpl.class.getName());

    @Override
    public void startQuiz(int quizId) {
        final String getQuestionsSQL = "SELECT * FROM quiz_questions WHERE quiz_id = ?";
        final String insertCurrentQuestionSQL = "INSERT INTO current_question (quiz_id, question, answer_options, correct_answer) VALUES (?, ?, ?, ?)";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement getQuestionsStmt = connection.prepareStatement(getQuestionsSQL);
             PreparedStatement insertCurrentQuestionStmt = connection.prepareStatement(insertCurrentQuestionSQL)) {
            getQuestionsStmt.setInt(1, quizId);
            final ResultSet resultSet = getQuestionsStmt.executeQuery();
            final List<QuizEntity.Question> questions = new ArrayList<>();
            while (resultSet.next()) {
                final String question = resultSet.getString("question");
                final String[] answerOptions = resultSet.getString("answer_options").split(",");
                final String correctAnswer = resultSet.getString("correct_answer");
                questions.add(new QuizEntity.Question(0, question, Arrays.asList(answerOptions), correctAnswer));
            }
            Collections.shuffle(questions);
            final List<QuizEntity.Question> selectedQuestions = questions.subList(0, Math.min(3, questions.size()));
            for (final QuizEntity.Question q : selectedQuestions) {
                insertCurrentQuestionStmt.setInt(1, quizId);
                insertCurrentQuestionStmt.setString(2, q.getQuestion());
                insertCurrentQuestionStmt.setString(3, String.join(",", q.getAnswerOptions()));
                insertCurrentQuestionStmt.setString(4, q.getCorrectAnswer());
                insertCurrentQuestionStmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error starting quiz", e);
        }
    }

    @Override
    public QuizEntity.Question getCurrentQuestion(int quizId) {
        final String getCurrentQuestionSQL = "SELECT * FROM current_question WHERE quiz_id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getCurrentQuestionSQL)) {
            preparedStatement.setInt(1, quizId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final String question = resultSet.getString("question");
                final String[] answerOptions = resultSet.getString("answer_options").split(",");
                final String correctAnswer = resultSet.getString("correct_answer");
                return new QuizEntity.Question(0, question, Arrays.asList(answerOptions), correctAnswer);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting current question", e);
        }
        return null;
    }

    @Override
    public String checkAnswer(int quizId, String answer) {
        final String checkAnswerSQL = "SELECT correct_answer FROM current_question WHERE quiz_id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(checkAnswerSQL)) {
            preparedStatement.setInt(1, quizId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final String correctAnswer = resultSet.getString("correct_answer");
                if (correctAnswer.equalsIgnoreCase(answer)) {
                    // Update points in the database
                    // ...
                    return "Correct! Well done!";
                } else {
                    return "Incorrect! The correct answer was: " + correctAnswer;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking answer", e);
        }
        return "Error checking answer";
    }

    @Override
    public boolean nextQuestion(int quizId) {
        final String nextQuestionSQL = "UPDATE current_question SET question_id = question_id + 1 WHERE quiz_id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(nextQuestionSQL)) {
            preparedStatement.setInt(1, quizId);
            final int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error moving to next question", e);
        }
        return false;
    }

    @Override
    public String getFinalResult(int quizId) {
        final String getResultSQL = "SELECT points, total_questions FROM quiz_results WHERE quiz_id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getResultSQL)) {
            preparedStatement.setInt(1, quizId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final int points = resultSet.getInt("points");
                final int totalQuestions = resultSet.getInt("total_questions");
                final double percentage = Math.round(((double) points / totalQuestions) * 100);
                return "Final result: " + points + "/" + totalQuestions + " (" + percentage + "%)";
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting final result", e);
        }
        return "Error getting final result";
    }
}