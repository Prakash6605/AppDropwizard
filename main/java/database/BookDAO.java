package database;

import core.Author;
import core.Book;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class BookDAO extends AbstractDAO<Book> {

    public BookDAO(SessionFactory factory){
        super(factory);
    }

    public List<Book> findAll(){
        return list((Query<Book>) namedQuery("core.Book.findAll"));
    }

    public Book create(Book book){
        return persist(book);
    }

    public Optional<Book> findById(long id){
        return Optional.ofNullable(get(id));
    }
}
