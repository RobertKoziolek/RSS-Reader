package com.robcio.rss_reader.model;

import javafx.scene.image.Image;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FeedModelBuilder {

    private String title;
    private String link;
    private Image img;
    private String content;

    public static FeedModelBuilder feedModel() {
        return new FeedModelBuilder();
    }

    public FeedModelBuilder withTitle(final String title) {
        this.title = title;
        return this;
    }

    public FeedModelBuilder withLink(final String link) {
        this.link = link;
        return this;
    }

    public FeedModelBuilder withImg(final Image img) {
        this.img = img;
        return this;
    }

    public FeedModelBuilder withContent(final String content) {
        this.content = content;
        return this;
    }

    public FeedModel build() {
        return new FeedModel(this);
    }

}
