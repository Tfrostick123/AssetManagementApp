package com.frostick.assetmanagement.repository;

import com.frostick.assetmanagement.data.asset.impl.AssetImpl;
import com.frostick.assetmanagement.data.valuation.impl.ValuationImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The Valuation Repository.
 */
@Repository
public interface ValuationRepository extends JpaRepository<ValuationImpl, Integer> {

    /**
     * Retrieve the top 30 valuations ordered by date for the passed in asset id.
     * @param asset the Asset
     * @return the top 30 valuations ordered by date for the passed in asset id
     */
    List<ValuationImpl> findTop30ByAssetOrderByDateDesc(AssetImpl asset);
}
