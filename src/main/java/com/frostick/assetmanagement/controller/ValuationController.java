package com.frostick.assetmanagement.controller;

import com.frostick.assetmanagement.service.AssetValuationUpdaterService;
import com.frostick.assetmanagement.service.ValuationService;
import com.frostick.assetmanagement.data.valuation.impl.ValuationImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * The Rest Controller for the valuation App.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/valuations")
@RequiredArgsConstructor
public class ValuationController {

    /**
     * The Valuation Service.
     */
    @Resource(name = "valuationService")
    private ValuationService valuationService;

    @Resource(name = "assetValuationService")
    private AssetValuationUpdaterService assetValuationUpdaterService;

    /**
     * Get mapping to retrieve all the valuations. This would likely never be used as the user would want the context
     * of the Asset.
     * @return the response containing list of valuations
     */
    @GetMapping
    public ResponseEntity<Object> getValuations() {
        return new ResponseEntity<>(valuationService.getAll(), HttpStatus.OK);
    }

    /**
     * Get mapping to retrieve a single valuation.
     * @param id the id
     * @return the response containing a single valuation
     */
    @GetMapping(value = "/valuation/{id}")
    public ResponseEntity<Object> getValuation(@PathVariable int id) {
        return new ResponseEntity<>(valuationService.getById(id), HttpStatus.OK);
    }

    /**
     * Save a valuation.
     * @param assetId to assign the valuation to
     * @param valuation the valuation
     * @return ResponseEntity wrapping the saved valuation
     */
    @PostMapping(value = "/assets/{assetId}/valuation")
    public ResponseEntity<Object> saveValuation(@PathVariable int assetId, @RequestBody ValuationImpl valuation) {
        return new ResponseEntity<>(assetValuationUpdaterService.saveAssetValuation(assetId, valuation), HttpStatus.OK);
    }

    /**
     * Save a valuation.
     * @param assetId to assign the valuation to
     * @param valuations the valuations
     * @return ResponseEntity wrapping the saved valuation
     */
    @PostMapping(value = "/assets/{assetId}/valuations")
    public ResponseEntity<Object> saveValuations(@PathVariable int assetId, @RequestBody List<ValuationImpl> valuations) {
        return new ResponseEntity<>(assetValuationUpdaterService.saveAssetValuations(assetId, valuations), HttpStatus.OK);
    }

    /**
     * Save a valuation.
     * @param id the id
     * @param valuation the valuation
     * @return ResponseEntity wrapping the saved valuation
     */
    @PutMapping(value = "/valuation/{id}")
    public ResponseEntity<Object> updateValuation(@PathVariable int id, @RequestBody ValuationImpl valuation) {
        return new ResponseEntity<>(valuationService.updateValuation(id, valuation), HttpStatus.OK);
    }

    /**
     * Delete the valuation with the passed valuation number.
     * @param id the valuation number
     * @return true if successful
     */
    @DeleteMapping("/valuation/{id}")
    public ResponseEntity<Object> deleteValuation(@PathVariable int id) {
        return new ResponseEntity<>(valuationService.deleteValuation(id), HttpStatus.OK);
    }

    /**
     * Get mapping to retrieve the valuations belonging to the asset.
     * @param assetId the asset id
     * @return the response containing a list of valuations
     */
    @GetMapping(value = "/assets/{assetId}")
    public ResponseEntity<Object> getValuationsByAssetId(@PathVariable int assetId) {
        return new ResponseEntity<>(assetValuationUpdaterService.getTop30AssetValuations(assetId), HttpStatus.OK);
    }

    /**
     * Get mapping to retrieve the valuation stats belonging to the asset.
     * @param assetId the asset id
     * @return the response containing the valuation stats
     */
    @GetMapping(value = "/assets/{assetId}/valuationStats")
    public ResponseEntity<Object> getValuationsStatsByAssetId(@PathVariable int assetId) {
        return new ResponseEntity<>(assetValuationUpdaterService.getAssetValuationStats(assetId), HttpStatus.OK);
    }


}
