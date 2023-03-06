package com.example.demo.dao;


import com.example.demo.models.userDetails;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface userDetailsRestRepository extends PagingAndSortingRepository<userDetails, Long> {
    List<userDetails> findByRole(String role);
}
