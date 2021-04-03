package com.example.artworkadmin.service;

import com.example.artworkadmin.model.Artwork;
import com.example.artworkadmin.repo.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ArtworkService {
    @Autowired
    private ArtworkRepository artworkRepository;

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
        return a;
    }

    public Artwork findByName(final String name) {
        Iterable<Artwork> it = artworkRepository.findByArtName(name);
        Iterator<Artwork> iterator = it.iterator();

        return iterator.hasNext() ? iterator.next() : null;
    }

    public void deleteById(final Long id) {
        artworkRepository.deleteById(id);
    }
}
