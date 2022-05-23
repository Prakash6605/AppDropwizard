package core;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="author")
@NamedQueries({
        @NamedQuery(name="core.Author.findAll",query = "select a from Author a")
})
public class Author {
// Author can have multiple books (A -> B)
    private long authorId;
    private String firstName , lastName;
    private Set<Book> authorBooks = new HashSet<Book>(0);

    public Author(){}

    public Author(String firstName , String lastName , Set<Book> authorBooks){
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorBooks = authorBooks ;
    }

    @Id
    @GeneratedValue
    @Column(name="author_id")
    public long getAuthorId(){
        return this.authorId;
    }

    public void setAuthorId(long authorId){
        this.authorId = authorId;
    }

    @Column(name="first_name" , nullable = false )
    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    @Column(name="last_name" , nullable = false )
    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="author_book" , joinColumns = {@JoinColumn(name="author_id")} , inverseJoinColumns = {@JoinColumn(name="book_id")})
    public Set<Book> getAuthorBooks(){
        return this.authorBooks;
    }

    public void setAuthorBooks(Set<Book> authorBooks){
        this.authorBooks = authorBooks;
    }

}
