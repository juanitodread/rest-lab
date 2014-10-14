/**
 * 
 */
package org.juanitodread.restlab.model.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.juanitodread.restlab.model.domain.Post;

/**
 * This is a singleton Memory Dao
 * 
 * http://embarcaderos.net/2009/06/23/the-singleton-pattern-in-java-multi-threaded-applications/
 * 
 * @author juansand
 *
 */
public class MemoryPostDao implements PostDao {

    private Map<String, Post> posts;
    
    private static class MemoryPostDaoHolder {
        public static MemoryPostDao instance = new MemoryPostDao();
    }

    private MemoryPostDao() {
        this.posts = Collections.unmodifiableMap(new HashMap<String, Post>());
    }
    
    public static MemoryPostDao getInstance() {
        return MemoryPostDaoHolder.instance;
    }

    @Override
    public synchronized Post save(Post post) {
        Map<String, Post> modifiablePosts = new HashMap<String, Post>(this.posts);
        modifiablePosts.put(post.getId().toString(), post);
        this.posts = Collections.unmodifiableMap(modifiablePosts);

        return post;
    }

    @Override
    public synchronized void delete(String id) {
        if (posts.containsKey(id)) {
            Map<String, Post> modifiablePosts = new HashMap<String, Post>(
                    this.posts);
            modifiablePosts.remove(id.toString());
            this.posts = Collections.unmodifiableMap(modifiablePosts);
        }
    }

    @Override
    public Post findById(String id) {
        return posts.get(id.toString());
    }

    @Override
    public List<Post> findAll() {
        return Collections.unmodifiableList(new ArrayList<Post>(this.posts
                .values()));
    }
}
