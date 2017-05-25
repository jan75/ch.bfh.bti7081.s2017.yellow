package ch.bfh.bti7081.s2017.yellow.presenters;

import java.util.Date;

/**
 * Created by taahuem2 on 25.05.17.
 */
public class WikiPresenter {

    private class WikiEntry {

        String title;
        String content;
        String category;
        Date createdAt;
        Date modifiedAt;
        String createdFrom;
        String modifiedFrom;

        public WikiEntry (String title, String content, String category, String createdFrom) {
            this.title = title;
            this.content = content;
            this.category = category;
            this.createdAt = new Date();
            this.modifiedAt = this.createdAt;
            this.createdFrom = createdFrom;
            this.modifiedFrom =  this.createdFrom;
        }


    }

    WikiEntry we = new WikiEntry("Husten", "Husten ist schlecht für den Rachen.", "allgemeine Meidzin", "Oberchefbossartzt Wehrlein");
}
