package guru.springframework.spring5webapp.bootstrap;

import ch.qos.logback.core.net.SyslogOutputStream;
import guru.springframework.spring5webapp.model.Address;
import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AddressRepository;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authRepo;
    private final BookRepository bookRepo;
    private final PublisherRepository publisherRepo;
    private final AddressRepository addressRepo;

    public BootStrapData(AuthorRepository authRepo, BookRepository bookRepo, PublisherRepository publisherRepo, AddressRepository addressRepo) {
        this.authRepo = authRepo;
        this.bookRepo = bookRepo;
        this.publisherRepo = publisherRepo;
        this.addressRepo = addressRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Address address1 = new Address("address1", "street1", "Sandweiler", "Luxembourg", 1234l);
        Address address2 = new Address("address2", "street2", "London", "UK", 5678l);
        addressRepo.save(address1);
        addressRepo.save(address2);

        Publisher pub1 = new Publisher("publisher1", address1);
        Publisher pub2 = new Publisher("publisher2", address2);

        publisherRepo.save(pub1);
        publisherRepo.save(pub2);

        Author eric = new Author("Eric", "Evans");
        Book book1 = new Book("Domani Driven Design", "123123");
        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);
        book1.setPublisher(pub1);
        pub1.getBooks().add(book1);
        authRepo.save(eric);
        bookRepo.save(book1);
        publisherRepo.save(pub1);



        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2ee Developement without EJB", "3535");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(pub2);
        pub2.getBooks().add(noEJB);

        authRepo.save(rod);
        bookRepo.save(noEJB);
        publisherRepo.save(pub2);

        System.out.println("Started in BootStrap");
        System.out.println("Number of Books:" + bookRepo.count());
        System.out.println("Number of Publishers:" + publisherRepo.count());
    }
}
