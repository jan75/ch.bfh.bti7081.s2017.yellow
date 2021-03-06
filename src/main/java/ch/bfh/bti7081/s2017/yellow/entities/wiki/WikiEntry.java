package ch.bfh.bti7081.s2017.yellow.entities.wiki;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.person.User;
import javax.persistence.*;
import java.util.Date;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private Wiki wiki;

    @Column(name="CAPTION")
    private String caption;

    @Lob
    @Column(name="ENTRY")
    private String entry;

    @Column(name="CATEGORY")
    private String category;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;


    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    
    public WikiEntry(Wiki wiki, String caption, String entry, String category) {
        this.wiki = wiki;
        this.caption = caption;
        this.entry = entry;
        this.category = category;
        this.createdAt = new Date(System.currentTimeMillis()-7*24*60*60*1000);;
        this.updatedAt = this.createdAt;
    }

    public WikiEntry() {

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
    public void setEntry(String entry) { this.entry = entry; }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    public User getUser(){
        return this.user;
    }
    public void setUser(User user){
        this.user = user;
    }
}
