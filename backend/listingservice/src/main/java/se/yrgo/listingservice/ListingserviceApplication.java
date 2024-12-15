package se.yrgo.listingservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Listing Service API",
                version = "v1",
                description = "API for Listing service, that efficiently fetches and lists ads for display."
        )
)
public class ListingserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ListingserviceApplication.class, args);
    }

}
