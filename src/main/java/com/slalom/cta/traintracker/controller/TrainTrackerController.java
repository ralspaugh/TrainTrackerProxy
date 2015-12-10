package com.slalom.cta.traintracker.controller;

import com.slalom.cta.traintracker.service.ArrivalsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainTrackerController {

    private ArrivalsService arrivalsService;

    @Autowired
    public TrainTrackerController(final ArrivalsService arrivalsService) {
        this.arrivalsService = arrivalsService;
    }

    @RequestMapping(value= "/arrivals", method = RequestMethod.GET)
    public String findArrivalTimesByStopInMinutes(@RequestParam("key") final String key,
                                                  @RequestParam("stopId") final String stopId,
                                                  @RequestParam("routeCd") final String routeCode) {
        return StringUtils.join(arrivalsService.findArrivalTimesByStopInMinutes(key, stopId, routeCode), ",");
    }
}
