/* Copyright 2017 Porsche Informatik GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pnet.data.api.function;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pnet.data.api.ResultCollection;
import pnet.data.api.ResultPage;
import pnet.data.api.activity.ActivityMatchcode;
import pnet.data.api.brand.BrandMatchcode;
import pnet.data.api.companytype.CompanyTypeMatchcode;
import pnet.data.api.contracttype.ContractTypeMatchcode;
import pnet.data.api.infoarea.InfoareaMatchcode;
import pnet.data.api.numbertype.NumberTypeMatchcode;
import pnet.data.api.util.ByMatchcode;

/**
 * API for Functions.
 *
 * @author ham
 */
@RestController
@RequestMapping("/api/v1/functions")
public interface FunctionDataFacade extends ByMatchcode<FunctionMatchcode, FunctionDataDTO>
{

    /**
     * Returns multiple {@link FunctionDataDTO}s each matching all specified restrictions. If one or more restrictions
     * are set each restriction will be applied (AND) and one of the values of each restriction must match (OR). It is
     * not possible to call this method without any restriction. The number of results is limited. The
     * {@link ResultCollection} may contain a call for more results.
     *
     * @param matchcodes the matchcodes for filtering, optional
     * @return a collection of all found items, never null
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResultCollection<FunctionDataDTO> getAll(
        @RequestParam(value = "mc", required = false) Collection<FunctionMatchcode> matchcodes);

    /**
     * Searches for {@link FunctionItemDTO} with the specified query. If one or more filters are set each filter will be
     * applied (AND) and one of the values of each filter must match (OR). The method returns a stripped down item with
     * only a few properties.
     *
     * @param language the language
     * @param query the query
     * @param page the number of the page, 1-based
     * @param perPage the number of items per page
     * @param tenants the tenants for filtering, optional
     * @param brandMatchcodes the matchcodes of brands for filtering, optional
     * @param companyTypeMatchcodes the matchcodes for company types for filtering, optional
     * @param contractTypeMatchcodes the matchcodes of contract types for filtering, optional
     * @param numberTypeMatchcodes the matchcodes of number types for filtering, optional
     * @param activityMatchcodes the matchcodes of activities for filtering, optional
     * @param infoareaMatchcodes the matchcodes of infoareas for filtering, optional
     * @return a page of the results, never null
     */
    // CHECKSTYLE:OFF
    @RequestMapping(value = "/search")
    ResultPage<FunctionItemDTO> search(@RequestParam(value = "l") String language, @RequestParam("q") String query,
        @RequestParam(value = "p", defaultValue = "1") int page,
        @RequestParam(value = "pp", defaultValue = "10") int perPage,
        @RequestParam(value = "t", required = false) Collection<BrandMatchcode> tenants,
        @RequestParam(value = "b", required = false) Collection<BrandMatchcode> brandMatchcodes,
        @RequestParam(value = "companyType", required = false) Collection<CompanyTypeMatchcode> companyTypeMatchcodes,
        @RequestParam(value = "contractType",
            required = false) Collection<ContractTypeMatchcode> contractTypeMatchcodes,
        @RequestParam(value = "numberType", required = false) Collection<NumberTypeMatchcode> numberTypeMatchcodes,
        @RequestParam(value = "activity", required = false) Collection<ActivityMatchcode> activityMatchcodes,
        @RequestParam(value = "infoarea", required = false) Collection<InfoareaMatchcode> infoareaMatchcodes);
    // CHECKSTYLE:ON

    /**
     * Finds multiple {@link FunctionItemDTO}s each matching all specified restrictions. If one or more restrictions are
     * set each restriction will be applied (AND) and one of the values of each restriction must match (OR).
     *
     * @param language the language
     * @param matchcodes the matchcodes for filtering, optional
     * @param tenants the tenants for filtering, optional
     * @param brandMatchcodes the matchcodes of brands for filtering, optional
     * @param companyTypeMatchcodes the matchcodes for company types for filtering, optional
     * @param contractTypeMatchcodes the matchcodes of contract types for filtering, optional
     * @param numberTypeMatchcodes the matchcodes of number types for filtering, optional
     * @param activityMatchcodes the matchcodes of activities for filtering, optional
     * @param infoareaMatchcodes the matchcodes of infoareas for filtering, optional
     * @param updatedAfter updated after the specified date and time, optional
     * @param page the number of the page, 1-based
     * @param perPage the number of items per page
     * @return a page of the results, never null
     */
    // CHECKSTYLE:OFF
    @RequestMapping(value = "/find")
    ResultPage<FunctionItemDTO> find(@RequestParam(value = "l") String language,
        @RequestParam(value = "mc", required = false) Collection<FunctionMatchcode> matchcodes,
        @RequestParam(value = "t", required = false) Collection<BrandMatchcode> tenants,
        @RequestParam(value = "b", required = false) Collection<BrandMatchcode> brandMatchcodes,
        @RequestParam(value = "companyType", required = false) Collection<CompanyTypeMatchcode> companyTypeMatchcodes,
        @RequestParam(value = "contractType",
            required = false) Collection<ContractTypeMatchcode> contractTypeMatchcodes,
        @RequestParam(value = "numberType", required = false) Collection<NumberTypeMatchcode> numberTypeMatchcodes,
        @RequestParam(value = "activity", required = false) Collection<ActivityMatchcode> activityMatchcodes,
        @RequestParam(value = "infoarea", required = false) Collection<InfoareaMatchcode> infoareaMatchcodes,
        @RequestParam(value = "up", required = false) LocalDateTime updatedAfter,
        @RequestParam(value = "p", defaultValue = "1") int page,
        @RequestParam(value = "pp", defaultValue = "10") int perPage);
    // CHECKSTYLE:ON

}
