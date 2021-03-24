package pl.jasmic.photomanager.photo;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @ModelAttribute("photos")
    public List<Photo> allPhotos() {
        return photoService.findAll();
    }

    @RequestMapping("/")
    public String allPhotos(Model model) {
        List<Photo> all = photoService.findAll();
        model.addAttribute("photos", all);
        return "gallery";
    }

    @PostMapping("/add")
    public String addPhoto(@RequestParam String name, @RequestParam MultipartFile file, Model model) {
        if (file.isEmpty() || name.isEmpty()) {
            model.addAttribute("image", "You forgot to upload an image or add image name!");
            return "gallery";
        }

        String photoDir = "/home/michal/Downloads/photos/";
        String originalFileName = file.getOriginalFilename();
        assert originalFileName != null;
        File thumbnailFile = new File(originalFileName.substring(0, originalFileName.lastIndexOf('.'))
                + "-thumbnail.jpg");

        try {
            file.transferTo(new File(photoDir + originalFileName));
            Thumbnails.of(photoDir + originalFileName)
                    .size(160, 160)
                    .toFile(photoDir + thumbnailFile);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        Photo photo = new Photo();
        photo.setName(name);
        photo.setOriginal(originalFileName);
        photo.setThumbnail(thumbnailFile.getPath());
        photo.prePersist();
        photoService.add(photo);
        return "redirect:/";
    }

    @RequestMapping("/del/{id}")
    public String deletePhoto(@PathVariable Long id) {
        Optional<Photo> byId = photoService.findById(id);
        byId.ifPresent(photoService::delete);
        return "redirect:/";
    }
}
