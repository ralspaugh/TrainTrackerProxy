package com.slalom.cta.traintracker.service.impl;

import com.slalom.cta.traintracker.arrivals.Ctatt;
import com.slalom.cta.traintracker.service.ArrivalsService;
import com.slalom.cta.traintracker.wsclient.ArrivalsWsClient;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ArrivalsServiceImpl implements ArrivalsService {

    private static final Logger LOGGER = Logger.getLogger(ArrivalsServiceImpl.class.getName());

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyyMMdd HH:mm:ss");

    private ArrivalsWsClient wsClient;

    @Autowired
    public ArrivalsServiceImpl(final ArrivalsWsClient wsClient) {
        this.wsClient = wsClient;
    }

    public List<Long> findArrivalTimesByStopInMinutes(final String apiKey, final String stopId, final String routeCode) {
        List<Long> arrivalTimes = new ArrayList<>();

        LOGGER.log(Level.INFO, "Retrieving")

        Ctatt response = wsClient.findArrivalsByRouteAndDirection(apiKey, stopId, routeCode);

        for (Ctatt.Eta eta : response.getEta()) {
            DateTime predictionTime = DateTime.parse(eta.getPrdt(), DATE_FORMAT);
            DateTime arrivalTime = DateTime.parse(eta.getArrT(), DATE_FORMAT);

            long differenceInMillis = arrivalTime.getMillis() - predictionTime.getMillis();
            long minutesTilArrival = (differenceInMillis / 60000);
            arrivalTimes.add(minutesTilArrival);
        }

        Collections.sort(arrivalTimes);

        return arrivalTimes;
    }

}
