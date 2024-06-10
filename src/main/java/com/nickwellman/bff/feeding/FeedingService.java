package com.nickwellman.bff.feeding;

import com.nickwellman.bff.feeding.model.FeedRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedingService {

    private final FeedingClient client;

    public FeedingService(final FeedingClient client) {
        this.client = client;
    }

    public Object putFeed(final FeedRequest request) {
        return client.putFeed(request);
    }

    public Object putFeeds(final List<FeedRequest> requests) {
        return client.putFeeds(requests);
    }

    public Object getFeeds() {
        return client.getFeeds();
    }
}
