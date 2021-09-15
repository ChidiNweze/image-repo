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
        return imageService.getAll();
    }

    @PostMapping
    public void create(@RequestBody Image image) { //should create dto, rather than using entity in production code
        imageService.create(image);
    }

    @PostMapping(path = "photoDump")
    public void createAll(@RequestBody List<Image> images) { //should create dto, rather than using entity in production code
        imageService.createMultiple(images);
    }

    @DeleteMapping(path = "{imageId}")
    public void delete(@PathVariable("imageId") Long imageId) {
        imageService.delete(imageId);
    }

    @PutMapping(path = "{imageId}")
    public void updateDescription(
            @PathVariable("imageId") Long imageId,
            @RequestParam(required = false) String description) {
        imageService.update(imageId, description);
    }

    @RequestMapping(value = "tag", method = RequestMethod.GET)
    public @ResponseBody List<Image> searchTag(@RequestParam("tag") String tag){
        return imageService.searchTag(tag);
    }

    @RequestMapping(value = "tags", method = RequestMethod.GET)
    public @ResponseBody List<Image> searchTag(@RequestParam("tags") List<String> tags){
        return imageService.searchMultipleTags(tags);
    }

}
