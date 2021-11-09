package project.Mphasis.Api.bookAPI.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Book")
public class Book
{
    @Id
    private Integer bookID;
    private String title;
    private String authors;
    private String average_rating;
    private String isbn;
    private String language_code;
    private String ratings_count;
    private Integer price;
}