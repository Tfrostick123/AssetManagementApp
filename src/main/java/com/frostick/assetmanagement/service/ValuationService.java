package com.frostick.assetmanagement.service;

import com.frostick.assetmanagement.data.asset.impl.AssetImpl;
import com.frostick.assetmanagement.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for Assets
 */
@Service("assetService")
public class AssetService {

    /**
     * Repository of Asset records.
     */
    @Autowired
    private AssetRepository assetRepository;

    /**
     * Get all the Asset records.
     * @return a list of all records
     */
    public List<AssetImpl> getAll() {
        return assetRepository.findAll();
    }

    /**
     * Get the record with the passed in record id.
     * @param id the id
     * @return the record
     */
    public Optional<AssetImpl> getById(int id) {
        return assetRepository.findById(id);
    }

    /**
     * Save the asset passed in.
     * @param asset the asset
     * @return the asset saved
     */
    public AssetImpl saveAsset(AssetImpl asset) {
        return assetRepository.save(asset);
    }

    /**
     * Save the asset passed in.
     * @param id asset number
     * @param asset the asset
     * @return the asset saved
     */
    public AssetImpl updateAsset(int id, AssetImpl asset) {
        Optional<AssetImpl> optional = getById(id);
        if (optional.isPresent()) {
            AssetImpl assetStored = optional.get();
            if (asset.getName() != null) {
                assetStored.setName(asset.getName());
            }
            if (asset.getDescription() != null) {
                assetStored.setDescription(asset.getDescription());
            }
            if (asset.getType() != null) {
                assetStored.setType(asset.getType());
            }
            assetStored.setModifiedBy(asset.getModifiedBy());
            return assetRepository.save(assetStored);
        } else {
            return null;
        }
    }

    /**
     * Delete the asset with the passed id.
     * @param id id
     * @return true if successful
     */
    public boolean deleteAsset(int id) {
        assetRepository.deleteById(id);
        return true;
    }
}
