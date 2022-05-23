package resources;

import core.Author;
import core.Book;
import database.AuthorDAO;
import database.BookDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.SessionFactory;
import request.AuthorRequest;
import sun.tools.jconsole.JConsole;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/author")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResources {

    private AuthorDAO authorDAO;

    public AuthorResources(AuthorDAO authorDAO){
        this.authorDAO = authorDAO;
    }

    @Path("/{id}")
    @GET
    @UnitOfWork
    public Response findById(@PathParam("id") long id){
        System.out.println("===== params ======= " + id);
        Optional<Author> author = authorDAO.findById(id);
        System.out.println("==== author =====" + author);
        if(author.isPresent()){
            return Response.accepted(author.get()).status(200).build();
        }else{
            return Response.notModified("No Such Author Exits").status(422).build();
        }
    }

    @GET
    @UnitOfWork
    public Response findAll(){
        System.out.println("======= comming here ======== " );
        List<Author> authors = authorDAO.findAll();
        return Response.accepted(authors).status(200).build();
    }

    @POST
    @UnitOfWork
    public Response createAuthor(AuthorRequest authorRequest){
        Optional<Author> newAuthor = authorRequest.build();
        Author authorCreated = authorDAO.create(newAuthor.get());
        return Response.accepted(authorCreated).status(200).build();
    }

    @Path("/{id}/add-book")
    @POST
    @UnitOfWork
    public Response addAuthor(@PathParam("id") long id , Book book){
        System.out.println(" =========== in this request ========= ");
        Optional<Author> author = authorDAO.findById(id);
        System.out.println(" =========== author ========= " + author);
        if(author.isPresent()){
            authorDAO.addBook(author.get(),book);
            return Response.accepted(author).status(200).build();
        }else{
            return Response.accepted("No such author").status(404).build();
        }
    }

    @Path("/{id}/get-books")
    @GET
    @UnitOfWork
    public Response getBooks(@PathParam("id") long id){
        Optional<Author> author = authorDAO.findById(id);
        if(author.isPresent()){
//            authorDAO.addBook(author.get(),book);
            return Response.accepted(author.get().getAuthorBooks()).status(200).build();
        }else{
            return Response.accepted("No such author").status(404).build();
        }
    }


}
