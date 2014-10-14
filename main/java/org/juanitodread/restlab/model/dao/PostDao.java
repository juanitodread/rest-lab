/**
 * 
 */
package org.juanitodread.restlab.model.dao;

import java.util.List;

import org.juanitodread.restlab.model.domain.Post;

/**
 * @author juansand
 *
 */
public interface PostDao {
    
    public Post save(Post post);
    
    public void delete(String id);
    
    public Post findById(String id);
    
    public List<Post> findAll();
}
