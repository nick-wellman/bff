package com.nickwellman.bff.wine;

import com.nickwellman.bff.collection.model.WineFriendsRequest;
import com.nickwellman.bff.collection.model.WineNoteRequest;
import com.nickwellman.bff.collection.model.WineRatingEditRequest;
import com.nickwellman.bff.collection.model.WineRatingRequest;
import com.nickwellman.bff.collection.model.WineRequest;
import com.nickwellman.bff.collection.model.WineryRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class WineService {

    private WineClient client;

    public WineService(WineClient client) {
        this.client = client;
    }

    public List<Object> getWineries() {
        return client.getWineries();
    }

    public Object getWineryById(final String id) {
        return client.getWineryById(id);
    }

    public Object getWines(final String wineId, final String wineryId) {
        return client.getWines(wineId, wineryId);
    }

    public Object getWineNotes(final String user, final Integer wineId) {
        return client.getWineNotes(user, wineId);
    }

    public List<Object> getWineRating(final String user, final Integer wineId) {
        return client.getWineRating(user, wineId);
    }

    public Object addWine(final WineRequest wineRequest) {
        return client.addWine(wineRequest);
    }

    public Object addWinery(final WineryRequest wineryRequest) {
        return client.addWinery(wineryRequest);
    }

    public Object addWineNotes(final WineNoteRequest wineNoteRequest) {
        return client.addWineNotes(wineNoteRequest);
    }

    public Object addWineRating(final WineRatingRequest request) {
        return client.addWineRating(request);
    }

    public Object editWineRating(final WineRatingEditRequest request) {
        return client.editWineRating(request);
    }

    public Object getWineFriends(final WineFriendsRequest request) {
        return client.getWineRatingByUsersByWineId(request);
    }

    public Object addWineImage(final String contentType, final int wineId, final String label, final MultipartFile imageFile) {
        return client.addWineImage(Map.of("content-type", contentType), wineId, label, imageFile);
    }

    public Object getWineImages(final int wineId) {
        return client.getWineImages(wineId);
    }

}
