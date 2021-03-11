package org.github.jrds.server.messages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "_type")

@JsonSubTypes({
        @JsonSubTypes.Type(value = FailureMessage.class, name = "failure"),
        @JsonSubTypes.Type(value = SuccessMessage.class, name = "success")})


public class Response extends Message {

    public Response( String to, int id) {
        super(null, to, id);
    }

}
