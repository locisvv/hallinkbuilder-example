package com.example.service;

import com.example.dto.Album;
import com.example.dto.Artist;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vsabadosh on 17/11/15.
 */
@Service
public class MusicRepositoryService {

    private Map<String, Album> albums;
    private Map<String, Artist> artists;

    /**
     * Constructor populates the 'database' (i.e. Maps) of Artists and Albums.
     */
    public MusicRepositoryService() {

        albums = new HashMap<String, Album>();
        artists = new HashMap<String, Artist>();

        Artist artist1 = new Artist("1", "Opeth");
        Artist artist2 = new Artist("2", "Celtic Frost");
        artists.put(artist1.getId(), artist1);
        artists.put(artist2.getId(), artist2);

        Album album1 = new Album("1", "Heritage", "1", 2);
        Album album2 = new Album("2", "Deliverance", "1", 3);
        Album album3 = new Album("3", "Pale Communion", "1", 0);
        Album album4 = new Album("3", "Monotheist", "2", 1);
        albums.put(album1.getId(), album1);
        albums.put(album2.getId(), album2);
        albums.put(album3.getId(), album3);
        albums.put(album4.getId(), album4);
    }

    public void addAlbum(Album album) {
        albums.put(album.getId(), album);
    }
    public Collection<Album> getAllAlbums() {
        Map<String, Album> newAlbums = new HashMap<String, Album>();
        for (Album album : albums.values()) {
            newAlbums.put(album.getId(), album.copyAlbum());
        }
        return newAlbums.values();
    }

    public Album getAlbum(final String id) {
        Album album = albums.get(id);
        return album.copyAlbum();
    }

    public Artist getArtist(final String id) {
        return artists.get(id);
    }
}