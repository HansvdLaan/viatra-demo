package com.vanderhighway.trbac.core.validator;

import com.brein.time.timeintervals.indexes.IntervalTree;
import com.brein.time.timeintervals.intervals.IntegerInterval;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
import org.eclipse.xtext.xbase.lib.Extension;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class PolicyValidator {


    @Extension
    private Logger logger = Logger.getLogger(PolicyValidator.class);

    protected AdvancedViatraQueryEngine engine;

    private IntervalTree tree;
    Map<IntegerInterval, EObject> intervalmap;

    public PolicyValidator(final AdvancedViatraQueryEngine engine) {
        this.engine = engine;
    }

    //TODO: move this to somewhere else!
    public void addChangeListeners(AdvancedViatraQueryEngine engine) {
        // fireNow = true parameter means all current matches are sent to the listener

       // engine.addMatchUpdateListener(TimeRangeGroupCollection.Matcher.on(engine), com.vanderhighway.trbac.core.validator.ListenerFactory.getTimeRangeGroupCombinationsUpdateListener(), true);
    }

}
