package com.example.demo.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getImages() {
        return imageRepository.findAll();
    }

    public void createImage(Image image) {
        Optional<Image> imageOptional = imageRepository.findByTitle(image.getTitle());
        if (imageOptional.isPresent()) {
            throw new IllegalStateException("Title taken. Rename.");
        }
        System.out.println(image);
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
