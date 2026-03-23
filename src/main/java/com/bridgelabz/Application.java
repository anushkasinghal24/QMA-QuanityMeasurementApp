package com.bridgelabz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@SpringBootApplication
//public class Application {
//
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//}

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        System.out.println(new BCryptPasswordEncoder().encode("1234")); // 👈 yaha daal

        SpringApplication.run(Application.class, args);
    }
}