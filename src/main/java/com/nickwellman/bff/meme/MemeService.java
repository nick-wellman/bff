package com.nickwellman.bff.meme;

import com.nickwellman.bff.meme.model.CreateMemeRequest;
import com.nickwellman.bff.meme.model.MemeRequestRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemeService {

    private final MemeClient client;

    public MemeService(final MemeClient client) {
        this.client = client;
    }

    public List<Object> getAllMemes() {
        return client.getAllMemes();
    }

    public List<Object> getMemes(final String tag) {
        return client.getMemes(tag);
    }

    public void addMeme(final CreateMemeRequest request) {
        client.addMeme(request);
    }

    public void addMemeRequest(final MemeRequestRequest request) {
        client.addMemeRequest(request);
    }

    public List<Object> getFacets() {
        return client.getFacets();
    }
}
