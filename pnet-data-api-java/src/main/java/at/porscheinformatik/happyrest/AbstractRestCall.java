package at.porscheinformatik.happyrest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import pnet.data.api.util.Pair;

/**
 * A REST call. Objects of this type must be final and thread-safe!
 *
 * @author ham
 */
public abstract class AbstractRestCall implements RestCall
{

    private final String url;
    private final List<String> acceptableMediaTypes;
    private final String contentType;
    private final List<RestAttribute> attributes;
    private final RestAttributeConverter converter;
    private final Object body;

    protected AbstractRestCall(String url, List<String> acceptableMediaTypes, String contentType,
        List<RestAttribute> attributes, RestAttributeConverter converter, Object body)
    {
        super();

        this.url = url;
        this.acceptableMediaTypes = acceptableMediaTypes;
        this.contentType = contentType;
        this.attributes = attributes;
        this.converter = converter;
        this.body = body;
    }

    protected abstract RestCall copy(String url, List<String> acceptableMediaTypes, String contentType,
        List<RestAttribute> attributes, RestAttributeConverter converter, Object body);

    @Override
    public String getUrl()
    {
        return url;
    }

    @Override
    public RestCall url(String url)
    {
        return copy(url, acceptableMediaTypes, contentType, attributes, converter, body);
    }

    @Override
    public RestCall path(String path)
    {
        return copy(prepareUrl(Objects.requireNonNull(url, "Cannot add path to missing URL"), path),
            acceptableMediaTypes, contentType, attributes, converter, body);
    }

    @Override
    public List<String> getAcceptableMediaTypes()
    {
        return acceptableMediaTypes;
    }

    @Override
    public RestCall accept(String... mediaTypes)
    {
        ArrayList<String> acceptableMediaTypes = new ArrayList<>(Arrays.asList(mediaTypes));

        if (this.acceptableMediaTypes != null)
        {
            acceptableMediaTypes.addAll(0, this.acceptableMediaTypes);
        }

        return copy(url, Collections.unmodifiableList(acceptableMediaTypes), contentType, attributes, converter, body);
    }

    @Override
    public List<RestAttribute> getAttributes()
    {
        return attributes;
    }

    @Override
    public RestCall header(String name, String... value)
    {
        if (value == null || value.length == 0)
        {
            return this;
        }

        return attribute(
            Arrays.stream(value).map($ -> RestAttribute.header(name, $)).toArray(size -> new RestAttribute[size]));
    }

    @Override
    public RestCall headers(String name, Collection<String> values)
    {
        if (values == null || values.isEmpty())
        {
            return this;
        }

        return attribute(
            values.stream().map($ -> RestAttribute.header(name, $)).toArray(size -> new RestAttribute[size]));
    }

    @Override
    public RestCall variable(String name, Object... value)
    {
        if (value == null || value.length == 0)
        {
            return this;
        }

        return attribute(
            Arrays.stream(value).map($ -> RestAttribute.variable(name, $)).toArray(size -> new RestAttribute[size]));
    }

    @Override
    public RestCall parameter(String name, Object... value)
    {
        if (value == null || value.length == 0)
        {
            return this;
        }

        return attribute(
            Arrays.stream(value).map($ -> RestAttribute.parameter(name, $)).toArray(size -> new RestAttribute[size]));
    }

    @Override
    public RestCall parameters(String name, Collection<?> values)
    {
        if (values == null || values.isEmpty())
        {
            return this;
        }

        return attribute(
            values.stream().map($ -> RestAttribute.parameter(name, $)).toArray(size -> new RestAttribute[size]));
    }

    @Override
    public RestCall parameters(Collection<? extends Pair<String, ?>> values)
    {
        if (values == null || values.isEmpty())
        {
            return this;
        }

        return attribute(values.stream().map($ -> RestAttribute.parameter($.getLeft(), $.getRight())).toArray(
            size -> new RestAttribute[size]));
    }

    protected RestCall attribute(RestAttribute... attributesToAdd)
    {
        ArrayList<RestAttribute> attributes = new ArrayList<>(Arrays.asList(attributesToAdd));

        if (this.attributes != null)
        {
            attributes.addAll(0, this.attributes);
        }

        return copy(url, acceptableMediaTypes, contentType, Collections.unmodifiableList(attributes), converter, body);
    }

    protected RestAttributeConverter getConverter()
    {
        return converter;
    }

    protected String convert(Object value)
    {
        try
        {
            return getConverter().convertAttributeToString(value);
        }
        catch (Exception | Error e)
        {
            throw new IllegalArgumentException("Failed to convert " + value + " to string", e);
        }
    }

    public RestCall converter(RestAttributeConverter converter)
    {
        return copy(url, acceptableMediaTypes, contentType, attributes, converter, body);
    }

    public String getContentType()
    {
        return contentType;
    }

    @Override
    public RestCall contentType(String contentType)
    {
        return copy(url, acceptableMediaTypes, contentType, attributes, converter, body);
    }

    public Object getBody()
    {
        return body;
    }

    @Override
    public RestCall body(Object body)
    {
        return copy(url, acceptableMediaTypes, contentType, attributes, converter, body);
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, Class<T> responseType) throws RestException
    {
        return invoke(method, null, responseType);
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, GenericType<T> responseType) throws RestException
    {
        return invoke(method, null, responseType);
    }

    @Override
    public abstract <T> RestResponse<T> invoke(RestMethod method, String path, Class<T> responseType)
        throws RestException;

    @Override
    public abstract <T> RestResponse<T> invoke(RestMethod method, String path, GenericType<T> responseType)
        throws RestException;

    protected static String prepareUrl(String url, String path)
    {
        if (path == null || path.length() == 0)
        {
            return url;
        }

        if (!url.endsWith("/"))
        {
            url += "/";
        }

        if (path != null)
        {
            if (path.startsWith("/"))
            {
                url += path.substring(1);
            }
            else
            {
                url += path;
            }
        }

        return url;
    }

}
