package com.robcio.rss_reader.feed;

import com.robcio.rss_reader.FeedConverter;
import com.robcio.rss_reader.model.FeedModel;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FeedDownloader {

    public List<FeedModel> getFeed(final String url) throws FeedException {
        final FeedConverter feedConverter = new FeedConverter();
        try {
            final SyndFeedInput input = new SyndFeedInput();
            final SyndFeed feed = input.build(new XmlReader(new URL(url)));

            System.out.println(feed);

            return feedConverter.convert(feed);
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }
        throw new FeedException("Could not get feed from provided URL");
    }
}
