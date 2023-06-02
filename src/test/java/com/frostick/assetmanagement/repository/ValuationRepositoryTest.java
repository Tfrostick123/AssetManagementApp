package com.frostick.assetmanagement.repository;

import com.frostick.assetmanagement.TestHelper;
import com.frostick.assetmanagement.data.asset.Type;
import com.frostick.assetmanagement.data.asset.impl.AssetImpl;
import com.frostick.assetmanagement.data.valuation.impl.ValuationImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Valuation Repository Test class.
 */
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DataJpaTest
public class ValuationRepositoryTest {
    /**
     * The valuation Repository.
     */
    @Autowired
    private ValuationRepository valuationRepository;

    /**
     * The Asset Repository.
     */
    @Autowired
    private AssetRepository assetRepository;

    /**
     * Test findTop30ByAssetOrderByDateDesc method.
     */
    @Test
    public void testFindTop30ByAssetOrderByDateDesc() {
        AssetImpl asset = new AssetImpl("Test Asset 1", Type.PROPERTY, "Asset 1", "Tom", "Tom");
        assetRepository.save(asset);
        AssetImpl asset2 = new AssetImpl("Test Asset 2", Type.PROPERTY, "Asset 2", "Tom", "Tom");
        assetRepository.save(asset2);
        //Save 40 valuations for Asset 1.
        for (int i = 0; i <= 40; i++) {
            ValuationImpl valuation = new ValuationImpl(TestHelper.TEST_DATE.plusDays(i), i * 1000, asset);
            valuationRepository.save(valuation);
        }
        //Save 40 valuations for Asset 2.
        for (int i = 0; i <= 40; i++) {
            ValuationImpl valuation = new ValuationImpl(TestHelper.TEST_DATE.plusDays(i), i * 1000, asset2);
            valuationRepository.save(valuation);
        }

        List<ValuationImpl> valuations2 = valuationRepository.findTop30ByAssetOrderByDateDesc(asset);
        assertThat(valuations2).hasSize(30);
        assertTrue(valuations2.stream().allMatch(v -> v.getAsset() == asset));
    }
}
