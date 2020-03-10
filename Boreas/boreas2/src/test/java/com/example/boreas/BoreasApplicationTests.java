package com.example.boreas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import java.io.File;

@SpringBootTest
public class BoreasApplicationTests {

    @Autowired
    Person person;

    @Test
    public void contextLoads() {

//        System.out.println(person.toString());
    }

}
