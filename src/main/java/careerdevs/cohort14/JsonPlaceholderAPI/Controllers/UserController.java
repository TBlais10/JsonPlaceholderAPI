package careerdevs.cohort14.JsonPlaceholderAPI.Controllers;

import careerdevs.cohort14.JsonPlaceholderAPI.Models.Photos.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class UserController {

    public String userURL = "https://jsonplaceholder.typicode.com/users";

    @GetMapping("/test")
    public String test() {
        return "Check one, two! HEY HEY! Happy Thanksgiving";
    }

    @GetMapping("/")
    public careerdevs.cohort14.JsonPlaceholderAPI.Models.User.User[] allUsers(RestTemplate restTemplate) {

        return restTemplate.getForObject(userURL, careerdevs.cohort14.JsonPlaceholderAPI.Models.User.User[].class);
    }

    @GetMapping("/{id}")
    public careerdevs.cohort14.JsonPlaceholderAPI.Models.User.User[] specificUser(RestTemplate restTemplate, @PathVariable Long id) {
        String newURL = userURL + "?id=" + id;

        try {
            return restTemplate.getForObject(newURL, careerdevs.cohort14.JsonPlaceholderAPI.Models.User.User[].class);
        } catch (HttpClientErrorException.NotFound exception) {
            System.out.println("User not found! Please try again");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @GetMapping("/{first}/{last}")
    public ArrayList<User[]> getRangeOfPhotos(RestTemplate restTemplate, @PathVariable Long first, @PathVariable Long last){
        //TODO: add error handling
        ArrayList<User[]> arr = new ArrayList<>();
        for (Long i = first; i <= last; i++) {
            arr.add(restTemplate.getForObject(userURL+"?id=" + i, User[].class));
        }
        return arr;
    }

}
