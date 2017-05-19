package ch.bfh.bti7081.s2017.yellow.entities.wiki;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.person.User;

import java.util.List;

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
    private List<WikiEntry> wikiEntry;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

	public List<WikiEntry> getWikiEntry() {
		return wikiEntry;
	}

	public void setWikiEntry(List<WikiEntry> wikiEntry) {
		this.wikiEntry = wikiEntry;
	}

}
