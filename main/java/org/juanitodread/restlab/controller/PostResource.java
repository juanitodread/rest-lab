/**
 * 
 */
package org.juanitodread.restlab.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.juanitodread.restlab.model.dao.MemoryPostDao;
import org.juanitodread.restlab.model.domain.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author juansand
 *
 */
@Path("/posts")
public class PostResource {

    private static final Logger log = LoggerFactory
            .getLogger(PostResource.class);

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Post> getAllPosts() {
        final String METHOD_NAME = "getAllPosts";
        log.info("Start method {}", METHOD_NAME);

        final List<Post> posts = MemoryPostDao.getInstance().findAll();

        log.info("End method {}", METHOD_NAME);
        return posts;
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Post getPost(@PathParam("id") final String id) {
        final String METHOD_NAME = "getPost";
        log.info("Start method {}", METHOD_NAME);

        final Post post = MemoryPostDao.getInstance().findById(id);

        if (post == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        log.info("End method {}", METHOD_NAME);
        return post;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Post createPost(final JSONObject jsonPost) {
        final String METHOD_NAME = "createPost";
        log.info("Start method {}", METHOD_NAME);

        Post post = null;
        try {
            post = Post.newInstance(UUID.randomUUID(),
                    jsonPost.getString("title"), jsonPost.getString("content"),
                    new Date(), jsonPost.getString("author"));
            post = MemoryPostDao.getInstance().save(post);
        } catch (JSONException e) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        log.info("End method {}", METHOD_NAME);
        return post;
    }

    @DELETE
    @Path("{id}")
    public void deletePost(@PathParam("id") final String id) {
        final String METHOD_NAME = "deletePost";
        log.info("Start method {}", METHOD_NAME);

        MemoryPostDao.getInstance().delete(id);

        log.info("End method {}", METHOD_NAME);
        throw new WebApplicationException(Response.Status.OK);
    }
}
