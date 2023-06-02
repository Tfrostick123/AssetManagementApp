package com.frostick.assetmanagement.repository;

import com.frostick.assetmanagement.data.asset.impl.AssetImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The asset Repository.
 */
@Repository
public interface AssetRepository extends JpaRepository<AssetImpl, Integer> {
}
