package pl.jasmic.photomanager.photo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PhotoService {

    List<Photo> findAll();

    Optional<Photo> findById(Long id);

    void add(Photo photo);

    void delete(Photo photo);
}
