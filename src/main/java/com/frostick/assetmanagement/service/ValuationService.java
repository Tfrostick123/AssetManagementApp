package com.frostick.assetmanagement.service;

import com.frostick.assetmanagement.data.asset.impl.AssetImpl;
import com.frostick.assetmanagement.data.valuation.impl.ValuationImpl;
import com.frostick.assetmanagement.data.valuation.impl.ValuationStatsImpl;
import com.frostick.assetmanagement.repository.AssetRepository;
import com.frostick.assetmanagement.repository.ValuationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

/**
 * Service for Valuations
 */
@Service("valuationService")
@RequiredArgsConstructor
public class ValuationService {

    /**
     * Repository of Valuation records.
     */
    private final ValuationRepository valuationRepository;

    /**
     * Get all the Valuation records.
     * @return a list of all records
     */
    public List<ValuationImpl> getAll() {
        return valuationRepository.findAll();
    }

    /**
     * Get the record with the passed in record id.
     * @param id the id
     * @return the record
     */
    public Optional<ValuationImpl> getById(int id) {
        return valuationRepository.findById(id);
    }

    /**
     * Save the valuation passed in.
     * @param valuation the valuation
     * @return the valuation saved
     */
    public ValuationImpl saveValuation(ValuationImpl valuation) {
        return valuationRepository.save(valuation);
    }

    /**
     * Save the valuation passed in.
     * @param id valuation number
     * @param valuation the valuation
     * @return the valuation saved
     */
    public ValuationImpl updateValuation(int id, ValuationImpl valuation) {
        Optional<ValuationImpl> optional = getById(id);
        return optional.map(record -> {
                    record.setDate(valuation.getDate());
                    record.setValue(valuation.getValue());
                    return valuationRepository.save(record);
                }
        ).orElseThrow(
                () -> new ResourceNotFoundException(format("Valuation not found with id: [%s]", id))
        );
    }

    /**
     * Delete the valuation with the passed id.
     * @param id id
     * @return true if successful
     */
    public boolean deleteValuation(int id) {
        valuationRepository.deleteById(id);
        return true;
    }

    public List<ValuationImpl> getTop30AssetValuations(AssetImpl asset) {
        return valuationRepository.findTop30ByAssetOrderByDateDesc(asset);
    }

}
