package pl.jasmic.photomanager.photo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @RequestMapping("/")
    public String allPhotos(Model model) {
        List<Photo> all = photoService.findAll();
        model.addAttribute("photos", all);
        return "gallery";
    }

    @PostMapping("/add")
    public String addPhoto(@RequestParam String name, @RequestParam MultipartFile file) {
        Photo photo = new Photo();
        photo.setName(name);
        photo.setPath(file.getOriginalFilename());
        photo.prePersist();
        photoService.add(photo);
        return "redirect:/";
    }

    @RequestMapping("/del/{id}")
    public String delPhoto(@PathVariable Long id) {
        Optional<Photo> byId = photoService.findById(id);
        byId.ifPresent(photoService::delete);
        return "redirect:/";
    }
}
