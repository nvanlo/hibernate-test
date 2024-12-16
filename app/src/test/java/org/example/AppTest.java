package org.example;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AppTest {
    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    @Transactional
    @Rollback(false)
    void setUp() {
        entityManager.persist(new User(1L, "Nik"));
        entityManager.flush();
        entityManager.clear();
    }

    @Transactional
    @Test
    void appHasAGreeting() {
        User user = entityManager.find(User.class, 1L);
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("Nik");

        assertThatThrownBy(() -> {
            User sameUser = new User(1L, "Nik2");
            entityManager.persist(sameUser);
            entityManager.flush();
        });
    }
}
