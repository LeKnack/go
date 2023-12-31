package com.go.entity;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path="users")
public interface UserRepository extends MongoRepository<User, String>{

    List<User> findByUserName(@Param("name") String name);
}