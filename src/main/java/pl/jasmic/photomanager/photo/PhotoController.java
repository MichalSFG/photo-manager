package pl.jasmic.photomanager.photo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/photo")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @RequestMapping("/gallery")
    public String allPhotos(Model model) {
        List<Photo> all = photoService.findAll();
        model.addAttribute("photos", all);
        return "gallery";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addPhoto() {
        Photo photo = new Photo();
        photo.setName("photo7");
        photo.setPath("/383133.jpg");
        photo.prePersist();
        photoService.add(photo);
        return "added";
    }
}
