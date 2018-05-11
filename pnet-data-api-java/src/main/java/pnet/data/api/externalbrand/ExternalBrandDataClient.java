package pnet.data.api.externalbrand;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataApiException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.GetByMatchcode;
import pnet.data.api.util.Pair;

/**
<<<<<<< Updated upstream
 * Client for {@link ExternalBrandDataDTO}s.
=======
 * @author cet
 *
>>>>>>> Stashed changes
 */
@Service
public class ExternalBrandDataClient extends AbstractPnetDataApiClient<ExternalBrandDataClient>
    implements GetByMatchcode<ExternalBrandDataDTO>
{

    @Autowired
    public ExternalBrandDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    public PnetDataClientResultPage<ExternalBrandDataDTO> getAllByMatchcodes(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<ExternalBrandDataDTO> resultPage = createRestCall() //
            .parameters("mc", matchcodes)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/externalbrands/details",
                new GenericType.Of<DefaultPnetDataClientResultPage<ExternalBrandDataDTO>>()
                {
                });

        resultPage.setNextPageSupplier(() -> getAllByMatchcodes(matchcodes, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    public ExternalBrandDataSearch search()
    {
        return new ExternalBrandDataSearch(getMapper(), this::search, null);
    }

    protected PnetDataClientResultPage<ExternalBrandItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<ExternalBrandItemDTO> resultPage = createRestCall()
            .parameter("l", language)
            .parameter("q", query)
            .parameters(restricts)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/externalbrands/search",
                new GenericType.Of<DefaultPnetDataClientResultPage<ExternalBrandItemDTO>>()
                {
                });

        resultPage.setNextPageSupplier(() -> search(language, query, restricts, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    public ExternalBrandDataFind find()
    {
        return new ExternalBrandDataFind(getMapper(), this::find, null);
    }

    protected PnetDataClientResultPage<ExternalBrandItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<ExternalBrandItemDTO> resultPage = createRestCall()
            .parameters(restricts)
            .parameter("l", language)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/externalbrands/find",
                new GenericType.Of<DefaultPnetDataClientResultPage<ExternalBrandItemDTO>>()
                {
                });

        resultPage.setNextPageSupplier(() -> find(language, restricts, pageIndex + 1, itemsPerPage));

        return resultPage;
    }
}