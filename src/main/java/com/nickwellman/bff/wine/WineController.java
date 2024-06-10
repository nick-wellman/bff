package com.nickwellman.bff.wine;

import com.nickwellman.bff.collection.model.WineFriendsRequest;
import com.nickwellman.bff.collection.model.WineNoteRequest;
import com.nickwellman.bff.collection.model.WineRatingEditRequest;
import com.nickwellman.bff.collection.model.WineRatingRequest;
import com.nickwellman.bff.collection.model.WineRequest;
import com.nickwellman.bff.collection.model.WineryRequest;
import com.nickwellman.bff.configuration.ProjectConstants;
import com.nickwellman.bff.session.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
public class WineController {

    private final WineService service;

    private final SessionManager sessionManager;

    public WineController(final WineService service, final SessionManager sessionManager) {
        this.service = service;
        this.sessionManager = sessionManager;
    }

    /*
    Winery
     */
    @GetMapping("/api/v1/wineries")
    public List<Object> getWineries() {
        return service.getWineries();
    }

    @GetMapping("/api/v1/wineries/{id}")
    public Object getWineryById(@PathVariable final String id) {
        return service.getWineryById(id);
    }

    @PutMapping("/api/v1/wineries")
    public Object addWinery(@RequestBody final WineryRequest wineryRequest) {
        return service.addWinery(wineryRequest);
    }

    /*
    Wines
     */
    @GetMapping("/api/v1/wines")
    public Object getWines(@RequestParam("wineId") final Optional<String> wineId, @RequestParam("wineryId") final Optional<String> wineryId) {
        return service.getWines(wineId.orElse(null), wineryId.orElse(null));
    }

    @PutMapping("/api/v1/wines")
    public Object addWine(@RequestBody final WineRequest wineRequest) {
        return service.addWine(wineRequest);
    }

    /*
    Wine Notes
     */
    @GetMapping("/api/v1/wineNotes")
    public Object getWineNotes(@RequestHeader(ProjectConstants.AUTH_TOKEN) String token,
                               @RequestParam("user") final Optional<String> user,
                               @RequestParam("wineId") final Optional<Integer> wineId) {
        return service.getWineNotes(user.orElse(null), wineId.orElse(null));
    }

    @PutMapping("/api/v1/wineNotes")
    public Object addWineNote(@RequestBody final WineNoteRequest wineNoteRequest) {
        return service.addWineNotes(wineNoteRequest);
    }

    /*
    Wine Rating
     */
    @GetMapping("/api/v1/wineRating")
    public Object getWineRating(@RequestHeader(ProjectConstants.AUTH_TOKEN) String token,
                                @RequestParam("user") final Optional<String> user,
                                @RequestParam("wineId") final Optional<Integer> wineId) {
        return service.getWineRating(user.orElse(sessionManager.getSessionDetails(token).getUsername()), wineId.orElse(null));
    }

    @PutMapping("/api/v1/wineRating")
    public Object addWineRating(@RequestBody final WineRatingRequest request) {
        return service.addWineRating(request);
    }

    @PutMapping("/api/v1/wineRating/edit")
    public Object editWineRating(@RequestBody final WineRatingEditRequest request) {
        return service.editWineRating(request);
    }

    @PostMapping("/api/v1/wineRating")
    public Object getWineRatingByUsersByWineIds(@RequestBody final WineFriendsRequest request) {
        return service.getWineFriends(request);
    }

    /*
    Wine Image
     */
    @PutMapping("/api/v1/wineImages")
    public Object addWineImage(@RequestParam("wineId") final int wineId,
                               @RequestParam("label") final String label,
                               @RequestParam("imageFile") final MultipartFile imageFile,
                               @RequestHeader final Map<String, String> headers) {
        return service.addWineImage(headers.get("content-type"), wineId, label, imageFile);
    }

    @GetMapping("/api/v1/wineImages")
    public Object getWineImages(@RequestParam("wineId") final int wineId) {
        return service.getWineImages(wineId);
    }

}
