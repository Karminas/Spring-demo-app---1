package com.example.demo.dao;


import com.example.demo.models.userDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface userDetailsRepository extends JpaRepository <userDetails, Long>  {
    List<userDetails> findByRole(String role);
}
