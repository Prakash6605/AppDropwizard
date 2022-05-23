package database;

import core.Author;
import core.Book;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class AuthorDAO extends AbstractDAO<Author> {

    public AuthorDAO(SessionFactory factory){
        super(factory);
    }

    public List<Author> findAll(){
        return list((Query<Author>) namedQuery("core.Author.findAll"));
    }

    public Author create(Author author){
        return persist(author);
    }

    // finding the author by author id
    public Optional<Author> findById(long id){
        return Optional.ofNullable(get(id));
    }


    public Author addBook(Author author , Book book){
        Optional<Book> bookIsPresent = Optional.ofNullable(currentSession().get(Book.class,book.getBookId()));
        if(!bookIsPresent.isPresent()){
            currentSession().save(book);
        }
        author.getAuthorBooks().add(book);
        currentSession().update(author);
        return author;
    }
}
