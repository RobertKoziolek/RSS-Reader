package com.robcio.rss_reader.model;

import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class FeedModel {
    @Setter
    private boolean expanded;
    private String title;
    private String link;
    private Image img;
    private String content;

    public FeedModel(final FeedModelBuilder feedModelBuilder) {
        this.title = feedModelBuilder.getTitle();
        this.link = feedModelBuilder.getLink();
        this.img = feedModelBuilder.getImg();
        this.content = feedModelBuilder.getContent();
    }
}
