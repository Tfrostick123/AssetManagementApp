package com.frostick.assetmanagement.service;

import com.frostick.assetmanagement.data.asset.impl.AssetImpl;
import com.frostick.assetmanagement.data.valuation.impl.ValuationImpl;
import com.frostick.assetmanagement.data.valuation.impl.ValuationStatsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service("assetValuationService")
@RequiredArgsConstructor
public class AssetValuationUpdaterService {
    private final AssetService assetService;

    private final ValuationService valuationService;

    /**
     * Save the valuation passed in.
     * @param valuation the valuation
     * @return the valuation saved
     */
    public ValuationImpl saveAssetValuation(int assetId, ValuationImpl valuation) {
        AssetImpl asset = assetService.getById(assetId);
        valuation.setAsset(asset);
        return valuationService.saveValuation(valuation);
    }

    public List<ValuationImpl> saveAssetValuations(int assetId, List<ValuationImpl> valuations) {
        return valuations.stream()
                .map(v -> saveAssetValuation(assetId, v))
                .collect(Collectors.toList());
    }

    /**
     * Get the asset valuation stats for the passed in asset id.
     * @param assetId the id
     * @return the Valuations Stats object
     */
    public List<ValuationImpl> getTop30AssetValuations(int assetId) {
        AssetImpl asset = assetService.getById(assetId);
        return valuationService.getTop30AssetValuations(asset);
    }

    /**
     * Get the asset valuation stats for th passed in asset id.
     * @param assetId the id
     * @return the Valuations Stats object
     */
    public ValuationStatsImpl getAssetValuationStats(int assetId) {
            List<ValuationImpl> valuations = getTop30AssetValuations(assetId);
            if (!valuations.isEmpty()) {
                double currentValue = 0d;
                Double change1d = null;
                Double average7d = null;
                Double change7d = null;
                Double averageTotal = null;
                Double changeTotal = null;
                int totalDayCount = valuations.size();
                double total = 0;
                for (int i = 0; i < totalDayCount; i++) {
                    double value = valuations.get(i).getValue();
                    total += value;
                    if (i == 0) {
                        currentValue = total;
                    } else if (i == 1) {
                        change1d = value - currentValue;
                    } else if (i == 6) {
                        average7d = total /  i + 1;
                        change7d = value - currentValue;
                    } else if (i == totalDayCount - 1) {
                        averageTotal = total / totalDayCount;
                        changeTotal = value - currentValue;
                    }
                }
                return new ValuationStatsImpl(currentValue, average7d, averageTotal, change7d,
                        changeTotal, change1d, totalDayCount);
            } else {
                throw new RuntimeException(format("No valuations exist for asset id: [%s]", assetId));
            }
    }

//    public HashMap<LocalDate, Double> getUtilisationBreaches(LinkedHashMap<LocalDate, Double> schedule,
//                                                             Map<LocalDate, Double> existingUtilisation,
//                                                             Map<LocalDate, Double> dealUtilisation) {
//        return Stream.of(existingUtilisation, dealUtilisation)
//                .flatMap(map -> map.entrySet().stream())
//                .filter(entry -> isInBreach(entry, schedule))
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey(),
//                        Map.Entry::getValue(),
//                        (v1, v2) -> v1 + v2);
//    }

    public boolean isInBreach(final Map.Entry<LocalDate, Double> entry,
                              final LinkedHashMap<LocalDate, Double> schedule) {
        if (schedule.containsKey(entry.getKey())) {
            return schedule.get(entry.getKey()) - entry.getValue() > 0;
        }
        return false;
    }
}
