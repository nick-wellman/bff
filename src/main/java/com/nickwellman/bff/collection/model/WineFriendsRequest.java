package com.nickwellman.bff.collection.model;

import lombok.Data;

import java.util.List;

@Data
public class WineFriendsRequest {
    private List<String> users;
    private List<Integer> wineIds;
}
