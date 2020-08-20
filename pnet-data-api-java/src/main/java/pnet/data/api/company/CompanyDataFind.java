package pnet.data.api.company;

import java.util.List;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.CompanyMergable;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictCompanyNumber;
import pnet.data.api.util.RestrictContractType;
import pnet.data.api.util.RestrictCountryCode;
import pnet.data.api.util.RestrictDatedBackUntil;
import pnet.data.api.util.RestrictExternalBrand;
import pnet.data.api.util.RestrictId;
import pnet.data.api.util.RestrictLocation;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictPostalCode;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictType;
import pnet.data.api.util.RestrictUpdatedAfter;
import pnet.data.api.util.Scrollable;

/**
 * Find interface for companies
 *
 * @author HAM
 */
public class CompanyDataFind extends AbstractFind<CompanyItemDTO, CompanyDataFind>
    implements RestrictId<CompanyDataFind>, RestrictMatchcode<CompanyDataFind>, RestrictTenant<CompanyDataFind>,
    RestrictBrand<CompanyDataFind>, RestrictCompanyNumber<CompanyDataFind>, RestrictPostalCode<CompanyDataFind>,
    RestrictCountryCode<CompanyDataFind>, RestrictType<CompanyDataFind>, RestrictContractType<CompanyDataFind>,
    RestrictLocation<CompanyDataFind>, RestrictExternalBrand<CompanyDataFind>, RestrictUpdatedAfter<CompanyDataFind>,
    RestrictDatedBackUntil<CompanyDataFind>, IncludeInactive<CompanyDataFind>, CompanyMergable<CompanyDataFind>,
    Scrollable<CompanyDataFind>
{

    public CompanyDataFind(FindFunction<CompanyItemDTO> findFunction, List<Pair<String, Object>> restricts)
    {
        super(findFunction, restricts);
    }
}