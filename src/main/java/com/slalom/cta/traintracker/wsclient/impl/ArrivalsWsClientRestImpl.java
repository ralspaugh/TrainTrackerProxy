package com.slalom.cta.traintracker.wsclient.impl;

import com.slalom.cta.traintracker.arrivals.Ctatt;
import com.slalom.cta.traintracker.wsclient.ArrivalsWsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@Profile("default")
public class ArrivalsWsClientRestImpl implements ArrivalsWsClient {

    private static final String BASE_URL = "http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx";
    private static final String STOP_ID = "stpid";
    private static final String ROUTE_CODE = "rt";
    private static final String API_KEY = "key";

    private Unmarshaller unmarshaller;

    @Autowired
    public ArrivalsWsClientRestImpl(final Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public Ctatt findArrivalsByRouteAndDirection(final String apiKey, final String stopId, final String routeCode) {
        try {
            URL url = new URL(BASE_URL + "?"
                    + API_KEY + "=" + apiKey + "&"
                    + STOP_ID + "=" + stopId + "&"
                    + ROUTE_CODE + "=" + stopId);

            return (Ctatt) unmarshaller.unmarshal(url);
        } catch (Exception ex) {
            return null;
        }
    }
}
