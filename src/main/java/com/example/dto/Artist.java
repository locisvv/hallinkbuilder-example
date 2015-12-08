package com.example.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by vsabadosh on 17/11/15.
 */

@XmlRootElement(name = "artist")
public class Artist {
    private String id;
    private String name;

    public Artist(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Artist() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
