package core;

import javax.persistence.*;

@Entity
@Table(name="book")
@NamedQueries({
        @NamedQuery(name="core.Book.findAll",query = "select b from Book b")
})
public class Book {
// book_id , book_name
    private long bookId;
    private String bookName;

    public Book(){}

    public Book(long bookId , String bookName){
        this.bookId = bookId;
        this.bookName = bookName;
    }

    @Id
    @GeneratedValue
    @Column(name="book_id")
    public long getBookId(){
        return  this.bookId;
    }

    public void setBookId(long bookId){
        this.bookId = bookId;
    }

    @Column(name="book_name")
    public String getBookName(){
        return this.bookName;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }
}
