package com.example.demo.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    public void create(Image image) {
        Optional<Image> imageOptional = imageRepository.findByTitle(image.getTitle());
        if (imageOptional.isPresent()) {
            throw new IllegalStateException("Title taken. Rename.");
        }
        System.out.println(image);
        imageRepository.save(image);
    }

    public void delete(Long imageId) {
        if(imageRepository.existsById(imageId)) {
            imageRepository.deleteById(imageId);
        } else {
            throw new IllegalStateException("Image with Id "+ imageId + " does not exist");
        }
    }

    @Transactional
    public void update(Long imageId, String newDescription) {
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new IllegalStateException("Image with Id "+ imageId + " does not exist"));
        if(!Objects.equals(newDescription, image.getDescription())) { //check if it is actually new
            image.setDescription(newDescription);
        }
        imageRepository.save(image);
    }

    //Other Services to make:
    /*
SEARCH function
from characteristics of the images (tags)
from text

ADD image(s) to the repository
one / bulk / enormous amount of images

DELETE image(s)
one / bulk / selected / all images
*/
}
