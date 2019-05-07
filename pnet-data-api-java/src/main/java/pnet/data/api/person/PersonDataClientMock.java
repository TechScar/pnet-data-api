package pnet.data.api.person;

import static pnet.data.api.util.MockFilters.*;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPageWithAggregates;
import pnet.data.api.client.context.PnetDataApiContextMock;
import pnet.data.api.util.DataClientMock;
import pnet.data.api.util.ItemClientMock;
import pnet.data.api.util.MockStore;
import pnet.data.api.util.MockUtils;
import pnet.data.api.util.Pair;

/**
 * A mock, that can be used for testing with very basic filtering.
 *
 * @author HAM
 */
public class PersonDataClientMock extends PersonDataClient
    implements ItemClientMock<PersonItemDTO, PersonDataClientMock>, DataClientMock<PersonDataDTO, PersonDataClientMock>
{

    public PersonDataClientMock()
    {
        super(new PnetDataApiContextMock());

        MockStore<PersonItemDTO> itemStore = getItemStore();

        itemStore
            .addFilter("q", whenMatches(PersonItemDTO::getPersonId, PersonItemDTO::getFirstName,
                PersonItemDTO::getLastName, PersonItemDTO::getPersonnelNumber));
        itemStore.addFilter("id", whenEquals(PersonItemDTO::getPersonId));
        itemStore.addFilter("externalId", whenEquals(PersonItemDTO::getExternalId));
        itemStore.addFilter("guid", whenEquals(PersonItemDTO::getGuid));
        itemStore.addFilter("preferredUserId", whenEquals(PersonItemDTO::getPreferredUserId));
        itemStore.addFilter("email", whenEquals(PersonItemDTO::getEmail));
        itemStore.addFilter("personnelNumber", whenEquals(PersonItemDTO::getPersonnelNumber));
        itemStore
            .addFilter("companyId",
                withCollection(PersonItemDTO::getCompanies, whenEquals(ActivePersonCompanyLinkDTO::getCompanyId)));
        itemStore
            .addFilter("companyNumber",
                withCollection(PersonItemDTO::getCompanies, whenEquals(ActivePersonCompanyLinkDTO::getCompanyNumber)));
        itemStore
            .addFilter("company", withCollection(PersonItemDTO::getCompanies,
                whenEquals(ActivePersonCompanyLinkDTO::getCompanyMatchcode)));

        addDefaultTenantsFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);
        addDefaultScrollDummy(itemStore);

        MockStore<PersonDataDTO> dataStore = getDataStore();

        dataStore.addFilter("id", whenEquals(PersonDataDTO::getPersonId));
        dataStore.addFilter("externalId", whenEquals(PersonDataDTO::getExternalId));
        dataStore.addFilter("guid", whenEquals(PersonDataDTO::getGuid));
        dataStore.addFilter("preferredUserId", whenEquals(PersonDataDTO::getPreferredUserId));
        dataStore.addFilter("email", whenEquals(PersonDataDTO::getEmail));
        dataStore.addFilter("personnelNumber", whenEquals(PersonDataDTO::getPersonnelNumber));

        addDefaultTenantsFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<PersonDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        List<PersonDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(entries, pageIndex, itemsPerPage);
    }

    @Override
    protected PnetDataClientResultPage<PersonItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        List<PersonItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(entries, pageIndex, itemsPerPage);
    }

    @Override
    protected PnetDataClientResultPageWithAggregates<PersonItemDTO, PersonAggregatesDTO> search(Locale language,
        String query, List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        List<PersonItemDTO> entries = findItems(restricts);

        List<PersonTenantAggregateDTO> aggregatedTenants =
            MockUtils.aggregateTenants(entries, PersonTenantAggregateDTO::new);
        List<PersonCompanyAggregateDTO> aggregatedCompanies = MockUtils
            .aggregateFlat(entries,
                entry -> entry.getCompanies().stream().map(ActivePersonCompanyLinkDTO::getCompanyId),
                PersonCompanyAggregateDTO::new);

        PersonAggregatesDTO aggregates = new PersonAggregatesDTO(aggregatedTenants, aggregatedCompanies,
            Collections.emptyList(), Collections.emptyList());

        return MockUtils.mockResultPageWithAggregates(entries, aggregates, pageIndex, itemsPerPage);
    }
}