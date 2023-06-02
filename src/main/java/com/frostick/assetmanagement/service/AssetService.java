package com.frostick.assetmanagement.service;

import com.frostick.assetmanagement.data.asset.impl.AssetImpl;
import com.frostick.assetmanagement.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

/**
 * Service for Assets.
 */
@Service("assetService")
public class AssetService {

    /**
     * Repository of Asset records.
     */
    private final AssetRepository assetRepository;

    @Autowired
    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

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
    public AssetImpl getById(int id) {
        Optional<AssetImpl> optional = assetRepository.findById(id);
        return optional.orElseThrow(() -> new ResourceNotFoundException(format("Asset not found with id: [%s]", id)));
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
        return assetRepository.findById(id)
                .map(updatedAsset -> updatedAsset.updateAssetField(asset))
                .map(assetRepository::save)
                .orElse(null);
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
