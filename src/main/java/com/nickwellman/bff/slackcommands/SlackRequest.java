package com.nickwellman.bff.slackcommands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlackRequest {
    String token;
    String team_id;
    String team_domain;
    String channel_id;
    String channel_name;
    String user_id;
    String user_name;
    String command;
    String text;
    String api_app_id;
    String is_enterprise_install;
    String response_url;
    String trigger_id;

    public MultiValueMap<String, String> convertToFormData() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("token", token);
        body.add("team_id", team_id);
        body.add("team_domain", team_domain);
        body.add("channel_id", channel_id);
        body.add("channel_name", channel_name);
        body.add("user_id", user_id);
        body.add("user_name", user_name);
        body.add("command", command);
        body.add("text", text);
        body.add("api_app_id", api_app_id);
        body.add("is_enterprise_install", is_enterprise_install);
        body.add("response_url", response_url);
        body.add("trigger_id", trigger_id);

        return body;
    }
}
