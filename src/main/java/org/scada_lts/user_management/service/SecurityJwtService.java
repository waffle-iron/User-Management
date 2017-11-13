package org.scada_lts.user_management.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityJwtService {

    private final static String ISSUER = "UserDto-Management";
    private final static Long TIME_EXPIRATION = 200_000L;
    private final static String SUBJECT = "UM";

    public String generateToken(String id ) {

        try {
            //The JWT signature algorithm we will be using to sign the token
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            //We will sign our JWT with our ApiKey secret
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("test");
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            //Let's set the JWT Claims
            JwtBuilder builder = Jwts.builder().setId(id)
                    .setIssuedAt(now)
                    .setSubject(SUBJECT)
                    .setIssuer(ISSUER)
                    .signWith(signatureAlgorithm, signingKey);

            //if it has been specified, let's add the expiration
            if (TIME_EXPIRATION >= 0) {
                long expMillis = nowMillis + TIME_EXPIRATION;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp);
            }

            //Builds the JWT and serializes it to a compact, URL-safe string
            return builder.compact();
        } catch (Exception e) {
            //LOG.error(e);
            return "";
        }
    }

    public boolean checkJwt(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary("test"))
                    .parseClaimsJws(jwt).getBody();
            System.out.println("ID: " + claims.getId());
            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Issuer: " + claims.getIssuer());
            System.out.println("Expiration: " + claims.getExpiration());

            return claims.getExpiration().getTime() > new Date().getTime();
        } catch (Exception e) {
            // LOG.error(e);
            return false;
        }
    }

}
