/**
 * 
 */
package org.juanitodread.restlab.model.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Date;
import java.util.UUID;

import org.juanitodread.restlab.model.domain.Post;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author juansand
 *
 */
public class MemoryPostDaoTest {

    private static MemoryPostDao memoryDao;
    private static final int POSTS_CREATED = 3;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        memoryDao = MemoryPostDao.getInstance();//new MemoryPostDao(new HashMap<String, Post>());
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * Test method for
     * {@link org.juanitodread.restlab.model.dao.MemoryPostDao#save(org.juanitodread.restlab.model.domain.Post)}
     * .
     */
    @Test
    public final void testSave() {
        Post post = Post.newInstance(UUID.randomUUID(), "First post",
                "This is my first post :)", new Date(), "juanitodread");
        Post savedPost = memoryDao.save(post);

        assertTrue(post.toString().equals(savedPost.toString()));
    }

    /**
     * Test method for
     * {@link org.juanitodread.restlab.model.dao.MemoryPostDao#delete(java.util.UUID)}
     * .
     */
    @Test
    public final void testDelete() {
        String postId = "01614620-fa6b-4a3a-8a7d-a04ae27428ac";
        Post post = Post.newInstance(UUID.fromString(postId),
                "Post to be deleted :(",
                "This is my post that will be deleted", new Date(),
                "juanitodread");
        memoryDao.save(post);
        int size = memoryDao.findAll().size();

        memoryDao.delete(postId);
        int newSize = size - 1;
        assertTrue(newSize == memoryDao.findAll().size());
    }

    /**
     * Test method for
     * {@link org.juanitodread.restlab.model.dao.MemoryPostDao#findById(java.util.UUID)}
     * .
     */
    @Test
    public final void testFindById() {
        String id = "550e8400-e29b-41d4-a716-446655440000";
        Post myPost = Post.newInstance(UUID.fromString(id), "My lost post",
                "This is my lost post", new Date(), "juanitodread");
        memoryDao.save(myPost);
        Post postFound = memoryDao.findById(id);
        assertTrue(myPost.equals(postFound));
    }

    /**
     * Test method for
     * {@link org.juanitodread.restlab.model.dao.MemoryPostDao#findAll()}.
     */
    @Test
    public final void testFindAll() {
        for (int i = 1; i <= POSTS_CREATED; i++) {
            memoryDao
                    .save(Post.newInstance(UUID.randomUUID(), "Post " + i,
                            "This is my " + i + " post :)", new Date(),
                            "juanitodread"));
        }

        List<Post> allPosts = memoryDao.findAll();
        assertTrue("Three posts were created", allPosts.size() == POSTS_CREATED);
    }

}
