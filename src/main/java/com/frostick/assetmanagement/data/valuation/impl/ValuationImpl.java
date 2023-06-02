package com.frostick.assetmanagement.data.valuation.impl;

import com.frostick.assetmanagement.data.Auditable;
import com.frostick.assetmanagement.data.asset.impl.AssetImpl;
import com.frostick.assetmanagement.data.valuation.Valuation;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

/**
 * Entity class for asset valuation.
 */
@Entity
@Table(name="valuation", schema="valuation")
public class ValuationImpl extends Auditable<String> implements Valuation {

    /** The id.*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    /** The date of the valuation. */
    @Column(name="`date`", nullable = false)
    private LocalDate date;
    /** The value. */
    @Column(name="`value`", nullable = false)
    @Basic
    private double value;

    /** The asset of the valuation. */
    @JoinColumn(name="asset_id", nullable = false)
    @ManyToOne(cascade = {PERSIST, MERGE})
    private AssetImpl asset;

    /**
     * Constructor.
     */
    public ValuationImpl() { };

    /**
     * Constructor.
     * @param date the date
     * @param value the value
     * @param asset the asset
     */
    public ValuationImpl(LocalDate date, double value, AssetImpl asset) {
        this.date = date;
        this.value = value;
        this.asset = asset;
    }

    /**
     * Get the id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id.
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the date.
     * @return the date
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * Set the date
     * @param date the date
     */
    @Override
    public void setDate(final LocalDate date) {
        this.date = date;
    }

    /**
     * Get the valuation value.
     * @return the value
     */
    @Override
    public double getValue() {
        return value;
    }

    /**
     * Set the valuation value
     * @param value the value
     */
    @Override
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Get the asset.
     * @return the asset
     */
    public AssetImpl getAsset() {
        return asset;
    }

    /**
     * Set the asset.
     * @param asset asset
     */
    public void setAsset(AssetImpl asset) {
        this.asset = asset;
    }



}
