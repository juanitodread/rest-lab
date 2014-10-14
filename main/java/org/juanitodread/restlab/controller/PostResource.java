/**
 * 
 */
package org.juanitodread.restlab.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.juanitodread.restlab.model.dao.MemoryPostDao;
import org.juanitodread.restlab.model.domain.Post;

/**
 * @author juansand
 *
 */
@Path("/posts")
public class PostResource {

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Post> getAllPosts() {
        final List<Post> posts = MemoryPostDao.getInstance().findAll();
        return posts;
    }
    
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Post getPost(@PathParam("id") String id) {
        final Post post = MemoryPostDao.getInstance().findById(id);
        
        if(post == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
        return post;
    }
}
