package com.synerset.unitility.spring.examples.defaultunits;

import com.synerset.unitility.unitsystem.geographic.GeoCoordinate;
import com.synerset.unitility.unitsystem.geographic.GeoDistance;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class DefaultUnitsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping("/temperatures/{temperature}")
    public Temperature getTemperatureByPath(@PathVariable("temperature") Temperature temperature) {
        logger.info("ENG format: {}", temperature.toEngineeringFormat());
        logger.info("ENG format in F: {}", temperature.toFahrenheit().toEngineeringFormat());
        // Delegate to service, processing
        return temperature;
    }

    @PostMapping("/temperatures")
    public Temperature getTemperatureByBody(@RequestBody Temperature temperature) {
        logger.info("ENG format: {}", temperature.toEngineeringFormat());
        logger.info("ENG format in F: {}", temperature.toFahrenheit().toEngineeringFormat());
        // Delegate to service, processing
        return temperature;
    }

    @GetMapping("/temperatures/by-param")
    public Temperature getTemperatureByBodyByParam(@RequestParam("tmp") Temperature temperature) {
        logger.info("ENG format: {}", temperature.toEngineeringFormat());
        logger.info("ENG format in F: {}", temperature.toFahrenheit().toEngineeringFormat());
        // Delegate to service, processing
        return temperature;
    }
    @PostMapping("/latitude")
    public Latitude getLatitudeByBody(@RequestBody Latitude latitude) {
        logger.info("DMS Format: {}", latitude.toDMSFormat());
        logger.info("ENG Format: {}", latitude.toEngineeringFormat());
        // Delegate to service, processing
        return latitude;
    }

    @GetMapping("/latitudes/{latitude}")
    public Latitude getLatitudeByParam(@PathVariable("latitude") Latitude latitude) {
        logger.info("DMS Format: {}", latitude.toDMSFormat());
        logger.info("ENG Format: {}", latitude.toEngineeringFormat());
        // Delegate to service, processing
        return latitude;
    }

    @PostMapping("/coordinate")
    public GeoCoordinate getGeoCoordinateByBody(@RequestBody GeoCoordinate geoCoordinate) {
        logger.info("DMS Format: {}", geoCoordinate.toDMSFormat());
        logger.info("ENG Format: {}", geoCoordinate.toEngineeringFormat());
        // Delegate to service, processing
        return geoCoordinate;
    }

    @PostMapping("/geodistance")
    public GeoDistance getGeoDistanceByBody(@RequestBody GeoDistance geoDistance) {
        logger.info("Target coordinate in DMS format: {}", geoDistance.getTargetCoordinate().toDMSFormat(3));
        logger.info("Bearing in decimal degrees: {}", geoDistance.getTrueBearing().getInDegrees());
        logger.info("Distance in ENG Format: {}", geoDistance.toEngineeringFormat());
        // Delegate to service, processing
        return geoDistance;
    }

}