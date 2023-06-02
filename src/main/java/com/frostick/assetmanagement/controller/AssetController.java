package com.frostick.assetmanagement.controller;

import com.frostick.assetmanagement.data.asset.impl.AssetImpl;
import com.frostick.assetmanagement.service.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * The Rest Controller for the asset App.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/assets")
public class AssetController {

    /**
     * The Asset Service.
     */
    @Resource(name = "assetService")
    private AssetService assetService;

    /**
     * Get mapping to retrieve all the assets.
     * @return the response containing list of assets
     */
    @GetMapping
    public ResponseEntity<Object> getAssets() {
        return new ResponseEntity<>(assetService.getAll(), HttpStatus.OK);
    }

    /**
     * Get mapping to retrieve a single asset.
     * @param id the id
     * @return the response containing a single asset
     */
    @GetMapping(value = "/asset/{id}")
    public ResponseEntity<Object> getAsset(@PathVariable int id) {
        return new ResponseEntity<>(assetService.getById(id), HttpStatus.OK);
    }

    /**
     * Save an asset.
     * @param asset the asset
     * @return ResponseEntity wrapping the saved asset
     */
    @PostMapping(value = "/asset")
    public ResponseEntity<Object> saveAsset(@RequestBody AssetImpl asset) {
        return new ResponseEntity<>(assetService.saveAsset(asset), HttpStatus.OK);
    }

    /**
     * Save an asset.
     * @param id the id
     * @param asset the asset
     * @return ResponseEntity wrapping the saved asset
     */
    @PutMapping(value = "/asset/{id}")
    public ResponseEntity<Object> updateAsset(@PathVariable int id, @RequestBody AssetImpl asset) {
        return new ResponseEntity<>(assetService.updateAsset(id, asset), HttpStatus.OK);
    }

    /**
     * Delete the asset with the passed asset number.
     * @param id the asset number
     * @return true if successful
     */
    @DeleteMapping("/asset/{id}")
    public ResponseEntity<Object> deleteAsset(@PathVariable int id) {
        return new ResponseEntity<>(assetService.deleteAsset(id), HttpStatus.OK);
    }
}
