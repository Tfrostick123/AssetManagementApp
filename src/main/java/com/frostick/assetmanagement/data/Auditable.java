package com.frostick.assetmanagement.data;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * Abstract auditable class.
 * @param <T> Type of user identifier
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

    /**
     * Creation Date.
     */
    @Column(name = "created_date", updatable = false)
    @Temporal(TIMESTAMP)
    @CreatedDate
    protected Date creationDate;


    /**
     * Last modification date.
     */
    @Column(name = "last_modified_date")
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;

    /**
     * User who created the record.
     */
    @CreatedBy
    @Column(name="created_by")
    protected T createdBy;

    /**
     * User who modified the record.
     */
    @LastModifiedBy
    @Column(name="modified_by")
    protected T modifiedBy;

    /**
     * Get the user who created the record.
     * @return the user
     */
    public T getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the user who created the record.
     * @param createdBy the user
     */
    public void setCreatedBy(T createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get the user who modified the record.
     * @return the user
     */
    public T getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Set the user who modified the record.
     * @param modifiedBy the user
     */
    public void setModifiedBy(T modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
