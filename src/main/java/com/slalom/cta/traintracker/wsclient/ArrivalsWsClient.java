package com.slalom.cta.traintracker.wsclient;

import com.slalom.cta.traintracker.arrivals.Ctatt;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;

public interface ArrivalsWsClient {

    Ctatt findArrivalsByRouteAndDirection(final String apiKey, final String stopId, final String routeCode);
}
