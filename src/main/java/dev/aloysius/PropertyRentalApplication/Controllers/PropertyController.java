package dev.aloysius.PropertyRentalApplication.Controllers;

import dev.aloysius.PropertyRentalApplication.Models.Products.Properties;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    @PostMapping("/add-property")
    public void registerProperty(@RequestBody Properties properties){

    }

    @GetMapping("/")
    public List<Properties> allProperties(){
        return null;
        //this will be paginated;
        //i will also need to run a script to insert properties into the db
    }

    @GetMapping("/hello")
    public String hello(Authentication authentication){
        String name = authentication.getName();

        return "hello user with username %s, signing with %s".formatted(name, Authentication.class.getSimpleName());
    }
}
