package se.yrgo.adservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.yrgo.adservice.domain.AdCategory;
import se.yrgo.adservice.service.AdCategoryService;

@SpringBootApplication
public class AdserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdserviceApplication.class, args);

    }
}
