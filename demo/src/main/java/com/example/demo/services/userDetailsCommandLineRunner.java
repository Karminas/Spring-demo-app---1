package com.example.demo.services;

import com.example.demo.dao.userDetailsRepository;
import com.example.demo.models.userDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class userDetailsCommandLineRunner implements CommandLineRunner {

    //Create instance of a logger using the Logger Factory class (Design pattern)
    private Logger logger = LoggerFactory.getLogger(getClass());

    //Create instance or userDetail repo using constructor injection.
    private userDetailsRepository repository;

    public userDetailsCommandLineRunner(userDetailsRepository repository) {
        this.repository = repository;
    }


    @Override
    public void run(String... args) throws Exception {
        logger.info(args.toString());

        //Add new user to the repository
        repository.save(new userDetails("Tupac", "Admin"));
        repository.save(new userDetails("Alejandro", "User"));

        //Print user details using logger
        for (userDetails user: repository.findAll()) {
            logger.info(user.toString());
        }

        //Find all users wit ha given role
        List<userDetails> foundUsersByRole = repository.findByRole("User");
        foundUsersByRole.forEach(userDetails -> logger.info(userDetails.toString()));

    }


}
