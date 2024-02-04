package com.synerset.unitility.spring.examples.newquantity.customunit;

import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.common.AngleUnits.getDefaultUnit;

public enum CustomAngleUnits implements AngleUnit {
    REVOLUTIONS("rev", val -> val * 360, val -> val / 360);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    CustomAngleUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public AngleUnit getBaseUnit() {
        return AngleUnits.DEGREES;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static AngleUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return getDefaultUnit();
        }
        String requestedSymbol = formatSymbol(rawSymbol);
        for (AngleUnit unit : values()) {
            String currentSymbol = formatSymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemArgumentException("Unsupported symbol: " + rawSymbol + ", class: "
                + AngleUnits.class.getSimpleName());
    }

    private static String formatSymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .toString();
    }

}