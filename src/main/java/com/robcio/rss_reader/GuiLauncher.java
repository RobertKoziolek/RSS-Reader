package com.robcio.rss_reader;

import com.robcio.rss_reader.feed.FeedDownloader;
import com.robcio.rss_reader.gui.ChannelMenu;
import com.robcio.rss_reader.gui.FeedList;
import com.robcio.rss_reader.model.FeedModel;
import com.rometools.rome.io.FeedException;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

import static com.robcio.rss_reader.constants.Numeral.HEIGHT;
import static com.robcio.rss_reader.constants.Numeral.WIDTH;

public class GuiLauncher extends Application {

    private static HostServices hostServices;

    public static void main(final String[] args) {
        launch(args);
    }

    public static void openUrlInBrowser(final String url) {
        hostServices.showDocument(url);
    }

    @Override
    public void start(final Stage primaryStage) throws FeedException {
        hostServices = getHostServices();
        primaryStage.setTitle("RSS-Reader!");

        final FeedDownloader feedDownloader = new FeedDownloader();
        final List<FeedModel> feedList = feedDownloader.getFeed("https://www.rmf24.pl/fakty/polska/feed");
        feedList.forEach(System.out::println);

        final FeedList listView = new FeedList();
        listView.fill(feedList);

        final VBox box = new VBox();
        box.getChildren()
                .add(listView);
        VBox.setVgrow(listView, Priority.ALWAYS);
        box.setFillWidth(true);

        final BorderPane root = new BorderPane();
        final MenuBar menuBar = new MenuBar(new ChannelMenu(feedDownloader, listView));
        root.setTop(menuBar);
        root.setCenter(listView);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }
}
