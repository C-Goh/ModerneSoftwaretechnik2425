package com.softwaretechnik.spielekiste.badge.domain.service;

import com.softwaretechnik.spielekiste.game.domain.Game;
import com.softwaretechnik.spielekiste.shared.infrastructure.persistence.SQLiteManager;
import com.softwaretechnik.spielekiste.user.domain.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.*;

public class BadgeDomainServiceTest {

    private BadgeCondition<Game> badgeCondition;
    private BadgeDomainService<Game> badgeDomainService;
    private Game game;
    private UserEntity user;

    @BeforeEach
    public void setUp() {
        badgeCondition = Mockito.mock(BadgeCondition.class);
        badgeDomainService = new BadgeDomainService<>(List.of(badgeCondition));
        game = Mockito.mock(Game.class);
        user = Mockito.mock(UserEntity.class);

        when(game.getUser()).thenReturn(user);
    }

    @Test
    public void testCheckAndAwardBadges_ConditionMetAndBadgeNotAwarded() throws SQLException {
        when(badgeCondition.isConditionMet(game)).thenReturn(true);
        when(badgeCondition.getBadgeId()).thenReturn("badge1");
        when(user.getId()).thenReturn(1);

        try (MockedStatic<SQLiteManager> mockedSQLiteManager = mockStatic(SQLiteManager.class)) {
            Connection connection = mock(Connection.class);
            PreparedStatement preparedStatement = mock(PreparedStatement.class);
            ResultSet resultSet = mock(ResultSet.class);

            mockedSQLiteManager.when(SQLiteManager::getConnection).thenReturn(connection);
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            badgeDomainService.checkAndAwardBadges(game);

            verify(badgeCondition, times(1)).awardBadge(user, "badge1", game.getGameType());
        }
    }

    @Test
    public void testCheckAndAwardBadges_ConditionNotMet() {
        when(badgeCondition.isConditionMet(game)).thenReturn(false);

        badgeDomainService.checkAndAwardBadges(game);

        verify(badgeCondition, never()).awardBadge(any(), anyString(), anyString());
    }

    @Test
    public void testCheckAndAwardBadges_BadgeAlreadyAwarded() throws SQLException {
        when(badgeCondition.isConditionMet(game)).thenReturn(true);
        when(badgeCondition.getBadgeId()).thenReturn("badge1");
        when(user.getId()).thenReturn(1);

        try (MockedStatic<SQLiteManager> mockedSQLiteManager = mockStatic(SQLiteManager.class)) {
            Connection connection = mock(Connection.class);
            PreparedStatement preparedStatement = mock(PreparedStatement.class);
            ResultSet resultSet = mock(ResultSet.class);

            mockedSQLiteManager.when(SQLiteManager::getConnection).thenReturn(connection);
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(true);
            when(resultSet.getInt(1)).thenReturn(1);

            badgeDomainService.checkAndAwardBadges(game);

            verify(badgeCondition, never()).awardBadge(any(), anyString(), anyString());
        }
    }
}