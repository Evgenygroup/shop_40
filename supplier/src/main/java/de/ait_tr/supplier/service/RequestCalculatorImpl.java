package de.ait_tr.supplier.service;

import de.ait_tr.g_40_shop.domain.dto.ProductSupplyDto;
import de.ait_tr.supplier.service.interfaces.RequestCalculator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RequestCalculatorImpl implements RequestCalculator {

    // Такое количество товара мы должны постоянно поддерживать на складе в магазине
    private final Map<String, Integer> requiredQuantities = Map.of(
            "Banana", 18,
            "Apple", 19,
            "Orange", 15,
            "Coconut", 12,
            "Peach", 22,
            "Pineapple", 14,
            "Cherry", 20
    );

    @Override
    public Map<String, Integer> calculateRequest(List<ProductSupplyDto> products) {
        return products.stream()
                .collect(Collectors.toMap(
                        ProductSupplyDto::getTitle,
                        x -> requiredQuantities.get(x.getTitle()) - x.getQuantity()
                ));
    }
}