package pnet.data.api.numbertype;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

/**
 * Search interface for {@link NumberTypeDataClient}.
 */
public class NumberTypeDataSearch extends AbstractSearch<NumberTypeItemDTO, NumberTypeDataSearch>
{
    public NumberTypeDataSearch(ObjectMapper mapper, SearchFunction<NumberTypeItemDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, searchFunction, restricts);
    }
}
