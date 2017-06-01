package ch.bfh.bti7081.s2017.yellow.entities.contacts;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dario on 09.05.2017.
 */
@Entity
@Table(name="CONTACT_BOOK")
public class ContactBook implements Storable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @OneToMany(mappedBy="contactBook")
    @Cascade({ CascadeType.ALL})
    private List<ContactBookEntry> entries;

    public ContactBook() {

    }

    public ContactBook(List<ContactBookEntry> entries){
        this.entries = entries;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<ContactBookEntry> getEntries(){
        return this.entries;
    }

    public void setEntries(List<ContactBookEntry> entries){
        this.entries = entries;
    }

    public void addEntry(ContactBookEntry entry){
        entries.add(entry);
    }
}
