package uz.pdp.cityapartmentservice.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.pdp.cityapartmentservice.domain.dto.UserDto;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final RestTemplate restTemplate;
    @Value("${services.user-service-url}")
    private String userURL;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(username,httpHeaders);
        return restTemplate.exchange(URI.create(userURL + "/api/v1/auth/get"), HttpMethod.GET,entity, UserDto.class).getBody();
    }
}