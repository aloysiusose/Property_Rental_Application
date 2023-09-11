package dev.aloysius.PropertyRentalApplication.Controllers;

import dev.aloysius.PropertyRentalApplication.Models.Products.Properties;
import dev.aloysius.PropertyRentalApplication.Services.PropertyService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/add-property")
    public void registerProperty(@RequestBody Properties properties){
        propertyService.addProperty(properties);

    }

    @GetMapping("/available-properties")
    public List<Properties> availableProperties(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return propertyService.availableProperties(page, size);
        //i will also need to run a script to insert properties into the db
    }
    @GetMapping("/all-properties")
    public Page<Properties> allProperties(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return propertyService.properties(page, size);
    }

    @GetMapping("/hello")
    public String hello(Authentication authentication){
        String name = authentication.getName();

        return "hello user with username %s, signing with %s".formatted(name, Authentication.class.getSimpleName());
    }
    @ExceptionHandler
    public ResponseEntity<?> handlePropertyException(Exception ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
