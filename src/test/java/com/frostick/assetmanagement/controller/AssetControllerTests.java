package com.frostick.assetmanagement.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frostick.assetmanagement.data.asset.Type;
import com.frostick.assetmanagement.data.asset.impl.AssetImpl;
import com.frostick.assetmanagement.service.AssetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for the Asset Controller.
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ImportAutoConfiguration(WebMvcAutoConfiguration.class)
@WebMvcTest(AssetController.class)
public class AssetControllerTests {
    /**
     * Mock Asset service.
     */
    @MockBean(name="assetService")
    private AssetService assetService;
    /**
     * Mock Mvc to assist with the Restful API calls.
     */
    @Autowired
    private MockMvc mockMvc;
    /**
     * Object Mapper.
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test the get assets Rest call.
     * @throws Exception exception
     */
    @Test
    void testGetAssets() throws Exception {
        AssetImpl asset = new AssetImpl("Test Asset 1", Type.PROPERTY, "Asset 1", "Tom", "Tom");
        AssetImpl asset2 = new AssetImpl("Test Asset 2", Type.PROPERTY, "Asset 2", "Tom", "Tom");
        AssetImpl asset3 = new AssetImpl("Test Asset 3", Type.PROPERTY, "Asset 3", "Tom", "Tom");
        List<AssetImpl> assets = new ArrayList<>(Arrays.asList(asset, asset2, asset3));
        when(assetService.getAll()).thenReturn(assets);
        mockMvc.perform(get("/assets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(assets.size()))
                .andDo(print());
    }

    /**
     * Test the get asset Rest call.
     * @throws Exception exception
     */
    @Test
    void testGetAsset() throws Exception {
        int id = anyInt();
        AssetImpl asset = new AssetImpl("Test Asset 1", Type.PROPERTY, "Asset 1", "Tom", "Tom");
        when(assetService.getById(id)).thenReturn(asset);
        mockMvc.perform(get("/assets/asset/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(asset.getName()))
                .andExpect(jsonPath("$.type").value(asset.getType().name()))
                .andExpect(jsonPath("$.description").value(asset.getDescription()))
                .andExpect(jsonPath("$.modifiedBy").value(asset.getModifiedBy()))
                .andDo(print());
    }

    /**
     * Test the save asset Rest call.
     * @throws Exception exception
     */
    @Test
    void testSaveAsset() throws Exception {
        AssetImpl asset = new AssetImpl("Test Asset 1", Type.PROPERTY, "Asset 1", "Tom", "Tom");
        when(assetService.saveAsset(asset)).thenReturn(asset);
        mockMvc.perform(post("/assets/asset")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(asset)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * Test the update asset rest call
     * @throws Exception exception
     */
    @Test
    void testUpdateAsset() throws Exception {
        int id = anyInt();
        AssetImpl assetUpdated = new AssetImpl("Asset 1 updated", Type.PROPERTY, "Asset 1 updated", "Tom", "Updater");
        when(assetService.updateAsset(id, eq(assetUpdated))).thenReturn(assetUpdated);
        mockMvc.perform(put("/assets/asset/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(assetUpdated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(assetUpdated.getName()))
                .andExpect(jsonPath("$.type").value(assetUpdated.getType().name()))
                .andExpect(jsonPath("$.description").value(assetUpdated.getDescription()))
                .andExpect(jsonPath("$.modifiedBy").value(assetUpdated.getModifiedBy()))
                .andDo(print());
    }

}
