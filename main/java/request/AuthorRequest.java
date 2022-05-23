package request;

import com.fasterxml.jackson.annotation.JsonProperty;
import core.Author;
import org.checkerframework.checker.units.qual.A;

import javax.annotation.Nullable;
import java.util.Optional;

public class AuthorRequest {

    @JsonProperty("firstName")
    @Nullable
    public String firstName;

    @JsonProperty("lastName")
    @Nullable
    public String lastName;

    public Optional<Author> build(){
        if(firstName == null || lastName == null )
            return Optional.empty();
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        return Optional.ofNullable(author);
    }
}
