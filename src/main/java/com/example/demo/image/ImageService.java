package com.example.demo.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
