package com.assignment.moja_car_wash.mapper;

public interface Mapper<Source, Destination> {
    Destination mapFromSource(Source s);

    Source mapToSource(Destination d);
}
