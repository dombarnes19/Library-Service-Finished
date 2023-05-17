package com.bloomtech.library.services;

import com.amazonaws.event.DeliveryMode;
import com.bloomtech.library.exceptions.CheckableNotFoundException;
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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    //TODO: Implement behavior described by the unit tests in tst.com.bloomtech.library.services.LibraryService

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private CheckableService checkableService;

    public List<Library> getLibraries() {

        return libraryRepository.findAll();
    }

    public Library getLibraryByName(String name) {
        Optional<Library> library = libraryRepository.findByName(name);
        if (library.isEmpty()) {
            library = getLibraries()
                    .stream()
                    .filter(l -> l.getName().equals(name))
                    .findFirst();
            if(library.isEmpty()){
                throw new LibraryNotFoundException(String.format("Library with the name: %s was not found", name));
            }
        }
        return library.get();
    }

    public void save(Library library) {
        List<Library> libraries = getLibraries();
        if (libraries.stream().anyMatch(p->p.getName().equals(library.getName()))) {
            throw new ResourceExistsException("Library with name: " + library.getName() + " already exists!");
        }
        libraryRepository.save(library);
    }

    public CheckableAmount getCheckableAmount(String libraryName, String checkableIsbn) {
        Library library = getLibraryByName(libraryName);
        List<CheckableAmount> checkableAmounts = library.getCheckables();
        Checkable checkable = checkableService.getByIsbn(checkableIsbn);
        for (CheckableAmount checkableAmount : checkableAmounts) {
            if (checkableAmount.getCheckable() == checkable) {
                return checkableAmount;
            }
        }

        return new CheckableAmount(checkable, 0);
    }

    public CheckableAmount getCheckableAmount(String libraryName, Checkable checkable) {
        Library library = getLibraryByName(libraryName);
        List<CheckableAmount> checkableAmounts = library.getCheckables();
        for (CheckableAmount checkableAmount : checkableAmounts) {
            if (checkableAmount.getCheckable() == checkable) {
                return checkableAmount;
            }
        }

        return new CheckableAmount(checkable, 0);
    }

    public List<LibraryAvailableCheckouts> getLibrariesWithAvailableCheckout(String isbn) {
        List<Library> libraries = getLibraries();
        List<LibraryAvailableCheckouts> available = new ArrayList<>();
        Checkable checkable = checkableService.getByIsbn(isbn);
        for (Library library : libraries) {
            System.out.println(library.getName());
            CheckableAmount checkableAmount = getCheckableAmount(library.getName(), checkable);
            int amountAvailable = checkableAmount.getAmount();
            if (amountAvailable != 0) {
                available.add(new LibraryAvailableCheckouts(amountAvailable, library.getName()));
            }
        }

        return available;
    }

    public List<OverdueCheckout> getOverdueCheckouts(String libraryName) {
        System.out.println(libraryRepository);
        Library library = getLibraryByName(libraryName);
        Set<LibraryCard> libraryCards = library.getLibraryCards();
        List<OverdueCheckout> overdueCheckouts = new ArrayList<>();
        for (LibraryCard libraryCard : libraryCards) {
            List<Checkout> checkouts = libraryCard.getCheckouts();
            Patron patron = libraryCard.getPatron();
            for (Checkout checkout : checkouts) {
                if (checkout.getDueDate().isBefore(LocalDateTime.now())) {
                    overdueCheckouts.add(new OverdueCheckout(patron, checkout));
                }
            }
        }


        return overdueCheckouts;
    }
}