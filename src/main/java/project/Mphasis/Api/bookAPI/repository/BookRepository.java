package project.Mphasis.Api.bookAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import project.Mphasis.Api.bookAPI.entity.Book;

@Repository


public interface BookRepository extends MongoRepository<Book,Integer> {
}
