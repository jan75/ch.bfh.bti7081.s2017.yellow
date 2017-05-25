package ch.bfh.bti7081.s2017.yellow.entities.wiki;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.person.User;

import javax.persistence.*;

/**
 * Created by dario on 09.05.2017.
 */
@Entity
@Table(name="WIKI_ENTRY")
public class WikiEntry implements Storable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @ManyToOne
    private Wiki wiki;

    @Column(name="CAPTION")
    private String caption;

    @Column(name="ENTRY")
    private String entry;

    @ManyToOne
    private User user;
    
    public WikiEntry(Wiki wiki, String caption, String entry) {
        this.wiki = wiki;
        this.caption = caption;
        this.entry = entry;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}