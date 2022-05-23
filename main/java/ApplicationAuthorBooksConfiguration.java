import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class ApplicationAuthorBooksConfiguration extends Configuration {
    private final DataSourceFactory dataSourceFactory = new DataSourceFactory();

    // connection to the databse is done here
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory(){
        return dataSourceFactory;
    }

}
