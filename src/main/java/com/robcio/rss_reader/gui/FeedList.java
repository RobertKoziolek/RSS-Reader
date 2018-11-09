package com.robcio.rss_reader.gui;

import com.robcio.rss_reader.model.FeedModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.stream.Collectors;

public class FeedList extends ListView<FeedModel> {

    public FeedList() {
        setCellFactory(list -> new FeedItemCell());
    }

    public void fill(final List<FeedModel> modelList) {
        clear();
        final ObservableList<FeedModel> observableList = modelList.stream()
                                                                  .collect(Collectors.toCollection(FXCollections::observableArrayList));
        setItems(observableList);
    }

    private void clear() {
        setItems(null);
    }
}
