package pnet.data.api.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pnet.data.api.activity.ActivityPnetDataApiClientConfig;
import pnet.data.api.advisortype.AdvisorTypePnetDataApiClientConfig;
import pnet.data.api.brand.BrandPnetDataApiClientConfig;
import pnet.data.api.client.context.ContextPnetDataApiClientConfig;
import pnet.data.api.company.CompanyPnetDataApiClientConfig;
import pnet.data.api.companygrouptype.CompanyGroupTypePnetDataApiClientConfig;
import pnet.data.api.companytype.CompanyTypePnetDataApiClientConfig;
import pnet.data.api.contractstate.ContractStatePnetDataApiClientConfig;
import pnet.data.api.externalbrand.ExternalBrandPnetDataApiClientConfig;
import pnet.data.api.function.FunctionPnetDataApiClientConfig;
import pnet.data.api.numbertype.NumberTypePnetDataApiClientConfig;

/**
 * Spring configuration for the PnetDataApiClient module
 */
@Configuration
@Import({
    ActivityPnetDataApiClientConfig.class,
    AdvisorTypePnetDataApiClientConfig.class,
    BrandPnetDataApiClientConfig.class,
    CompanyPnetDataApiClientConfig.class,
    CompanyGroupTypePnetDataApiClientConfig.class,
    CompanyTypePnetDataApiClientConfig.class,
    ContextPnetDataApiClientConfig.class,
    ContractStatePnetDataApiClientConfig.class,
    ExternalBrandPnetDataApiClientConfig.class,
    FunctionPnetDataApiClientConfig.class,
    NumberTypePnetDataApiClientConfig.class})
public class PnetDataClientConfig
{

    // intentionally left blank

}
