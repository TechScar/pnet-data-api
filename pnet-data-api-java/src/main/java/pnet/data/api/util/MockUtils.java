package pnet.data.api.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.DefaultPnetDataClientResultPageWithAggregations;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPageWithAggregations;

/**
 * Utilities for mocks
 *
 * @author HAM
 */
public final class MockUtils
{

    private MockUtils()
    {
        super();
    }

    public static <T> PnetDataClientResultPage<T> mockResultPage(List<T> allItems, int pageIndex, int itemsPerPage)
    {
        List<List<T>> chunks = split(allItems, itemsPerPage, ArrayList::new);
        List<T> items = pageIndex >= chunks.size() ? Collections.emptyList() : chunks.get(0);
        DefaultPnetDataClientResultPage<T> result = new DefaultPnetDataClientResultPage<>(items, itemsPerPage,
            allItems.size(), pageIndex, allItems.size() / itemsPerPage + 1, null);

        result.setPageSupplier(index -> mockResultPage(allItems, index, itemsPerPage));
        result.setScrollSupplier(scrollId -> mockResultPage(allItems, pageIndex + 1, itemsPerPage));

        return result;
    }

    public static <T, AggregationsT> PnetDataClientResultPageWithAggregations<T, AggregationsT> mockResultPageWithAggregations(
        List<T> allItems, AggregationsT aggregations, int pageIndex, int itemsPerPage)
    {
        List<List<T>> chunks = split(allItems, itemsPerPage, ArrayList::new);
        List<T> items = pageIndex >= chunks.size() ? Collections.emptyList() : chunks.get(0);
        DefaultPnetDataClientResultPageWithAggregations<T, AggregationsT> result =
            new DefaultPnetDataClientResultPageWithAggregations<>(items, aggregations, itemsPerPage, allItems.size(),
                pageIndex, allItems.size() / itemsPerPage + 1, null);

        result.setPageSupplier(index -> mockResultPage(allItems, index, itemsPerPage));
        result.setScrollSupplier(scrollId -> mockResultPage(allItems, pageIndex + 1, itemsPerPage));

        return result;
    }

    protected static <T, CollectionOfT extends Collection<T>> List<CollectionOfT> split(Iterable<T> source,
        int maxChunkSize, Function<Collection<T>, CollectionOfT> targetCollectionFactory)
    {
        List<CollectionOfT> result = new ArrayList<>();
        Iterator<T> iterator = source.iterator();
        Collection<T> current = new ArrayList<>();

        while (iterator.hasNext())
        {
            current.add(iterator.next());

            if (current.size() >= maxChunkSize || (!iterator.hasNext() && current.size() > 0))
            {
                result.add(targetCollectionFactory.apply(current));
                current.clear();
            }
        }

        return result;
    }

    public static <EntryT, GroupT, AggregationT> List<AggregationT> aggregate(Collection<EntryT> entries,
        Function<? super EntryT, GroupT> mapper, BiFunction<GroupT, Long, AggregationT> aggregationFactory)
    {
        return entries
            .stream()
            .map(mapper)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .stream()
            .map(entry -> aggregationFactory.apply(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
    }

    public static <EntryT, GroupT, AggregationT> List<AggregationT> aggregateFlat(Collection<EntryT> entries,
        Function<? super EntryT, ? extends Stream<? extends GroupT>> mapper,
        BiFunction<GroupT, Long, AggregationT> aggregationFactory)
    {
        return entries
            .stream()
            .flatMap(mapper)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .stream()
            .map(entry -> aggregationFactory.apply(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
    }

    public static <AggregationT> List<AggregationT> aggregateTenants(Collection<? extends WithTenants> entries,
        BiFunction<String, Long, AggregationT> aggregationFactory)
    {
        return aggregateFlat(entries, item -> item.getTenants().stream(), aggregationFactory);
    }

}
