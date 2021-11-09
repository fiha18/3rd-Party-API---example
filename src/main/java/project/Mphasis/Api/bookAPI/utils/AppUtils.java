package project.Mphasis.Api.bookAPI.utils;

import org.springframework.beans.BeanUtils;
import project.Mphasis.Api.bookAPI.dto.BookDto;
import project.Mphasis.Api.bookAPI.entity.Book;

public class AppUtils {
    public static BookDto entityToDto(Book book)
    {
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book,bookDto);
        return bookDto;
    }

    public static Book dtoToEntity(BookDto bookDto)
    {
        Book book = new Book();
        BeanUtils.copyProperties(bookDto,book);
        return book;
    }
}
