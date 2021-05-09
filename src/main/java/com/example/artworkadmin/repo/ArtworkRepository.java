package com.example.artworkadmin.repo;

import com.example.artworkadmin.model.Artwork;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "artwork", path = "artwork")
public interface ArtworkRepository extends CrudRepository<Artwork, Long> {
    List<Artwork> findByArtTitle(@Param("name") String name);
}
