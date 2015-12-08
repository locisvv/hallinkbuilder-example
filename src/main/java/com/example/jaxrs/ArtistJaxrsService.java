package com.example.jaxrs;

import com.example.service.MusicRepositoryService;
import com.example.dto.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
* Created by vsabadosh on 17/11/15.
*/
@Component
@Path("/artist")
public class ArtistJaxrsService {
    @Autowired
    private MusicRepositoryService musicRepositoryService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getArtist(@PathParam("id") String id, @HeaderParam(HttpHeaders.ACCEPT) String accept) {
        Artist artist = musicRepositoryService.getArtist(id);

        return Response.ok(artist).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response putArtist(Artist artistResource) {
        return Response.status(201).entity(artistResource).build();
    }
}

