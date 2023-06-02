package com.frostick.assetmanagement.data.valuation;

import java.time.LocalDate;
import java.util.Date;

/**
 * Interface defining Valuation object.
 */
public interface Valuation {
    /**
     * The date.
     * @return the date
     */
    LocalDate getDate();

    /**
     * Set the date.
     * @param date the date
     */
    void setDate(LocalDate date);

    /**
     *
     * Get the value.
     * @return the value
     */
    double getValue();

    /**
     * Set the value.
     * @param value the value
     */
    void setValue(double value);
}
