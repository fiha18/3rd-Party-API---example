package project.Mphasis.Api.bookAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookDto
{
    private Integer bookID;
    private String title;
    private String authors;
    private String average_rating;
    private String isbn;
    private String language_code;
    private String ratings_count;
    private Integer price;
}