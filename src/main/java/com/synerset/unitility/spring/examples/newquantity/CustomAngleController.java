package com.synerset.unitility.spring.examples.newquantity;

import com.synerset.unitility.spring.examples.newquantity.customunits.CustomAngle;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomAngleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping("/angles/{angle}")
    public CustomAngle getCustomAnglePath(@PathVariable("angle") CustomAngle customAngle) {
        processCustomAngle(customAngle);
        return customAngle;
    }

    @PostMapping("/angles")
    public CustomAngle getCustomAngleBody(@RequestBody CustomAngle customAngle) {
        processCustomAngle(customAngle);
        return customAngle;
    }

    private void processCustomAngle(CustomAngle customAngle) {
        String customAngleAsString = customAngle.toEngineeringFormat();
        logger.info("Revolutions: {}", customAngleAsString);
        String anglesInDegAsString = customAngle.toBaseUnit().toEngineeringFormat(3);
        logger.info("Degrees: {}", anglesInDegAsString);
        // Because our custom angle implements AngleUnit, we can convert easily between Angle and CustomAngle:
        String angleInRadians = customAngle.toUnit(AngleUnits.RADIANS).toEngineeringFormat(3);
        logger.info("Radians: {}", angleInRadians);
    }

}