package com.frostick.assetmanagement.repository;

import com.frostick.assetmanagement.data.asset.Type;
import com.frostick.assetmanagement.data.asset.impl.AssetImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the asset repository.
 */
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DataJpaTest
public class AssetRepositoryTest {

    /**
     * Asset Repository.
     */
    @Autowired
    private AssetRepository assetRepository;

    /**
     * Test the saving of an asset.
     */
    @Test
    public void testSaveAsset() {
        AssetImpl asset = new AssetImpl("Test Asset 1", Type.PROPERTY, "Asset 1", "Tom", "Tom");
        assetRepository.save(asset);
        assertThat(asset).hasFieldOrPropertyWithValue("name", "Test Asset 1");
        assertThat(asset).hasFieldOrPropertyWithValue("type", Type.PROPERTY);
        assertThat(asset).hasFieldOrPropertyWithValue("description", "Asset 1");
        assertThat(asset).hasFieldOrPropertyWithValue("createdBy", "Tom");
        assertThat(asset).hasFieldOrPropertyWithValue("modifiedBy", "Tom");
    }

    /**
     * Test finding all assets.
     */
    @Test
    public void testFindAll() {
        Iterable<AssetImpl> assets = assetRepository.findAll();
        assertThat(assets).isEmpty();

        AssetImpl asset = new AssetImpl("Test Asset 1", Type.PROPERTY, "Asset 1", "Tom", "Tom");
        assetRepository.save(asset);
        AssetImpl asset2 = new AssetImpl("Test Asset 2", Type.PROPERTY, "Asset 2", "Tom", "Tom");
        assetRepository.save(asset2);
        AssetImpl asset3 = new AssetImpl("Test Asset 3", Type.PROPERTY, "Asset 3", "Tom", "Tom");
        assetRepository.save(asset3);

        assets = assetRepository.findAll();
        assertThat(assets).hasSize(3).contains(asset, asset2, asset3);
    }

    /**
     * Test the saving of an asset.
     */
    @Test
    public void testFindAssetById() {
        AssetImpl asset = new AssetImpl("Test Asset 1", Type.PROPERTY, "Asset 1", "Tom", "Tom");
        assetRepository.save(asset);
        AssetImpl asset2 = new AssetImpl("Test Asset 2", Type.PROPERTY, "Asset 2", "Tom", "Tom");
        assetRepository.save(asset2);
        AssetImpl asset3 = new AssetImpl("Test Asset 3", Type.PROPERTY, "Asset 3", "Tom", "Tom");
        assetRepository.save(asset3);

        Optional<AssetImpl> assetFound = assetRepository.findById(asset2.getId());
        assertThat(assetFound.isPresent()).isTrue();
        assertThat(assetFound.get()).isEqualTo(asset2);
    }

    /**
     * Test the saving of an asset.
     */
    @Test
    public void testUpdate() {
        AssetImpl asset = new AssetImpl("Test Asset 1", Type.PROPERTY, "Asset 1", "Tom", "Tom");
        assetRepository.save(asset);
        AssetImpl asset2 = new AssetImpl("Test Asset 2", Type.PROPERTY, "Asset 2", "Tom", "Tom");
        assetRepository.save(asset2);
        AssetImpl asset3 = new AssetImpl("Test Asset 3", Type.PROPERTY, "Asset 3", "Tom", "Tom");
        assetRepository.save(asset3);

        AssetImpl assetFound = assetRepository.findById(asset2.getId()).get();
        assetFound.setName("Test Asset Modified");
        assetFound.setType(Type.STOCK);
        assetFound.setDescription("This has been updated");
        assetRepository.save(assetFound);

        AssetImpl assetUpdated = assetRepository.findById(asset2.getId()).get();
        assertThat(assetUpdated.getId()).isEqualTo(asset2.getId());
        assertThat(assetUpdated.getName()).isEqualTo(assetFound.getName());
        assertThat(assetUpdated.getDescription()).isEqualTo(assetFound.getDescription());
        assertThat(assetUpdated.getCreatedBy()).isEqualTo(assetFound.getCreatedBy());
        assertThat(assetUpdated.getModifiedBy()).isEqualTo(assetFound.getModifiedBy());
    }

    /**
     * Test asset deletion.
     */
    @Test
    public void testDelete() {
        AssetImpl asset = new AssetImpl("Test Asset 1", Type.PROPERTY, "Asset 1", "Tom", "Tom");
        assetRepository.save(asset);
        AssetImpl asset2 = new AssetImpl("Test Asset 2", Type.PROPERTY, "Asset 2", "Tom", "Tom");
        assetRepository.save(asset2);
        AssetImpl asset3 = new AssetImpl("Test Asset 3", Type.PROPERTY, "Asset 3", "Tom", "Tom");
        assetRepository.save(asset3);

        assetRepository.deleteById(asset2.getId());
        assertThat(assetRepository.findAll()).hasSize(2).contains(asset, asset3);
    }

}
