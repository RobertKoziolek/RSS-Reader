package com.robcio.rss_reader.gui;

import com.robcio.rss_reader.feed.FeedDownloader;
import com.robcio.rss_reader.model.FeedModel;
import com.rometools.rome.io.FeedException;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.List;

public class ChannelMenu extends Menu {

    private final FeedDownloader feedDownloader;
    private final FeedList listView;

    public ChannelMenu(final FeedDownloader feedDownloader, final FeedList listView) {
        super("Kanał");
        this.feedDownloader = feedDownloader;
        this.listView = listView;
        add("RMF Kraj", "https://www.rmf24.pl/fakty/polska/feed");
        add("RMF Świat", "https://www.rmf24.pl/fakty/swiat/feed");
        add("RMF Ekonomia", "https://www.rmf24.pl/ekonomia/feed");
        add("TVN24 Najważniejsze", "https://www.tvn24.pl/najwazniejsze.xml");
    }

    private void add(final String label, final String url) {
        final MenuItem menuItem = new MenuItem(label);
        menuItem.setOnAction(event -> {
            try {
                final List<FeedModel> feedList = feedDownloader.getFeed(url);
                listView.fill(feedList);
            } catch (FeedException e) {
                System.out.println("Could not use provided URL");
            }
        });
        getItems().add(menuItem);
    }
}
