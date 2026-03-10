package de.ait_tr.supplier.service.interfaces;

import java.util.Map;

public interface SupplyRequestService {

    void sendSupplyRequest(Map<String, Integer> supplyRequest);
}