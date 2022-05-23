package request;

import com.fasterxml.jackson.annotation.JsonProperty;
import core.Author;
import core.Book;

import javax.annotation.Nullable;
import java.util.Optional;

public class BookRequest {
    @JsonProperty("bookName")
    @Nullable
    public String bookName;

    public Optional<Book> build(){
        if(bookName == null)
            return Optional.empty();
        Book book = new Book();
        book.setBookName(bookName);
        return Optional.ofNullable(book);
    }
}
