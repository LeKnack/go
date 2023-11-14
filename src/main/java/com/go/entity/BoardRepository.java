package com.go.entity;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "boards", path = "boards")
public interface BoardRepository extends  MongoRepository<Board, String>{
    
}
