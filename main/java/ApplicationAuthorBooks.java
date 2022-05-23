import core.Author;
import core.Book;
import database.AuthorDAO;
import database.BookDAO;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.AuthorResources;
import resources.BookResources;

public class ApplicationAuthorBooks extends Application<ApplicationAuthorBooksConfiguration> {


    private final HibernateBundle<ApplicationAuthorBooksConfiguration> hibernateBundle = new HibernateBundle<ApplicationAuthorBooksConfiguration>
            (Author.class, Book.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ApplicationAuthorBooksConfiguration configuration){
            return configuration.getDataSourceFactory();
        }

    };

    public static void main(String args[])throws Exception{
        new ApplicationAuthorBooks().run(args);

    }

    public void initialize(Bootstrap<ApplicationAuthorBooksConfiguration> bootstrap){
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(ApplicationAuthorBooksConfiguration configuration , Environment environment){
        System.out.println("======== inside the run function ============");
        final AuthorDAO authorDAO = new AuthorDAO(hibernateBundle.getSessionFactory());
        final BookDAO bookDAO = new BookDAO((hibernateBundle.getSessionFactory()));

        environment.jersey().register(new AuthorResources(authorDAO));
        environment.jersey().register(new BookResources(bookDAO));
    }
}
