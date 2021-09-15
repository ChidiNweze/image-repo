package com.example.demo.image;

import java.time.LocalDate;
import java.util.List;

//It's been a while since I worked with Firebase or blob data, so I chose to abstract away that aspect
public class Image {
    private Long id;
    private String title;
    private String description;
    private List<String> tags;

    public Image() {

    }

    public Image(Long id,
                 String title,
                 String description,
                 List<String> tags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                '}';
    }
}
