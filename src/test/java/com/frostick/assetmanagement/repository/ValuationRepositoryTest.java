package com.frostick.assetmanagement.repository;

import com.frostick.assetmanagement.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class AssetServiceTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AssetService assetService;

    void testExample() throws Exception {

    }
}
