package com.robcio.rss_reader.gui;

import com.robcio.rss_reader.model.FeedModel;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import static com.robcio.rss_reader.constants.Numeral.*;


public class FeedItemCell extends ListCell<FeedModel> {

    private final ImageView imageView;
    private final Label headerLabel;


    FeedItemCell() {
        super();
        this.imageView = new ImageView();
        this.headerLabel = new Label();
        this.headerLabel.setFont(Font.font(18));
        this.headerLabel.setGraphic(this.imageView);

        setThumbnailSize(THUMBNAIL_SMALL_WIDTH, THUMBNAIL_SMALL_HEIGHT);
    }

    @Override
    protected void updateItem(final FeedModel item, final boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            imageView.setImage(null);
            setGraphic(null);
            setText(null);
        } else {
            final VBox vBox = new VBox();
            imageView.setImage(item.getImg());
            headerLabel.setText(item.getTitle());

            final ObservableList<Node> vBoxChildren = vBox.getChildren();
            vBoxChildren.add(headerLabel);

            headerLabel.setOnMouseClicked(event -> {
                item.setExpanded(!item.isExpanded());
                if (item.isExpanded()) {
                    vBoxChildren.add(new Label(item.getContent()));
                    setThumbnailSize(THUMBNAIL_LARGE_WIDTH, THUMBNAIL_LARGE_HEIGHT);
                } else {
                    vBoxChildren.remove(1, vBoxChildren.size());
                    setThumbnailSize(THUMBNAIL_SMALL_WIDTH, THUMBNAIL_SMALL_HEIGHT);
                }
            });
            setGraphic(vBox);
        }
    }

    private void setThumbnailSize(final double thumbnailSmallWidth, final double thumbnailSmallHeight) {
        imageView.setFitWidth(thumbnailSmallWidth);
        imageView.setFitHeight(thumbnailSmallHeight);
    }
}