package com.frostick.assetmanagement.controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frostick.assetmanagement.TestHelper;
import com.frostick.assetmanagement.data.asset.Type;
import com.frostick.assetmanagement.data.asset.impl.AssetImpl;
import com.frostick.assetmanagement.data.valuation.impl.ValuationImpl;
import com.frostick.assetmanagement.repository.ValuationRepository;
import com.frostick.assetmanagement.service.ValuationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ValuationController.class)
public class ValuationControllerTests {
    @MockBean
    private ValuationService valuationService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetValuationsByAssetId() throws Exception {
        AssetImpl asset = new AssetImpl("Test Asset 1", Type.PROPERTY, "Asset 1", "Tom", "Tom");
        List<ValuationImpl> valuations = new ArrayList<>();
        valuations.add(new ValuationImpl(TestHelper.TEST_DATE, TestHelper.TEST_VALUE_100, asset));
        valuations.add(new ValuationImpl(TestHelper.TEST_DATE_2, TestHelper.TEST_VALUE_100, asset));
        valuations.add(new ValuationImpl(TestHelper.TEST_DATE_3, TestHelper.TEST_VALUE_100, asset));
        when(valuationService.getAssetValuations(1)).thenReturn(valuations);
        mockMvc.perform(get("valuations/assets/{assetsId}/valuations"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
