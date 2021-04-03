package com.example.artworkadmin.controller;

import com.example.artworkadmin.model.Artwork;
import com.example.artworkadmin.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArtworkController {
    @Autowired
    private ArtworkService artworkService;

    @GetMapping("/api/v1.0/healthcheck")
    public String healthCheck() {
        return "Healthy";
    }

    @GetMapping("/api/v1.0/artworks")
    public List<Artwork> allUsers() {
        return artworkService.findAll();
    }

    @GetMapping("/api/v1.0/artworks/{id}")
    public Artwork getArtworkById(@PathVariable Long id) {
        return artworkService.findById(id);
    }

    @PostMapping("/api/v1.0/artworks")
    public Artwork saveArtwork(@RequestBody Artwork artwork) {
        return artworkService.save(artwork);
    }

    @GetMapping("/api/v1.0/artworks/search")
    public Artwork getArtworkByName(@RequestParam String name) {
        return artworkService.findByName(name);
    }

    @DeleteMapping("/api/v1.0/artworks/{id}")
    public Long deleteArtworkById(@PathVariable Long id) {
        artworkService.deleteById(id);
        return id;
    }
}
