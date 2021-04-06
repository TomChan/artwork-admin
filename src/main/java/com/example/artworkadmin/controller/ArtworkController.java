package com.example.artworkadmin.controller;

import com.example.artworkadmin.exporter.ArtworkExcelExporter;
import com.example.artworkadmin.model.Artwork;
import com.example.artworkadmin.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/api/v1.0/artworks/searchType")
    public List<Artwork> getArtworkByKey(@RequestParam String type, @RequestParam String field) {
        return artworkService.findByKey(type.toLowerCase(), field);
    }

    @DeleteMapping("/api/v1.0/artworks/{id}")
    public Long deleteArtworkById(@PathVariable Long id) {
        artworkService.deleteById(id);
        return id;
    }

    @GetMapping("/api/v1.0/artworks/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = ((SimpleDateFormat) dateFormatter).format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Artwork> listUsers = artworkService.findAll();

        ArtworkExcelExporter excelExporter = new ArtworkExcelExporter(listUsers);

        excelExporter.export(response);
    }
}
