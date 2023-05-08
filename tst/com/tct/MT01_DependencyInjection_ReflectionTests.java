package com.tct;

import com.bloomtech.library.controllers.CheckableController;
import com.bloomtech.library.controllers.LibraryController;
import com.bloomtech.library.repositories.CheckableRepository;
import com.bloomtech.library.repositories.LibraryCardRepository;
import com.bloomtech.library.repositories.LibraryRepository;
import com.bloomtech.library.repositories.PatronRepository;
import com.bloomtech.library.services.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MT01_DependencyInjection_ReflectionTests {

    @ParameterizedTest
    @ValueSource(classes = {
            LibraryService.class,
            PatronService.class,
            LibraryCardService.class,
            CheckableService.class,
            LibraryRepository.class,
            PatronRepository.class,
            LibraryCardRepository.class,
            CheckableRepository.class,
            LibraryController.class,
            CheckableController.class})
    void assertNoConstructors_In_Components(Class clazz) {
        assertEquals(0, clazz.getClass().getConstructors().length);
    }

    @ParameterizedTest
    @ValueSource(classes = {
            LibraryService.class,
            PatronService.class,
            LibraryCardService.class,
            CheckableService.class,
    })
    void checkAutowiredRepository_In_Services(Class serviceClass) throws NoSuchFieldException {
        String repositoryName = serviceClass.getName()
                .replaceAll("^.*s\\.", "")
                .replaceAll("Service", "Repository");
        repositoryName = repositoryName.substring(0,1).toLowerCase() + repositoryName.substring(1);

        assertTrue(Arrays.stream(serviceClass.getDeclaredField(repositoryName).getAnnotations())
                .anyMatch(a -> a.annotationType().equals(Autowired.class)));
    }

    @ParameterizedTest
    @ValueSource(classes = {
            LibraryRepository.class,
            PatronRepository.class,
            LibraryCardRepository.class,
            CheckableRepository.class})
    void checkAutowiredDatastore_In_Repositories(Class repoClass) throws NoSuchFieldException {
        assertTrue(Arrays.stream(repoClass.getDeclaredField("datastore").getAnnotations())
                .anyMatch(a -> a.annotationType().equals(Autowired.class)));
    }

    @ParameterizedTest
    @ValueSource(classes = {
            LibraryRepository.class,
            PatronRepository.class,
            LibraryCardRepository.class,
            CheckableRepository.class
    })
    void checkRepositoryAnnotation_In_Repositories(Class repoClass) {
        assertTrue(Arrays.stream(repoClass.getAnnotations())
                .anyMatch(a -> a.annotationType().equals(Repository.class)));
    }

    @ParameterizedTest
    @ValueSource(classes = {
            LibraryService.class,
            PatronService.class,
            LibraryCardService.class,
            CheckableService.class
    })
    void checkServiceAnnotation_In_Services(Class serviceClass) {
        assertTrue(Arrays.stream(serviceClass.getAnnotations())
                .anyMatch(a -> a.annotationType().equals(Service.class)));
    }

    @ParameterizedTest
    @ValueSource(classes = {
            LibraryController.class,
            CheckableController.class})
    void checkRestControllerAnnotation_In_Controllers(Class controllerClass) {
        assertTrue(Arrays.stream(controllerClass.getAnnotations())
                .anyMatch(a -> a.annotationType().equals(RestController.class)));
    }
}
