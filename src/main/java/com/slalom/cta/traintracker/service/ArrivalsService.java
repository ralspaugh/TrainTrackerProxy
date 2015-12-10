package com.slalom.cta.traintracker.service;

import java.util.List;

public interface ArrivalsService {

    List<Long> findArrivalTimesByStopInMinutes(final String apiKey, final String stopId, final String routeCode);
}
