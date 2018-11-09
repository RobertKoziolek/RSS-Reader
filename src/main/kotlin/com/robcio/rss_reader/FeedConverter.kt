package com.robcio.rss_reader

import com.robcio.rss_reader.model.FeedModel
import com.robcio.rss_reader.model.FeedModelBuilder
import com.rometools.rome.feed.synd.SyndFeed
import javafx.scene.image.Image
import org.apache.commons.lang3.StringUtils
import java.util.*

class FeedConverter {

    private val htmlExtractor = HtmlExtractor()

    fun convert(feed: SyndFeed): List<FeedModel> {
        val feedModels = LinkedList<FeedModel>()

        for (entry in feed.entries) {
            if (StringUtils.isEmpty(entry.title)) {
                continue
            }
            val description = entry.description.value
            val imgUrl = htmlExtractor.extract(description)
            val text = htmlExtractor.text(description)
            val feedModel = FeedModelBuilder.feedModel()
                .withTitle(entry.title.replace("\n", ""))
                .withLink(entry.link)
                .withImg(Image(imgUrl))
                .withContent(text)
                .build()
            feedModels.add(feedModel)
        }

        return feedModels
    }

}
