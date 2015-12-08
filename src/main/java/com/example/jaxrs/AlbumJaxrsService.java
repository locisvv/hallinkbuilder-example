package com.example.jaxrs;

/**
 * Created by vsabadosh on 17/11/15.
 */

import com.example.service.MusicRepositoryService;
import com.example.dto.Album;
import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.standard.StandardRepresentationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.*;

@Component
@Path("/albums")
public class AlbumJaxrsService {
    @Context
    private UriInfo uriInfo;
    @Autowired
    private MusicRepositoryService musicRepositoryService;

    public static final RepresentationFactory representationFactory = new StandardRepresentationFactory()
            .withFlag(RepresentationFactory.PRETTY_PRINT)
            .withFlag(RepresentationFactory.COALESCE_ARRAYS);

    public UriBuilder mkUri(final Class resourceClass) {
        return UriBuilder.fromResource(resourceClass).segment("rest");
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, RepresentationFactory.HAL_JSON, RepresentationFactory.HAL_XML})
    public Response getAllAlbums(@DefaultValue("false") @QueryParam("embedded") boolean embedded) {
        Collection<Album> albums = musicRepositoryService.getAllAlbums();
        List<Representation> resources = new ArrayList<>();
        for (Album album : albums) {
            resources.add(getAlbumResource(album, embedded));
        }
        return Response.ok(resources).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, RepresentationFactory.HAL_JSON, RepresentationFactory.HAL_XML})
    public Response getAlbum(@PathParam("id") String id, @DefaultValue("false") @QueryParam("embedded") boolean embedded) {
        return Response.ok(getAlbumResource(musicRepositoryService.getAlbum(id), embedded)).build();
    }

    private Representation getAlbumResource(Album album, boolean embedded) {
        URI self = uriInfo.getAbsolutePath().resolve(mkUri(AlbumJaxrsService.class).segment(album.getId()).build());
        Representation rep = representationFactory.newRepresentation(self);

        URI artist = uriInfo.getAbsolutePath().resolve(mkUri(ArtistJaxrsService.class).segment(album.getArtistId()).build());
        rep.withLink("artist", artist);
        rep.withBean(album);

        if (embedded) {
            Representation artistRep = representationFactory.newRepresentation(artist);
            artistRep.withBean(musicRepositoryService.getArtist(album.getArtistId()));
            rep.withRepresentation("artist", artistRep);
        }

        return rep;
    }
}
