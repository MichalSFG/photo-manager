package pl.jasmic.photomanager.photo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PhotoService {

    List<Photo> findAll();

    void add(Photo photo);
}
