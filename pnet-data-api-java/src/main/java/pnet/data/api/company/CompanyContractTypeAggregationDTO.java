package pnet.data.api.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.AbstractCountAggregationDTO;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class CompanyContractTypeAggregationDTO extends AbstractCountAggregationDTO
{

    public CompanyContractTypeAggregationDTO(@JsonProperty("matchcode") String matchcode,
        @JsonProperty("count") long count)
    {
        super(matchcode, count);
    }

}