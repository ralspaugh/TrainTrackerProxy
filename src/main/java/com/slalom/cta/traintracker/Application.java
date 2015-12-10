package com.slalom.cta.traintracker;

import com.slalom.cta.traintracker.arrivals.Ctatt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Configuration
@ComponentScan(basePackages = "com.slalom.cta.traintracker")
@EnableWebMvc
public class Application {

    @Bean
    public Unmarshaller unmashaller() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Ctatt.class);

        return jaxbContext.createUnmarshaller();
    }
}
