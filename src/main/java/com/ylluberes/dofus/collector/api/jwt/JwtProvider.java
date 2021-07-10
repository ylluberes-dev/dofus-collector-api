package com.ylluberes.dofus.collector.api.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.security.*;


/**
 * TODO: Check handler of exception [IOException][KeyStoreException][NoSuchAlgorithmException][CertificateException]
 */
@Service
public class JwtProvider {

    private KeyStore keyStore;

    @PostConstruct
    public void init () {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceStream,"secret".toCharArray());
        }catch (Exception e) {
            System.out.println("Error loading the key: "+e.getMessage());
        }
    }
    public String generateToken(Authentication authentication) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
       return Jwts.builder().setSubject(principal.getUsername())
               .signWith(getPrivateKey()).compact();
    }

    private PrivateKey getPrivateKey () {
        try {
            return (PrivateKey) keyStore.getKey("springblog","secret".toCharArray());
        }catch (Exception ex) {
            System.out.println("Error occurred getting the private key "+ex.getMessage());
        }
        return null;
    }
}
