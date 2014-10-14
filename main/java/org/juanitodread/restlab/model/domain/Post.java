/**
 * 
 */
package org.juanitodread.restlab.model.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author juansand
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Post implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1890149306174929182L;

    @XmlElement
    private final UUID id;
    
    @XmlElement
    private final String title;
    
    @XmlElement
    private final String content;
    
    @XmlElement
    private final Date date;
    
    @XmlElement
    private final String author;
    
    // this constructor is necessary for JAXB
    private Post(){
        this(null, null, null, null, null);
    }

    // private constructor, use factory method instead
    private Post(UUID id, String title, String content, Date date, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
    }

    public static Post newInstance(UUID id, String title, String content,
            Date date, String author) {
        return new Post(id, title, content, date, author);
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String
                .format("{\"class\":\"%s\",\"id\":\"%s\",\"title\":\"%s\",\"content\":\"%s\",\"date\":\"%tc\",\"author\":\"%s\"}",
                        this.getClass().getName(), this.id.toString(),
                        this.title, this.content, this.date, this.author);
    }
}
