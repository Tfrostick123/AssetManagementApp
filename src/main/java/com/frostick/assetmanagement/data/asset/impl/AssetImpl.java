package com.frostick.assetmanagement.data.asset.impl;

import com.frostick.assetmanagement.data.Auditable;
import com.frostick.assetmanagement.data.asset.Asset;
import com.frostick.assetmanagement.data.asset.Type;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Objects;

/**
 * Implementation of an asset.
 */
@Entity
@Table(name = "asset", schema = "asset")
public class AssetImpl  extends Auditable<String> implements Asset{
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name.
     */
    @Basic
    @Column(name="name", nullable = false)
    private String name;

    /**
     * The record type.
     */
    @Basic
    @Column(name="type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    /**
     * The Description.
     */
    @Basic
    @Column(name="description")
    private String description;

    /**
     * Constructor.
     * @param name name
     * @param type type
     * @param description description
     */
    public AssetImpl(String name, Type type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    /**
     * Constructor.
     * @param name name
     * @param type type
     * @param description description
     * @param createdBy created by user
     * @param modifiedBy modified by user
     */
    public AssetImpl(String name, Type type, String description, String createdBy, String modifiedBy) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.setCreatedBy(createdBy);
        this.setModifiedBy(modifiedBy);
    }

    /**
     * Constructor.
     */
    public AssetImpl() { }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Primary
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public AssetImpl updateAssetField(AssetImpl asset) {
        if (asset.getName() != null) {
            this.setName(asset.getName());
        }
        if (asset.getDescription() != null) {
            this.setDescription(asset.getDescription());
        }
        if (asset.getType() != null) {
            this.setType(asset.getType());
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetImpl asset = (AssetImpl) o;
        return id == asset.id && name.equals(asset.name) && type == asset.type && Objects.equals(description, asset.description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, description);
    }
}
