package careerdevs.cohort14.JsonPlaceholderAPI.Controllers;

import careerdevs.cohort14.JsonPlaceholderAPI.Models.Photos.Photo;
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
    public String testing() {
        return "Testing one two, one two!";
    }

    @GetMapping("/")
    public Photo[] allPhotos(RestTemplate restTemplate) {

        Photo[] response = restTemplate.getForObject(photoURL, Photo[].class);
        return response;
    }

    @GetMapping("/{id}")
    public Photo[] getCurtainPhoto(RestTemplate restTemplate, @PathVariable Long id) {
        try {
            return restTemplate.getForObject(photoURL + "?id=" + id, Photo[].class);
        } catch (HttpClientErrorException.NotFound exception) {
            System.out.println("User not found! Please try again");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/{first}/{last}")
    public ArrayList<Photo[]> getRangeOfPhotos(RestTemplate restTemplate, @PathVariable Long first, @PathVariable Long last) {
        try {
            ArrayList<Photo[]> arr = new ArrayList<>();
            for (Long i = first; i <= last; i++) {
                arr.add(restTemplate.getForObject(photoURL + "?id=" + i, Photo[].class));
            }
            return arr;
        } catch (HttpClientErrorException.NotFound exception) {
            System.out.println("Out of range! Please try again with a new set of ids");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
