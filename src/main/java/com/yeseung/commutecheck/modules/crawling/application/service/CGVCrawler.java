package com.yeseung.commutecheck.modules.crawling.application.service;

import com.yeseung.commutecheck.modules.crawling.domain.Movie;
import com.yeseung.commutecheck.modules.crawling.properties.CrawlerProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CGVCrawler {

    private final CrawlerProperties crawlerProperties;

    public List<Movie> getMovies() throws IOException {
        Document doc = Jsoup.connect(crawlerProperties.getCgv().getUrl().getMovie()).get();

        Elements movieElements = doc.select("div.sect-movie-chart li");
        List<Movie> movies = new ArrayList<>();
        for (Element item : movieElements) {
            Element boxImage = item.selectFirst("div.box-image");
            Element boxContents = item.selectFirst("div.box-contents");
            if (boxContents == null || boxImage == null) {
                continue;
            }
            String rank = Objects.requireNonNull(boxImage.selectFirst("strong.rank")).text().trim();
            String detailLink =
                crawlerProperties.getCgv().getUrl().getDefaultUrl() + Objects.requireNonNull(boxImage.selectFirst("a")).attr("href");
            String imageLink = Objects.requireNonNull(boxImage.selectFirst("img")).attr("src");

            String title = Objects.requireNonNull(boxContents.selectFirst("strong.title")).text().trim();
            String percent = Objects.requireNonNull(boxContents.selectFirst("div.score > strong.percent")).text();
            String txtInfo = Objects.requireNonNull(boxContents.selectFirst("span.txt-info")).text().replaceAll("\\s+", " ");
            String reservationLink =
                crawlerProperties.getCgv().getUrl().getDefaultUrl()
                    + Objects.requireNonNull(boxContents.selectFirst("a.link-reservation")).attr("href");
            DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            movies.add(new Movie(null,
                                 LocalDate.now().format(ofPattern),
                                 rank,
                                 title,
                                 percent,
                                 txtInfo,
                                 detailLink,
                                 imageLink,
                                 txtInfo,
                                 reservationLink,
                                 null));
        }
        return movies;
    }

}
