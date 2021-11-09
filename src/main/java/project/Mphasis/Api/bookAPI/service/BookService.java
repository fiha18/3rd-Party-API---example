package project.Mphasis.Api.bookAPI.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.Mphasis.Api.bookAPI.dto.BookDto;
import project.Mphasis.Api.bookAPI.entity.Book;
import project.Mphasis.Api.bookAPI.repository.BookRepository;
import project.Mphasis.Api.bookAPI.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {

    @Autowired
    RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(BookService.class);
    List<BookDto> newBooks = new ArrayList<>();
    @Autowired
    private BookRepository repository;
    public List<BookDto> fetchBooks()
    {
        String url = "https://s3-ap-southeast-1.amazonaws.com/he-public-data/books8f8fe52.json";
        String result = restTemplate.getForObject(url,String.class);
        result = "{" +"\"books\":"+ result + "}";
        //String result = output.replace("[", "{").replace("]", "}");
        //result = result.replaceAll("<.*?>|\u00A0", "");

        Integer bookID = null;
        String title = null;
        String authors = null;
        String average_rating = null;
        String isbn = null;
        String language_code = null;
        String ratings_count = null;
        Integer price = null;

        JSONObject root = new JSONObject(result);
        JSONArray booksObject = root.getJSONArray("books");

        for (int i = 0; i < booksObject.length(); i++)
        {
            JSONObject arrayElement = booksObject.getJSONObject(i);
            if (!arrayElement.isNull("bookID")) {
                bookID = arrayElement.getInt("bookID");
            }else{
                bookID = null;
            }
            if (!arrayElement.isNull("title")) {
                title = arrayElement.get("title").toString();
            }else{
                title = null;
            }
            if (!arrayElement.isNull("authors")) {
                authors = arrayElement.get("authors").toString();
            }else{
                authors = null;
            }
            if (!arrayElement.isNull("average_rating")) {
                average_rating = arrayElement.get("average_rating").toString();
            }else{
                average_rating = null;
            }
            if (!arrayElement.isNull("isbn")) {
                isbn = arrayElement.get("isbn").toString();
            }else{
                isbn = null;
            }
            if (!arrayElement.isNull("language_code")) {
                language_code = arrayElement.get("language_code").toString();
            }else{
                language_code = null;
            }
            if (!arrayElement.isNull("ratings_count")) {
                ratings_count = arrayElement.get("ratings_count").toString();
            }else{
                ratings_count = null;
            }
            if (!arrayElement.isNull("price")) {
                price = arrayElement.getInt("price");
            }else{
                price = null;
            }
            BookDto book = new BookDto();
            book.setBookID(bookID);
            book.setTitle(title);
            book.setAuthors(authors);
            book.setAverage_rating(average_rating);
            book.setIsbn(isbn);
            book.setLanguage_code(language_code);
            book.setRatings_count(ratings_count);
            book.setPrice(price);
            newBooks.add(book);
        }
        return newBooks;
    }

    public List<BookDto> searchBooks(String content)
    {
        List<BookDto> searchList = new ArrayList<>();
        //List<String> titles = new ArrayList<>();
        newBooks.forEach(b -> {
            if((b.getTitle().contains(" "+content+" ") || b.getTitle().contains(content+" ")) && content.length() >=3)
            {
                searchList.add(b);
            }
        });
       return searchList;
    }
    public List<BookDto> getBooks()
    {
        return repository.findAll().stream().map(AppUtils::entityToDto).collect(Collectors.toList());
        //return repository.findAll().stream().map(AppUtils::entityToDto).collect(Collectors.toList());
    }
    public  BookDto getBook(Integer id)
    {
        if (repository.findById(id).get() != null) {
            return repository.findById(id).map(AppUtils::entityToDto).get();
        }
        else
            logger.info("Not Book with id " + id);
        return null;
    }

    public List<Book> saveBooks()
    {
        return repository.saveAll(newBooks.stream().map(AppUtils::dtoToEntity).collect(Collectors.toList()));
        // Book book = new Book(bookDto.getBookId(),bookDto.getTitle(),bookDto.getAuthors(),bookDto.getAverage_rating(),bookDto.getIsbn(),bookDto.getLanguage_code(),bookDto.getRatings_count(),bookDto.getPrice());
        //return  repository.save(book);
    }


}
