package com.example.demo.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/img")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @GetMapping
    public List<Image> getImages() {
        return imageService.getImages();
    }

    @PostMapping
    public void create(@RequestBody Image image) { //should create dto
        imageService.createImage(image);
    }

    @DeleteMapping(path = "/{imageId}")
    public void delete(@PathVariable("imageId") Long imageId) {
        imageService.deleteImage(imageId);
    }

}
