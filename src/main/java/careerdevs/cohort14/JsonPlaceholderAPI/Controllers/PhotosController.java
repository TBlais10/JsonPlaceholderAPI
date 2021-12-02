package careerdevs.cohort14.JsonPlaceholderAPI.Controllers;

import careerdevs.cohort14.JsonPlaceholderAPI.Models.Photos.Photo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
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
    public ArrayList<Photo> getRangeOfPhotos(RestTemplate restTemplate, @PathVariable Long first, @PathVariable Long last) {
        try {
            ArrayList<Photo> arr = new ArrayList<>();
            for (Long i = first; i <= last; i++) {
                arr.add(restTemplate.getForObject(photoURL + "/" + i, Photo.class));
            }
            return arr;
        } catch (HttpClientErrorException.NotFound exception) {
            System.out.println("Out of range! Please try again with a new set of ids");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping("/new")
    public Photo newPhoto (RestTemplate restTemplate, @RequestBody Photo photo){
        return restTemplate.postForObject(photoURL, photo, Photo.class);
    }

    @PutMapping("/{id}")
    public String updatePhoto(RestTemplate restTemplate, @PathVariable Long id, @RequestBody Photo photo){
        try {
            restTemplate.put(photoURL + "/" + id, photo);
            return "Updated user " + id;
        } catch (HttpClientErrorException.NotFound exception){
            return "User " + id + " not found. Please try again";
        } catch (Exception e){
            return e.getMessage();
        }
    }

    @DeleteMapping("/{id}")
    public String deletePhoto(RestTemplate restTemplate, @PathVariable Long id){
        try{
        restTemplate.delete(photoURL + "/" + id);
        return "Photo " + id + " deleted";
        } catch (HttpClientErrorException.NotFound exception){
            return  "Id does not exists. Please try again";
        } catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

}
