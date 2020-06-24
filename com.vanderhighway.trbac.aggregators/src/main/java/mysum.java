import org.eclipse.viatra.query.runtime.matchers.aggregators.DoubleSumOperator;
import org.eclipse.viatra.query.runtime.matchers.aggregators.IntegerSumOperator;
import org.eclipse.viatra.query.runtime.matchers.aggregators.LongSumOperator;
import org.eclipse.viatra.query.runtime.matchers.psystem.aggregations.AggregatorType;
import org.eclipse.viatra.query.runtime.matchers.psystem.aggregations.BoundAggregator;
import org.eclipse.viatra.query.runtime.matchers.psystem.aggregations.IAggregatorFactory;

/**
 * This aggregator calculates the sum of the values of a selected aggregate parameter of a called pattern. The aggregate
 * parameter is selected with the '#' symbol; the aggregate parameter must not be used outside the aggregator call. The
 * other parameters of the call might be bound or unbound; bound parameters limit the matches to consider for the
 * summation.
 *
 * @since 1.4
 *
 */
@AggregatorType(
        parameterTypes = {Integer.class, Double.class, Long.class},
        returnTypes = {Integer.class, Double.class, Long.class})
public final class mysum implements IAggregatorFactory {

    @Override
    public BoundAggregator getAggregatorLogic(Class<?> domainClass) {
        if (Integer.class.equals(domainClass))
            return new BoundAggregator(IntegerSumOperator.INSTANCE, Integer.class, Integer.class);
        if (Double.class.equals(domainClass))
            return new BoundAggregator(DoubleSumOperator.INSTANCE, Double.class, Double.class);
        if (Long.class.equals(domainClass))
            return new BoundAggregator(LongSumOperator.INSTANCE, Long.class, Long.class);
        else throw new IllegalArgumentException();
    }
}

