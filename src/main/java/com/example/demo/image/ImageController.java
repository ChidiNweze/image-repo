package com.example.demo.image;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/image")
public class ImageController {
    @GetMapping
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
}
