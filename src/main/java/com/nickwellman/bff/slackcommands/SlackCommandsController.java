package com.nickwellman.bff.slackcommands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Map;

@RestController
@Validated
@Slf4j
public class SlackCommandsController {

    private static final String SLACK_SIGNING_SECRET = "7dfc14b2e7810c268d2ef565878266d5";
    private static final String X_SLACK_SIGNATURE = "x-slack-signature";
    private static final String X_SLACK_SIGNATURE_TIMESTAMP = "x-slack-request-timestamp";

    private SlackCommandsClient client;

    public SlackCommandsController(SlackCommandsClient client) {
        this.client = client;
    }

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    private static String calculateHMAC(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSha256");
        Mac mac = Mac.getInstance("HmacSha256");
        mac.init(secretKeySpec);
        return toHexString(mac.doFinal(data.getBytes()));
    }

    private static boolean verifyRequest(Map<String, String> headers, String request) throws NoSuchAlgorithmException, InvalidKeyException {
        String signature = headers.get(X_SLACK_SIGNATURE);
        String timestamp = headers.get(X_SLACK_SIGNATURE_TIMESTAMP);
        String data = "v0:" + timestamp + ":" + request;
        String hmac = calculateHMAC(data, SLACK_SIGNING_SECRET);
        hmac = "v0=" + hmac;
        return signature.equals(hmac);
    }

    @PostMapping(value = "/api/v1/slack-commands/notion-scanner", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object notionScanner(@RequestHeader final Map<String, String> headers,
                                @RequestBody String request) throws NoSuchAlgorithmException, InvalidKeyException {
        if (verifyRequest(headers, request)) {
            String[] tokens = request.split("\\&");
            MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<>();
            for (String s : tokens) {
                String[] t = s.split("=");
                requestMap.add(t[0], t[1]);
            }
            return client.notionScanner(Map.of("content-type", "application/x-www-form-urlencoded"), requestMap);
        }
        log.warn("signature did not match hmac.  return null");
        return null;
    }
}
