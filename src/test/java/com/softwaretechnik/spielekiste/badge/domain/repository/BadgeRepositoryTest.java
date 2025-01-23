package com.softwaretechnik.spielekiste.badge.domain.repository;

import com.softwaretechnik.spielekiste.badge.domain.entity.BadgeEntity;
import com.softwaretechnik.spielekiste.badge.infrastructure.persistence.BadgeRepositoryImpl;
import com.softwaretechnik.spielekiste.shared.infrastructure.config.PropertyLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static com.softwaretechnik.spielekiste.shared.infrastructure.persistence.SQLiteManager.getConnection;
import static com.softwaretechnik.spielekiste.shared.infrastructure.persistence.SQLiteManager.initializeDatabase;
import static org.junit.jupiter.api.Assertions.*;

public class BadgeRepositoryTest {

    private BadgeRepositoryImpl badgeRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        PropertyLoader.loadProperties("src/test/resources/test-application.properties");
        initializeDatabase();
        badgeRepository = new BadgeRepositoryImpl();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS badges");
        }
    }

    @Test
    public void testSaveBadge() throws SQLException {
        BadgeEntity badge = new BadgeEntity();
        badge.setBadgeId("badge1");
        badge.setGameType("gameType1");
        badge.setUserId(1);
        badge.setName("Badge Name");
        badge.setText("Badge Text");
        badge.setCondition("Condition");
        badge.setHasEarned(true);

        badgeRepository.save(badge);

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM badges WHERE badgeId = 'badge1'");
            assertTrue(resultSet.next());
            assertEquals("Badge Name", resultSet.getString("name"));
        }
    }

    @Test
    public void testFindAllBadges() {
        BadgeEntity badge1 = new BadgeEntity();
        badge1.setBadgeId("badge1");
        badge1.setGameType("gameType1");
        badge1.setUserId(1);
        badge1.setName("Badge Name 1");
        badge1.setText("Badge Text 1");
        badge1.setCondition("Condition 1");
        badge1.setHasEarned(true);

        BadgeEntity badge2 = new BadgeEntity();
        badge2.setBadgeId("badge2");
        badge2.setGameType("gameType2");
        badge2.setUserId(2);
        badge2.setName("Badge Name 2");
        badge2.setText("Badge Text 2");
        badge2.setCondition("Condition 2");
        badge2.setHasEarned(false);

        badgeRepository.save(badge1);
        badgeRepository.save(badge2);

        List<BadgeEntity> badges = badgeRepository.findAll();
        assertEquals(2, badges.size());
    }

    @Test
    public void testFindBadgeById() {
        BadgeEntity badge = new BadgeEntity();
        badge.setBadgeId("badge1");
        badge.setGameType("gameType1");
        badge.setUserId(1);
        badge.setName("Badge Name");
        badge.setText("Badge Text");
        badge.setCondition("Condition");
        badge.setHasEarned(true);

        badgeRepository.save(badge);

        Optional<BadgeEntity> foundBadge = badgeRepository.findById(badge.getId());
        assertTrue(foundBadge.isPresent());
        assertEquals("Badge Name", foundBadge.get().getName());
    }

    @Test
    public void testFindBadgesByUserId() {
        BadgeEntity badge1 = new BadgeEntity();
        badge1.setBadgeId("badge1");
        badge1.setGameType("gameType1");
        badge1.setUserId(1);
        badge1.setName("Badge Name 1");
        badge1.setText("Badge Text 1");
        badge1.setCondition("Condition 1");
        badge1.setHasEarned(true);

        BadgeEntity badge2 = new BadgeEntity();
        badge2.setBadgeId("badge2");
        badge2.setGameType("gameType2");
        badge2.setUserId(1);
        badge2.setName("Badge Name 2");
        badge2.setText("Badge Text 2");
        badge2.setCondition("Condition 2");
        badge2.setHasEarned(false);

        badgeRepository.save(badge1);
        badgeRepository.save(badge2);

        List<BadgeEntity> badges = badgeRepository.findByUserId(1);
        assertEquals(2, badges.size());
    }

    @Test
    public void testDeleteBadgeById() throws SQLException {
        BadgeEntity badge = new BadgeEntity();
        badge.setBadgeId("badge1");
        badge.setGameType("gameType1");
        badge.setUserId(1);
        badge.setName("Badge Name");
        badge.setText("Badge Text");
        badge.setCondition("Condition");
        badge.setHasEarned(true);

        badgeRepository.save(badge);

        badgeRepository.deleteById(badge.getId());

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM badges WHERE badgeId = 'badge1'");
            assertFalse(resultSet.next());
        }
    }
}