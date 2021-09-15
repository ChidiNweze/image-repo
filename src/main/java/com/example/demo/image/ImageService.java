package com.example.demo.image;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    public List<Image> getImages() {

        return List.of(
                new Image(
                        1L,
                        "My water bottle",
                        "red water bottle sitting on desk",
                        List.of("red","water","bottle","hydration")
                )
        );
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
