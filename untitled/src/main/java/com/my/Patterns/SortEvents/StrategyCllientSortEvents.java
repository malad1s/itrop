package com.my.Patterns.SortEvents;

import com.my.enity.Event;

import java.util.List;

public class StrategyCllientSortEvents{
    private SortEvents strategy;

    public void setStrategy(SortEvents strategy) {
        this.strategy = strategy;
    }
    public void executeStrategy(List<Event> list){
        strategy.sort(list);
    }
}
