package com.example.demo.image;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ImageConfig {
    @Bean
    CommandLineRunner commandLineRunner(ImageRepository imageRepository) {
        return args -> {
            List<Image> testImages = List.of(
                    new Image(
                            1L,
                            "EMERGENCY: VERY CUTE PUPPY",
                            "Adorable puppy, minding its business, and saving lives in the process.",
                            List.of("cutie","wholesome","aww","fluffy","loml")
                    ),
                    new Image(
                            2L,
                            "Skepta, Drake and Travis Scott",
                            "The musicians everyone should see before they die.",
                            List.of("talent","legends","loml","2022", "vision board")
                    ),
                    new Image(
                    3L,
                    "Culinary perfection",
                    "A huge pizza box full of Irish nachos. Student pictured drooling above it stated, “Honestly, just pay me in Irish nachos.”",
                    List.of("fluffy","waffle fries","jalapeno","heaven", "dream job")
                )
            );
            imageRepository.saveAll(testImages);
        };
    }
}
