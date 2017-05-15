package ch.bfh.bti7081.s2017.yellow.entities.wiki;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.person.User;

import javax.persistence.*;

/**
 * Created by dario on 09.05.2017.
 */
@Entity
@Table(name="WIKI")
public class Wiki implements Storable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="WIKI_ENTRY")
    @OneToMany
    private WikiEntry wikiEntry;

    @Column(name="USER")
    @ManyToOne
    private User user;

    public Wiki(WikiEntry wikiEntry, User user) {
        this.wikiEntry = wikiEntry;
        this.user = user;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public WikiEntry getWikiEntry() {
        return wikiEntry;
    }

    public void setWikiEntry(WikiEntry wikiEntry) {
        this.wikiEntry = wikiEntry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
