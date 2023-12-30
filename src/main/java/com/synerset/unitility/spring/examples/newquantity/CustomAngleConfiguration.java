package com.synerset.unitility.spring.examples.newquantity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.synerset.unitility.jackson.serdes.PhysicalQuantityDeserializer;
import com.synerset.unitility.spring.examples.newquantity.customunits.CustomAngle;
import com.synerset.unitility.spring.serdes.PhysicalQuantityWebMvcConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;

@Configuration
public class CustomAngleConfiguration {

    @Autowired
    void registerNewConfig(CustomParsingFactoryWithAngle parsingFactory, ObjectMapper objectMapper, FormatterRegistry registry) {
        // Create new JacksonModule and register deserializer. Serializer is already defined once per all PhysicalQuantity classes.
        SimpleModule customAngleModule = new SimpleModule("CustomAngles");
        customAngleModule.addDeserializer(CustomAngle.class, new PhysicalQuantityDeserializer<>(CustomAngle.class, parsingFactory));
        objectMapper.registerModule(customAngleModule);
        // Register new webMvc converter, to for automatic
        registry.addConverter(String.class, CustomAngle.class, new PhysicalQuantityWebMvcConverter<>(CustomAngle.class, parsingFactory));
    }

}