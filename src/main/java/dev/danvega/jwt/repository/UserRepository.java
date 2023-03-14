package dev.danvega.jwt.repository;

import dev.danvega.jwt.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
