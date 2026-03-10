package de.ait_tr.supplier.service;

import de.ait_tr.g_40_shop.domain.dto.ProductSupplyDto;
import de.ait_tr.g_40_shop.domain.entity.User;
import de.ait_tr.g_40_shop.security.sec_dto.TokenResponseDto;
import de.ait_tr.supplier.service.interfaces.HttpService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class HttpServiceImpl implements HttpService {

    private final RestTemplate template = new RestTemplate();

    @Override
    public List<ProductSupplyDto> getAvailableProducts() {
        String token = login();

        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", List.of(token));
        HttpEntity<Void> request = new HttpEntity<>(headers);
        String url = "http://localhost:8080/system/products";

        ResponseEntity<ProductSupplyDto[]> response = template
                .exchange(url, HttpMethod.GET, request, ProductSupplyDto[].class);

        if (!response.hasBody()) {
            throw new RuntimeException("Supply response has no body");
        }

        return Arrays.asList(response.getBody());

    }

    private String login() {
        User user = new User();
        user.setUsername("T");
        user.setPassword("1");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        String url = "http://localhost:8080/auth/login";

        ResponseEntity<TokenResponseDto> response = template
                .postForEntity(url, request, TokenResponseDto.class);

        if (!response.hasBody()) {
            throw new RuntimeException("Auth response has no body");
        }

        // Bearer 897fhbsfsdf6fyushfbhbsfidyfusdhfbsdnf
        return "Bearer " + response.getBody().getAccessToken();
    }
}