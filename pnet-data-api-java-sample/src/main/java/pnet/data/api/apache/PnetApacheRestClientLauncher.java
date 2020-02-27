package pnet.data.api.apache;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.PnetRestClient;
import pnet.data.api.client.MutablePnetDataClientPrefs;
import pnet.data.api.util.Prefs;

/**
 * Demonstrates the usage of the PNet Data API by using an Apache HttpClient an without Spring.
 *
 * @author HAM
 */
public final class PnetApacheRestClientLauncher
{

    private PnetApacheRestClientLauncher()
    {
        super();
    }

    public static void main(String[] args) throws PnetDataClientException
    {
        String url = Prefs.getUrl(Prefs.DEFAULT_KEY);
        String username = Prefs.getUsername(Prefs.DEFAULT_KEY);
        String password = Prefs.getPassword(Prefs.DEFAULT_KEY);
        ApacheClientFactory clientFactory = ApacheClientFactory.of(url, username, password).loggingToSlf4J();

        PnetRestClient client = new PnetRestClient((MutablePnetDataClientPrefs) clientFactory.getPrefs(),
            clientFactory.getAboutDataClient(), clientFactory.getActivityDataClient(),
            clientFactory.getAdvisorTypeDataClient(), clientFactory.getApplicationDataClient(),
            clientFactory.getBrandDataClient(), clientFactory.getCompanyDataClient(),
            clientFactory.getCompanyGroupDataClient(), clientFactory.getCompanyGroupTypeDataClient(),
            clientFactory.getCompanyNumberTypeDataClient(), clientFactory.getCompanyTypeDataClient(),
            clientFactory.getContractStateDataClient(), clientFactory.getContractTypeDataClient(),
            clientFactory.getExternalBrandDataClient(), clientFactory.getFunctionDataClient(),
            clientFactory.getLegalFormDataClient(), clientFactory.getNumberTypeDataClient(),
            clientFactory.getPersonDataClient(), clientFactory.getTodoGroupDataClient(), clientFactory.getRepository());

        client.consume();
    }

}
