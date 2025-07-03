package com.synerset.unitility.spring.examples.newquantity;

import com.synerset.unitility.spring.examples.newquantity.customunit.CustomAngle;
import com.synerset.unitility.spring.examples.newquantity.customunit.CustomAngleUnits;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityAbstractParsingFactory;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

@Primary
@Service
public class CustomParsingFactoryWithAngle extends PhysicalQuantityAbstractParsingFactory {

    private final Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> customRegistry;
    private final Map<Class<?>, Unit> immutableDefaultUnitRegistry;

    public CustomParsingFactoryWithAngle() {
        // Registering our custom angle unit
        Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> registry = new ConcurrentHashMap<>();
        registry.put(CustomAngle.class, CustomAngle::of);
        // We need to merge our map with a default parsing map. This bean must contain all parsing functions, not only the new one.
        registry.putAll(PhysicalQuantityParsingFactory.getDefaultParsingFactory().getClassRegistry());
        this.customRegistry = Collections.unmodifiableMap(registry);

        // Additionally, we have to register our expected default unit
        Map<Class<?>, Unit> defaultUnits = new ConcurrentHashMap<>();
        defaultUnits.put(CustomAngle.class, CustomAngleUnits.REVOLUTIONS);
        // And also, include all other default units registered in default parsing factory
        defaultUnits.putAll(PhysicalQuantityParsingFactory.getDefaultParsingFactory().getDefaultUnitRegistry());
        this.immutableDefaultUnitRegistry = Collections.unmodifiableMap(defaultUnits);
    }

    @Override
    public Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> getClassRegistry() {
        return customRegistry;
    }

    @Override
    public Map<Class<?>, Unit> getDefaultUnitRegistry() {
        return immutableDefaultUnitRegistry;
    }

}