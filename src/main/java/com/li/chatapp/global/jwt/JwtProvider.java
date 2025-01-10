package com.li.chatapp.global.jwt;

import com.li.chatapp.domain.member.member.entity.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    @Value("${custom.jwt.secret-key}")
    private String secretKeyOrigin;

    @Value("${custom.jwt.access-token-expiration}")
    private int accessTokenExpiration;

    private SecretKey cachedSecretKey;

    private SecretKey getSecretKey() {
        if (cachedSecretKey == null) {
            cachedSecretKey = _getSecretKey();
        }
        return cachedSecretKey;
    }

    public SecretKey _getSecretKey() {
        String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyOrigin.getBytes());
        return Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
    }

    public String genAccessToken(Member member) {
        return genToken(member, accessTokenExpiration);
    }

    public String genRefreshToken(Member member) {
       return genToken(member, accessTokenExpiration);
    }

    public String genToken(Member member, int seconds) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("id", member.getId());
        claims.put("name", member.getName());
        long now = new Date().getTime();
        Date accessTokenExpiration = new Date(now + seconds * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(accessTokenExpiration)
                .signWith(_getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
