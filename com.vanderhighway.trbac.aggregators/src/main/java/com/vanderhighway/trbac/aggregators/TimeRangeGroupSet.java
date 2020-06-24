package com.vanderhighway.trbac.aggregators;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.vanderhighway.trbac.model.trbac.model.TimeRangeGroup;

public class TimeRangeGroupSet extends HashSet<TimeRangeGroup> {

    public <D> TimeRangeGroupSet(Set<D> collect) {
    	super();
        this.addAll(collect.stream().map(x -> ((TimeRangeGroup) x)).collect(Collectors.toSet()));
    }
}
