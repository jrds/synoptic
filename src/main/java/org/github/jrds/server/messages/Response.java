package org.github.jrds.server.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "_type")

@JsonSubTypes({
        @JsonSubTypes.Type(value = FailureMessage.class),
        @JsonSubTypes.Type(value = SuccessMessage.class)})

public abstract class Response extends Message
{

    public Response(String to, int id)
    {
        super(null, to, id);
    }

    @JsonIgnore
    public abstract boolean isSuccess();

    @JsonIgnore
    public boolean isFailure()
    {
        return !isSuccess();
    }

    public abstract FailureMessage asFailure();
}
