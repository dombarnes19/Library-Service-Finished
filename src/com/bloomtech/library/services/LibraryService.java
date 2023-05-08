package com.bloomtech.library.services;

import com.bloomtech.library.exceptions.LibraryNotFoundException;
import com.bloomtech.library.exceptions.ResourceExistsException;
import com.bloomtech.library.models.*;
import com.bloomtech.library.models.checkableTypes.Checkable;
import com.bloomtech.library.models.checkableTypes.Media;
import com.bloomtech.library.repositories.LibraryRepository;
import com.bloomtech.library.models.CheckableAmount;
import com.bloomtech.library.views.LibraryAvailableCheckouts;
import com.bloomtech.library.views.OverdueCheckout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    //TODO: Implement behavior described by the unit tests in tst.com.bloomtech.library.services.LibraryService

    @Autowired
    private LibraryRepository libraryRepository;

    public List<Library> getLibraries() {
        return new ArrayList<>();
    }

    public Library getLibraryByName(String name) {
        return null;
    }

    public void save(Library library) {
        List<Library> libraries = libraryRepository.findAll();
        if (libraries.stream().filter(p->p.getName().equals(library.getName())).findFirst().isPresent()) {
            throw new ResourceExistsException("Library with name: " + library.getName() + " already exists!");
        }
        libraryRepository.save(library);
    }

    public CheckableAmount getCheckableAmount(String libraryName, String checkableIsbn) {
        return new CheckableAmount(null, 0);
    }

    public List<LibraryAvailableCheckouts> getLibrariesWithAvailableCheckout(String isbn) {
        List<LibraryAvailableCheckouts> available = new ArrayList<>();

        return available;
    }

    public List<OverdueCheckout> getOverdueCheckouts(String libraryName) {
        List<OverdueCheckout> overdueCheckouts = new ArrayList<>();

        return overdueCheckouts;
    }
}
