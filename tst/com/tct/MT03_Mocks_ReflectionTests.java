package com.tct;

import com.bloomtech.library.repositories.CheckableRepository;
import com.bloomtech.library.services.CheckableServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MT03_Mocks_ReflectionTests {

    @BeforeEach
    void libraryRepository() throws NoSuchFieldException {
        assertTrue(CheckableServiceTest.class.getDeclaredField("checkableRepository").getType().equals(CheckableRepository.class));
    }

    @Test
    void testMockBean() throws NoSuchFieldException {
        assertTrue(Arrays.stream(CheckableServiceTest.class.getDeclaredField("checkableRepository").getAnnotations())
                .filter(a -> a.annotationType().equals(MockBean.class))
                .findAny()
                .isPresent());
    }

    @Test
    void testNoAutowire() throws NoSuchFieldException {
        assertFalse(Arrays.stream(CheckableServiceTest.class.getDeclaredField("checkableRepository").getAnnotations())
                .filter(a -> a.annotationType().equals(Autowired.class))
                .findAny()
                .isPresent());
    }
}
