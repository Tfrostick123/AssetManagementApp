package com.frostick.assetmanagement.data.asset;

/**
 * Interface for an Asset.
 */
public interface Asset {
    /**
     * Get the id.
     * @return the id
     */
    int getId();

    /**
     * Set the id.
     * @param id the id
     */
    void setId(int id);

    /**
     * Get the name.
     * @return the name
     */
    String getName();

    /**
     * Set the name.
     * @param name the name
     */
    void setName(String name);

    /**
     * Get the type.
     * @return the type
     */
    Type getType();

    /**
     * Set the type.
     * @param type the type
     */
    void setType(Type type);

    /**
     * Get the description.
     * @return the description
     */
    String getDescription();

    /**
     * Set the description.
     * @param description the description
     */
    void setDescription(String description);
}
