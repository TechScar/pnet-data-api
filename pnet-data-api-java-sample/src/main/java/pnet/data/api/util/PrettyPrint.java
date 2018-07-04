package pnet.data.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import pnet.data.api.client.jackson.JacksonPnetDataApiModule;

public class PrettyPrint
{

    private static final ObjectMapper MAPPER = JacksonPnetDataApiModule.createObjectMapper();

    static
    {
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static String prettyPrint(Object o)
    {
        try
        {
            return MAPPER.writeValueAsString(o);
        }
        catch (JsonProcessingException e)
        {
            throw new IllegalArgumentException("Failed to transform to JSON", e);
        }
    }
}
