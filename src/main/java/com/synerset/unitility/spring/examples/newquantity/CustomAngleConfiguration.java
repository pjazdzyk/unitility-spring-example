package com.synerset.unitility.spring.examples.newquantity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.synerset.unitility.jackson.PhysicalQuantityDeserializer;
import com.synerset.unitility.spring.PhysicalQuantityWebMvcConverter;
import com.synerset.unitility.spring.examples.newquantity.customunits.CustomAngle;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;

@Configuration
public class CustomAngleConfiguration {

    void registerNewConfig(CustomParsingRegistryWithAngle parsingRegistry, ObjectMapper objectMapper, FormatterRegistry registry) {
        // Create new JacksonModule and register deserializer. Serializer is already defined once per all PhysicalQuantity classes.
        SimpleModule customAngleModule = new SimpleModule("CustomAngles");
        customAngleModule.addDeserializer(CustomAngle.class, new PhysicalQuantityDeserializer<>(CustomAngle.class, parsingRegistry));
        objectMapper.registerModule(customAngleModule);
        // Register new webMvc converter, to for automatic
        registry.addConverter(String.class, CustomAngle.class, new PhysicalQuantityWebMvcConverter<>(CustomAngle.class, parsingRegistry));
    }

}