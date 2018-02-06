package at.porscheinformatik.happyrest.spring;

import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import org.springframework.http.ResponseEntity;

import at.porscheinformatik.happyrest.RestResponse;

class SpringRestResponse<T> implements RestResponse<T>
{

    private final ResponseEntity<T> response;

    SpringRestResponse(ResponseEntity<T> response)
    {
        super();

        this.response = response;
    }

    @Override
    public int getStatusCode()
    {
        return response.getStatusCodeValue();
    }

    @Override
    public String getStatus()
    {
        String status = String.valueOf(response.getStatusCodeValue());

        if (response.getStatusCode().getReasonPhrase() != null)
        {
            status += " " + response.getStatusCode().getReasonPhrase();
        }

        return status;
    }

    @Override
    public boolean isInformational()
    {
        return response.getStatusCode().is1xxInformational();
    }

    @Override
    public boolean isSuccessful()
    {
        return response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public boolean isRedirection()
    {
        return response.getStatusCode().is3xxRedirection();
    }

    @Override
    public boolean isError()
    {
        return response.getStatusCode().isError();
    }

    @Override
    public T getBody()
    {
        return response.getBody();
    }

    @Override
    public List<String> getHeader(Object key)
    {
        return response.getHeaders().get(key);
    }

    @Override
    public String getFirstHeader(Object key)
    {
        List<String> header = getHeader(key);

        return header.size() > 0 ? header.get(0) : null;
    }

    @Override
    public String getCacheControl()
    {
        return response.getHeaders().getCacheControl();
    }

    @Override
    public String getContentType()
    {
        return response.getHeaders().getContentType().toString();
    }

    @Override
    public Locale getContentLanguage()
    {
        return response.getHeaders().getContentLanguage();
    }

    @Override
    public long getContentLength()
    {
        return response.getHeaders().getContentLength();
    }

    @Override
    public long getCreationDate()
    {
        return response.getHeaders().getDate();
    }

    @Override
    public long getExpiresDate()
    {
        return response.getHeaders().getExpires();
    }

    @Override
    public long getLastModified()
    {
        return response.getHeaders().getLastModified();
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append(getStatus()).append("\n");

        for (Entry<String, List<String>> entry : response.getHeaders().entrySet())
        {
            for (String value : entry.getValue())
            {
                builder.append(entry.getKey()).append(": ").append(value).append("\n");
            }
        }

        return builder.toString();
    }
}