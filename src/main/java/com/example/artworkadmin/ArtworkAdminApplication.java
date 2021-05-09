package com.example.artworkadmin;

import com.example.artworkadmin.model.Artwork;
import com.example.artworkadmin.repo.ArtworkRepository;
import com.example.artworkadmin.service.ArtworkService;
import com.example.artworkadmin.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class ArtworkAdminApplication implements CommandLineRunner{
    @Autowired
    private ArtworkService artworkService;
    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(ArtworkAdminApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }

    @Override
    public void run(String... args) throws Exception {
        String filename = env.getProperty("artwork.persist.location");
        try {
            InputStream inputStream = new FileInputStream(filename);
            List<Artwork> artworks = FileUtil.parseCsvFile(inputStream);
            artworks.forEach(artwork -> artworkService.save(artwork));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
