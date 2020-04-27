package dev.ericrybarczyk.spring5webapp.bootstrap;

import dev.ericrybarczyk.spring5webapp.domain.Author;
import dev.ericrybarczyk.spring5webapp.domain.Book;
import dev.ericrybarczyk.spring5webapp.domain.Publisher;
import dev.ericrybarczyk.spring5webapp.repositories.AuthorRepository;
import dev.ericrybarczyk.spring5webapp.repositories.BookRepository;
import dev.ericrybarczyk.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher techPublisher = new Publisher("Tech Publisher", "5150 Red Pill Drive", "Matrixville", "IN", "OU812");
        publisherRepository.save(techPublisher);

        Author joshuaBloch = new Author("Joshua", "Bloch");
        Book effectiveJava = new Book("Effective Java", "9780134686097");
        effectiveJava.setPublisher(techPublisher);
        joshuaBloch.getBooks().add(effectiveJava);
        effectiveJava.getAuthors().add(joshuaBloch);

        authorRepository.save(joshuaBloch);
        bookRepository.save(effectiveJava);

        Author samNewman = new Author("Sam", "Newman");
        Book buildingMicroservices = new Book("Building Microservices", "0636920033158");
        buildingMicroservices.setPublisher(techPublisher);
        samNewman.getBooks().add(buildingMicroservices);
        buildingMicroservices.getAuthors().add(samNewman);

        techPublisher.getBooks().add(effectiveJava);
        techPublisher.getBooks().add(buildingMicroservices);

        authorRepository.save(samNewman);
        bookRepository.save(buildingMicroservices);

        System.out.println("Bootstrap Data Complete");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println(techPublisher.getName() + " number of books: " + techPublisher.getBooks().size());

    }
}
