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
@RequestMapping("/api/photos")
public class PhotosController {

    public String photoURL = "https://jsonplaceholder.typicode.com/photos";

    @GetMapping("/test")
    public String testing(){
        return "Testing one two, one two!";
    }

    @GetMapping("/")
    public User[] allPhotos(RestTemplate restTemplate){

        User[] response = restTemplate.getForObject(photoURL, User[].class);
        return response;
    }

    @GetMapping("/{id}")
    public User[] getCurtainPhoto(RestTemplate restTemplate, @PathVariable Long id){
        try{
            return restTemplate.getForObject(photoURL +"?id=" + id, User[].class);
        } catch (HttpClientErrorException.NotFound exception) {
            System.out.println("User not found! Please try again");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/{first}/{last}")
    public ArrayList<User[]> getRangeOfPhotos(RestTemplate restTemplate, @PathVariable Long first, @PathVariable Long last){
        //TODO: add error handling
        ArrayList<User[]> arr = new ArrayList<>();
        for (Long i = first; i <= last; i++) {
            arr.add(restTemplate.getForObject(photoURL+"?id=" + i, User[].class));
        }
        return arr;
    }


}
