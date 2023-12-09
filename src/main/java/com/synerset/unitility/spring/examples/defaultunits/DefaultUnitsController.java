package com.synerset.unitility.spring.examples.defaultunits;

import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class DefaultUnitsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping("/temperatures/{temperature}")
    public Temperature getCustomAnglePath(@PathVariable("temperature") Temperature temperature) {
        processTemperature(temperature);
        return temperature;
    }

    @PostMapping("/temperatures")
    public Temperature getCustomAngleBody(@RequestBody Temperature temperature) {
        processTemperature(temperature);
        return temperature;
    }

    private void processTemperature(Temperature temperature) {
        String tempInCelsiusAsString = temperature.toEngineeringFormat();
        logger.info("Input in eng. format: {}", tempInCelsiusAsString);
        String tempInFahrenheitsAsString = temperature.toFahrenheit().toEngineeringFormat(3);
        logger.info("In Fahrenheits: {}", tempInFahrenheitsAsString);
    }

}