package com.frostick.assetmanagement.data.valuation.impl;

import com.frostick.assetmanagement.data.asset.impl.AssetImpl;

/**
 * A class to contain the asset and various calculated values based on the valuations associated with it.
 */
public class ValuationStatsImpl {
    /**
     * The current value of the asset.
     */
    private double currentValue;
    /**
     * The average value based on the past 7 days of valuations.
     */
    private Double averageValue7d;
    /**
     * The average value based on the total number of valuations.
     */
    private Double averageValueTotal;
    /**
     * The value change in the past 60 days.
     */
    private Double change1d;
    /**
     * The value change in the past 7 days.
     */
    private Double change7d;
    /**
     * The value change across the total number of valuations.
     */
    private Double totalChange;

    /**
     * The total days used to calculate totalChange and totalAverage.
     */
    private int totalCount;

    /**
     * Constructor.
     * @param currentValue the current value
     * @param averageValue7d the average value across the last 7 days
     * @param averageValueTotal the average value across the last 30days
     * @param change7d the total valuation change from the past 7 days
     * @param totalChange the total valuation change from the past 30 days
     * @param change1d the total valuation change form the past day
     */
    public ValuationStatsImpl(Double currentValue, Double averageValue7d, Double averageValueTotal,
                              Double change7d, Double totalChange, Double change1d, int totalCount) {
        this.currentValue = currentValue;
        this.averageValue7d = averageValue7d;
        this.averageValueTotal = averageValueTotal;
        this.change7d = change7d;
        this.totalChange = totalChange;
        this.change1d = change1d;
        this.totalCount = totalCount;
    }

    /**
     * Get the current value.
     * @return the current value
     */
    public double getCurrentValue() {
        return currentValue;
    }

    /**
     * Set the current value.
     * @param currentValue the current value
     */
    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * Get the average 7day value.
     * @return the average 7day value.
     */
    public Double getAverageValue7d() {
        return averageValue7d;
    }

    /**
     * Set the average 7day value.
     * @param averageValue7d averageValue7d
     */
    public void setAverageValue7d(Double averageValue7d) {
        this.averageValue7d = averageValue7d;
    }

    /**
     * Get the average 30day value.
     * @return the average 30day value
     */
    public Double getAverageValueTotal() {
        return averageValueTotal;
    }

    /**
     * Set the average 30day value
     * @param averageValue30d the average 30day value
     */
    public void setAverageValueTotal(Double averageValue30d) {
        this.averageValueTotal = averageValue30d;
    }

    /**
     * Get the 7-day change.
     * @return the 7-day change
     */
    public Double getChange7d() {
        return change7d;
    }

    /**
     * Set the 7-day change.
     * @param change7d 7-day change
     */
    public void setChange7d(Double change7d) {
        this.change7d = change7d;
    }

    /**
     * Get the 30-day change.
     * @return the 30-day change
     */
    public Double getTotalChange() {
        return totalChange;
    }

    /**
     * Set the 30-day change.
     * @param totalChange 30-day change
     */
    public void setTotalChange(Double totalChange) {
        this.totalChange = totalChange;
    }

    /**
     * Get the 1-day change.
     * @return the 1-day change
     */
    public Double getChange1d() {
        return change1d;
    }

    /**
     * Set the 1-day change.
     * @param change1d the 1-day change
     */
    public void setChange1d(Double change1d) {
        this.change1d = change1d;
    }

    /**
     * Get the total day count.
     * @return the total day count
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * Set the total day count
     * @param totalCount the total count
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

}
