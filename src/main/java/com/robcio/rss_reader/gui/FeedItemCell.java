package com.robcio.rss_reader.gui;

import com.robcio.rss_reader.GuiLauncher;
import com.robcio.rss_reader.model.FeedModel;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import static com.robcio.rss_reader.constants.Numeral.*;

public class FeedItemCell extends ListCell<FeedModel> {

    private final ImageView imageView;
    private final Label headerLabel;

    FeedItemCell() {
        super();
        imageView = new ImageView();
        headerLabel = new Label();
        headerLabel.setFont(Font.font(18));
        headerLabel.setGraphic(imageView);
        headerLabel.setWrapText(true);
        headerLabel.setMaxWidth(WIDTH - THUMBNAIL_SMALL_WIDTH / 2);
        headerLabel.setTextAlignment(TextAlignment.JUSTIFY);

        setThumbnailSize(THUMBNAIL_SMALL_WIDTH, THUMBNAIL_SMALL_HEIGHT);
    }

    @Override
    protected void updateItem(final FeedModel item, final boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            imageView.setImage(null);
            setThumbnailSize(THUMBNAIL_SMALL_WIDTH, THUMBNAIL_SMALL_HEIGHT);
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
                    vBoxChildren.add(getDescriptionLabel(item));
                    setThumbnailSize(THUMBNAIL_LARGE_WIDTH, THUMBNAIL_LARGE_HEIGHT);
                } else {
                    vBoxChildren.remove(1, vBoxChildren.size());
                    setThumbnailSize(THUMBNAIL_SMALL_WIDTH, THUMBNAIL_SMALL_HEIGHT);
                }
            });
            setGraphic(vBox);
        }
    }

    private Label getDescriptionLabel(final FeedModel item) {
        final Label descriptionLabel = new Label(item.getContent());
        descriptionLabel.setMaxWidth(WIDTH - 25);
        descriptionLabel.setTextAlignment(TextAlignment.JUSTIFY);
        descriptionLabel.setWrapText(true);
        descriptionLabel.setOnMouseClicked(event -> {
            GuiLauncher.openUrlInBrowser(item.getLink());
        });
        return descriptionLabel;
    }

    private void setThumbnailSize(final double thumbnailSmallWidth, final double thumbnailSmallHeight) {
        imageView.setFitWidth(thumbnailSmallWidth);
        imageView.setFitHeight(thumbnailSmallHeight);
    }
}