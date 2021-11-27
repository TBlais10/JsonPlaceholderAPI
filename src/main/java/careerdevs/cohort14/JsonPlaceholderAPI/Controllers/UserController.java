package careerdevs.cohort14.JsonPlaceholderAPI.Controllers;

import careerdevs.cohort14.JsonPlaceholderAPI.Models.User.User;
import careerdevs.cohort14.JsonPlaceholderAPI.Models.User.UserCompany;
import careerdevs.cohort14.JsonPlaceholderAPI.Models.User.UserMulti;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

@RestController
@RequestMapping("/api/user")
public class UserController {

    public String userURL = "https://jsonplaceholder.typicode.com/users";

    @GetMapping("/test")
    public String test() {
        return "Check one, two! HEY HEY! Happy Thanksgiving";
    }

    @GetMapping("/")
    public UserMulti allUsers(RestTemplate restTemplate) {

        return restTemplate.getForObject(userURL, UserMulti.class);
    }

    @GetMapping("/{id}")
    public UserMulti specificUser(RestTemplate restTemplate, @PathVariable Long id) {
        String newURL = userURL + "?id=" + id;

        try {
            return restTemplate.getForObject(newURL, UserMulti.class);
        } catch (HttpClientErrorException.NotFound exception) {
            System.out.println("User not found! Please try again");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return restTemplate.getForObject(newURL, UserMulti.class);
    }

}
