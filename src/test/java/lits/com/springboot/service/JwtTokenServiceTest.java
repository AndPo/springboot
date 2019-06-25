package lits.com.springboot.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lits.com.springboot.service.impl.JwtTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class JwtTokenServiceTest {

    @InjectMocks
    TokenService tokenService =new JwtTokenService("Secret");


    @Test(expected = BadCredentialsException.class)
    public void shoudParseToken() {
        assertEquals(new Long(1),
        tokenService.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWRDbGFpbSI6MX0.zexNIcgDbb4oMK1ddum8c8OGd0g6b_9kT1GPbxgDQM8"));
        tokenService.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE1NDg0MDU1NDIsImV4cCI6MTU3OTg1NTE0MiwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsImVtYWlsIjoianJvY2tldEBleGFtcGxlLmNvbSIsIlJvbGUiOiJNQU5BR0VSIiwicGFzc3dvcmQiOiJqcm9ja2V0In0.t5XKBEjWLwd-_ryCzknpuRQkRh-bNwTKqe2eCnToJHg");

        //todo add assert to JwtException and ExpiredJwtException. Ask teachers about assert Exceptions or same method without annotation value in @Test
}

    @Test
    public void shouldCreateToken() {
        String secret = "Secret";
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);

        assertEquals(Jwts.builder()
                .claim("userIdClaim", 1L)
                .signWith(SignatureAlgorithm.HS256, secretBytes)
                .compact(), tokenService.createToken(1L));
    }

}
