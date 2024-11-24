package com.softwaretechnik.spielekiste.quiz.infrastructure.persistence;

import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.quiz.domain.repository.QuizRepository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizRepositoryImpl implements QuizRepository {
    private static final Logger LOGGER = Logger.getLogger(QuizRepositoryImpl.class.getName());

    @Override
    public boolean checkAnswer(String answer) {
        final String checkAnswerSQL = "SELECT COUNT(*) FROM quiz_answers WHERE answer = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(checkAnswerSQL)) {
            preparedStatement.setString(1, answer);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking answer", e);
        }
        return false;
    }

    @Override
    public String getQuizQuestion(int quizId) {
        final String getQuestionSQL = "SELECT question FROM quiz_questions WHERE quiz_id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQuestionSQL)) {
            preparedStatement.setInt(1, quizId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("question");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting quiz question", e);
        }
        return null;
    }

    @Override
    public String nextQuestion(int quizId) {
        final String nextQuestionSQL = "SELECT question FROM quiz_questions WHERE quiz_id = ? ORDER BY question_id LIMIT 1";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(nextQuestionSQL)) {
            preparedStatement.setInt(1, quizId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("question");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting next quiz question", e);
        }
        return null;
    }

    @Override
    public int getFinalResult(int quizId) {
        final String getResultSQL = "SELECT result FROM quiz_results WHERE quiz_id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getResultSQL)) {
            preparedStatement.setInt(1, quizId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("result");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting final result", e);
        }
        return 0;
    }
}