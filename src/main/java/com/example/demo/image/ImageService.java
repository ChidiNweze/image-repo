package com.example.demo.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        imageRepository.save(image);
    }

    public void createMultiple(List<Image> images) {
        for (Image image : images) {
            Optional<Image> imageOptional = imageRepository.findByTitle(image.getTitle());
            if (imageOptional.isPresent()) {
                throw new IllegalStateException("Title "+ image.getTitle()+ " taken. Rename.");
            }
            imageRepository.save(image);
        }
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

    public List<Image> searchTag(String tag) {
        List<Image> matchingImages = new ArrayList<>();
        List<Image> allImages = imageRepository.findAll();

        for(Image image : allImages) {
            if(image.getTags().contains(tag)) {
                matchingImages.add(image);
            }
        }

        return matchingImages;
    }

    public List<Image> searchMultipleTags(List<String> tags) {
        List<Image> matchingImages = new ArrayList<>();
        List<Image> allImages = imageRepository.findAll();

        if (!allImages.isEmpty() && !tags.isEmpty()) {
            for(String tag : tags) { //nested for-loop could be optimized...
                for(Image image : allImages) {
                    if(image.getTags().contains(tag) && !matchingImages.contains(image)) {
                        matchingImages.add(image);
                    }
                }
            }
        }

        return matchingImages;
    }
}
