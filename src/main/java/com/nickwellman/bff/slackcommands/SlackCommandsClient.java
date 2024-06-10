package com.nickwellman.bff.slackcommands;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "slack-commands", url = "${slack-commands.url}")
public interface SlackCommandsClient {

    @PostMapping(value = "/api/v1/slack-commands/notion-scanner", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    Object notionScanner(@RequestHeader Map<String, String> headers, MultiValueMap<String, Object> request);
}
