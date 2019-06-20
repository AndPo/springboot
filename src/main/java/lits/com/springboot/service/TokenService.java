package lits.com.springboot.service;


public interface TokenService {
    Long parseToken(String token);

    String createToken(Long id);
}
