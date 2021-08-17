package com.example.codefellowship.repository;

import com.example.codefellowship.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Integer> {
}
