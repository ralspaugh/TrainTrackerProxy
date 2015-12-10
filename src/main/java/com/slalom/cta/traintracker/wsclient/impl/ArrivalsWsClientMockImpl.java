package com.slalom.cta.traintracker.wsclient.impl;

import com.slalom.cta.traintracker.arrivals.Ctatt;
import com.slalom.cta.traintracker.wsclient.ArrivalsWsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
@Profile("mock")
public class ArrivalsWsClientMockImpl implements ArrivalsWsClient {

    private Unmarshaller unmarshaller;

    @Autowired
    public ArrivalsWsClientMockImpl(final Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public Ctatt findArrivalsByRouteAndDirection(final String apiKey, final String stopId, final String routeCode) {
        try {
            return (Ctatt) unmarshaller.unmarshal(new ClassPathResource("ArrivalsMock.xml").getInputStream());
        } catch (Exception ex) {
            return null;
        }
    }
}
