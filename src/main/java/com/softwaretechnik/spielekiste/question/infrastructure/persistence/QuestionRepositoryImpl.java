package com.softwaretechnik.spielekiste.question.infrastructure.persistence;

import com.softwaretechnik.spielekiste.question.domain.entity.QuestionEntity;
import com.softwaretechnik.spielekiste.question.domain.repository.QuestionRepository;
import com.softwaretechnik.spielekiste.infrastructure.persistence.SQLiteManager;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of the QuestionRepository interface for managing question entities in a SQLite database.
 */
public class QuestionRepositoryImpl implements QuestionRepository {
    private static final Logger LOGGER = Logger.getLogger(QuestionRepositoryImpl.class.getName());

    /**
     * Validates the question text.
     *
     * @param frage the question text to validate
     * @return true if the question is valid, false otherwise
     */
    private static boolean isValidQuestion(String frage) {
        return frage.matches("[a-zA-Z0-9 ]+");
    }

    @Override
    public void createQuestion(QuestionEntity frage) {
        if (!isValidQuestion(frage.getFrage())) {
            LOGGER.log(Level.WARNING, "Invalid text: {0}", frage.getFrage());
            return;
        }

        if (findQuestionById(frage.getId()) != null) {
            LOGGER.log(Level.WARNING, "Question already exists: {0}", frage.getFrage());
            return;
        }

        final String insertQuestionSQL = "INSERT INTO questions (id, name) VALUES (?, ?)";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement insertQuestionStatement = connection.prepareStatement(insertQuestionSQL)) {
                insertQuestionStatement.setInt(1, frage.getId());
                insertQuestionStatement.setString(2, frage.getFrage());
                insertQuestionStatement.setString(3, frage.getAntwort());
                Array moeglicheAntworten = connection.createArrayOf("VARCHAR", frage.getMoeglicheAntworten());
                insertQuestionStatement.setArray(2, moeglicheAntworten);
                insertQuestionStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating question", e);
        }
    }

    @Override
    public QuestionEntity findQuestionById(int id) {
        final String getQuestionSQL = "SELECT * FROM questions WHERE id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQuestionSQL)) {
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Array moeglicheAntworten = connection.createArrayOf("VARCHAR", frage.getMoeglicheAntworten());

                return new QuestionEntity(resultSet.getInt("id"), resultSet.getString("name"),
                 resultSet.getString("antwort"), resultSet.getArray(id));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting questions", e);
        }
        return null;
    }
// Drüberschauen
    @Override
    public List<QuestionEntity> findAllQuestions() {
        final List<QuestionEntity> fragen = new ArrayList<>();
        final String getAllQuestionsSQL = "SELECT * FROM questions";
        try (Connection connection = SQLiteManager.getConnection();
             Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(getAllQuestionsSQL)) {
            while (resultSet.next()) {
                fragen.add(new QuestionEntity(resultSet.getInt("id"), resultSet.getString("frage"),
                 resultSet.getString("antwort"), resultSet.getArray("moeglicheAntworten")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting all questions", e);
        }
        return fragen;
    }

    @Override
    public void updateQuestion(QuestionEntity frage) {
        final String updateQuestionSQL = "UPDATE questions SET frage = ? WHERE id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuestionSQL)) {
            preparedStatement.setString(1, frage.getFrage());
            preparedStatement.setInt(2, frage.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating question", e);
        }
    }

    @Override
    public void deleteQuestion(int id) {
        final String deleteQuestionSQL = "DELETE FROM questions WHERE id = ?";
        try (Connection connection = SQLiteManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuestionSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting question", e);
        }
    }
}
