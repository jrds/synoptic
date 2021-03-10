package org.github.jrds.server.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Instruction {

    private String title;
    private String body;
    private User author;


    @JsonCreator
    public Instruction(@JsonProperty("title") String title,
                       @JsonProperty("body") String body,
                       @JsonProperty("author") User author) {
        this.title = title;
        this.body = body;
        this.author = author;
    }


    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    
    void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Instruction other = (Instruction) obj;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }


    
}

//TODO - Should this be a class in lesson?