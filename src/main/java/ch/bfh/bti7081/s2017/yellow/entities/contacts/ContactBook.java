package ch.bfh.bti7081.s2017.yellow.entities.contacts;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dario on 09.05.2017.
 */
@Entity
@Table(name="CONTACT_BOOK")
public class ContactBook implements Storable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @OneToMany
    private List<ContactBookEntry> entries;

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

    public void setEntries(ContactBookEntry entry){
        entries.add(entry);
    }
}
