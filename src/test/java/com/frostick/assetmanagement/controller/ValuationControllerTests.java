package com.frostick.assetmanagement.controller;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frostick.assetmanagement.TestHelper;
import com.frostick.assetmanagement.data.asset.Type;
import com.frostick.assetmanagement.data.asset.impl.AssetImpl;
import com.frostick.assetmanagement.data.valuation.impl.ValuationImpl;
import com.frostick.assetmanagement.service.AssetValuationUpdaterService;
import com.frostick.assetmanagement.service.ValuationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for Valuation controller.
 */
@ImportAutoConfiguration(WebMvcAutoConfiguration.class)
@WebMvcTest(ValuationController.class)
public class ValuationControllerTests {
    @MockBean(name="valuationService")
    private ValuationService valuationService;

    @MockBean(name="assetValuationService")
    private AssetValuationUpdaterService assetValuationUpdaterService;

    /**
     * Mock Mvc to help with Restful API calls
     */
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test getting the valuations that are linked to an asset.
     * @throws Exception exception
     */
    @Test
    void testGetValuationsByAssetId() throws Exception {
        int assetId = anyInt();
        AssetImpl asset = new AssetImpl("Test Asset 1", Type.PROPERTY, "Asset 1", "Tom", "Tom");
        List<ValuationImpl> valuations = new ArrayList<>();
        valuations.add(new ValuationImpl(TestHelper.TEST_DATE, TestHelper.TEST_VALUE_100, asset));
        valuations.add(new ValuationImpl(TestHelper.TEST_DATE_2, TestHelper.TEST_VALUE_100, asset));
        valuations.add(new ValuationImpl(TestHelper.TEST_DATE_3, TestHelper.TEST_VALUE_100, asset));
        when(assetValuationUpdaterService.getTop30AssetValuations(assetId)).thenReturn(valuations);
        mockMvc.perform(get("/valuations/assets/{assetsId}", assetId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * Test getting the valuations that are linked to an asset.
     * @throws Exception exception
     */
    @Test
    void testSaveValuations() throws Exception {
        int assetId = anyInt();
        AssetImpl asset = new AssetImpl("Test Asset 1", Type.PROPERTY, "Asset 1", "Tom", "Tom");
        List<ValuationImpl> valuations = new ArrayList<>();
        for (int i = 0; i <= 40; i++) {
            valuations.add(new ValuationImpl(TestHelper.TEST_DATE.plusDays(i), i * 1000, asset));
        }
        when(assetValuationUpdaterService.saveAssetValuations(assetId, anyList())).thenReturn(valuations);
        mockMvc.perform(post("/valuations/assets/{assetsId}/valuations", assetId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(valuations)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    void testGetTop30ValuationsByAsset() throws Exception {
        AssetImpl asset = new AssetImpl("Test Asset 1", Type.PROPERTY, "Asset 1", "Tom", "Tom");
        List<ValuationImpl> valuations = new ArrayList<>();
    }

}
