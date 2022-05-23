package resources;

import core.Author;
import core.Book;
import database.BookDAO;
import io.dropwizard.hibernate.UnitOfWork;
import request.BookRequest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResources {

    private BookDAO bookDAO;

    public BookResources(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    @GET
    @UnitOfWork
    public Response findAll(){
        List<Book> books = bookDAO.findAll();
        System.out.println("========= books ======== " + books);
        return Response.accepted(books).status(200).build();
    }

    @POST
    @UnitOfWork
    public Response createBook(BookRequest bookRequest){
        Optional<Book> newBook = bookRequest.build();
        Book bookCreated = bookDAO.create(newBook.get());
        return Response.accepted(bookCreated).status(200).build();
    }
}
