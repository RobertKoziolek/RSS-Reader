package com.robcio.rss_reader

import org.jsoup.Jsoup

import java.util.Objects

class HtmlExtractor {

    fun extract(htmlContent: String): String? {
        val document = Jsoup.parse(htmlContent)
        val img = document.select("img").first()
        return if (Objects.isNull(img)) null else img.attr("src")
    }

    fun text(htmlContent: String): String {
        return Jsoup.parse(htmlContent).text()
    }
}
