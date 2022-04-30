package com.idigital.epam.energy.hmac;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Component
public class HMACUtils implements HMACUtilsService {

    /**
     * To calculate HASH signature
     */


    public static String calculateHASH(String keyId, String timestamp, String action, String secretKey) {
        String data = "keyId=" + keyId + ";timestamp=" + timestamp + ";action=" + action;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            return new String(Base64.getEncoder().encode(rawHmac));
        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException();
        }



    }


    public ResponseEntity<?> getRequestWithHmac(String keyId, String action, String path, String secretKey) throws URISyntaxException {
        String time = String.valueOf(new Date().getTime() + 300000);
        String signature = calculateHASH(keyId, time, action, secretKey);
        WebClient webClient = WebClient.create();
        return webClient.get().uri(new URI(path))
                .header("sm-keyId", keyId)
                .header("sm-timestamp", time)
                .header("sm-action", action)
                .header("sm-signature", signature)
                .retrieve()
                .toEntity(String.class)
                .block();



    }

    @Override
    public ResponseEntity<?> postRequestWithHmac(String keyId, String action, String path, String secretKey, Object object) throws URISyntaxException, Exception {
        String time = String.valueOf(new Date().getTime() + 300000);
        String signature = calculateHASH(keyId, time, action, secretKey);
        WebClient webClient = WebClient.create();
        return webClient.post().uri(new URI(path))
                .bodyValue(object)
                .header("sm-keyId", keyId)
                .header("sm-timestamp", time)
                .header("sm-action", action)
                .header("sm-signature", signature)
                .retrieve()
                .toEntity(String.class)
                .block();
    }
    public static void main (String[] args){
        long now = new Date().getTime() +86400;
        System.out.println("Now timestamp is : " + now);
        String s = HMACUtils.calculateHASH("ENERGY", String.valueOf(now),"payment","energyKey");
        System.out.println("signature: " + s);
    }

}



