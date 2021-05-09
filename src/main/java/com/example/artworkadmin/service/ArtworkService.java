package com.example.artworkadmin.service;

import com.example.artworkadmin.model.Artwork;
import com.example.artworkadmin.repo.ArtworkRepository;

import com.example.artworkadmin.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ArtworkService {
    @Autowired
    private ArtworkRepository artworkRepository;
    @Autowired
    private Environment env;

    public List<Artwork> findAll() {
        Iterable<Artwork> it = artworkRepository.findAll();

        List<Artwork> artworks = new ArrayList<Artwork>();
        it.forEach(a -> artworks.add(a));

        return artworks;
    }

    public Artwork findById(final Long id) {
        Optional<Artwork> artworkOptional = artworkRepository.findById(id);
        return artworkOptional.isPresent() ? artworkOptional.get() : null;
    }

    public Artwork save(final Artwork artwork) {
        Artwork a = artworkRepository.save(artwork);
        String filename = env.getProperty("artwork.persist.location");
        try {
            FileWriter fw = new FileWriter(filename);
            FileUtil.exportToCsv(fw, this.findAll());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

    public Artwork findByName(final String name) {
        Iterable<Artwork> it = artworkRepository.findByArtTitle(name);
        Iterator<Artwork> iterator = it.iterator();

        return iterator.hasNext() ? iterator.next() : null;
    }

    public void deleteById(final Long id) {
        artworkRepository.deleteById(id);
    }

    public List<Artwork> findByKey(final String key, final String value, final Boolean filterImage) {
        Iterable<Artwork> it = artworkRepository.findAll();
        Iterator<Artwork> iterator = it.iterator();

        ArrayList<Artwork> result = new ArrayList<>();

        while(iterator.hasNext()) {
            Artwork artwork = iterator.next();
            if (filterImage && !"Yes".equalsIgnoreCase(artwork.getDisplayImage())) {
                continue;
            }

            String sourceValue = null;
            List<String> sourceListValue = null;
            switch (key) {
                case Artwork.ART_TITLE_KEY:
                    sourceValue = artwork.getArtTitle();
                    break;
                case Artwork.ARTIST_NAME_KEY:
                    sourceValue = artwork.getArtistName();
                    break;
                case Artwork.COLOR_KEY:
                    sourceListValue = artwork.getColorList();
                    break;
                case Artwork.MEDIUM_KEY:
                    sourceListValue = artwork.getMediumList();
                    break;
                case Artwork.CULTURE_KEY:
                    sourceListValue = artwork.getCultureList();
                    break;
                case Artwork.BRG_KEY:
                    sourceValue = artwork.getBrg();
                    break;
                case Artwork.CATEGORY_KEY:
                    sourceListValue = artwork.getCategorylist();
                    break;
                case Artwork.ACQUISTION_YEAR_KEY:
                    sourceValue = artwork.getAcquistionYear();
                    break;
                case Artwork.CREATION_YEAR_KEY:
                    sourceValue = artwork.getCreationYear();
                    break;
                case Artwork.ART_LOC_KEY:
                    sourceValue = artwork.getArtLoc();
                    break;
            }

            if (sourceValue != null && sourceValue.equalsIgnoreCase(value)) {
                result.add(artwork);
            }
            if (sourceListValue != null) {
                if (sourceListValue.stream().anyMatch(s -> s.equalsIgnoreCase(value))) {
                    result.add(artwork);
                }
            }
        }

        return result;
    }
}
