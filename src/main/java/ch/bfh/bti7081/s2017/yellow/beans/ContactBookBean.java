package ch.bfh.bti7081.s2017.yellow.beans;

import ch.bfh.bti7081.s2017.yellow.entities.Storable;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBook;
import ch.bfh.bti7081.s2017.yellow.entities.contacts.ContactBookEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 21.05.17.
 */
public class ContactBookBean extends BaseBean<ContactBook> {

    private List<ContactBookEntryBean> entries = new ArrayList<>();

    public List<ContactBookEntryBean> getEntries(){
        return this.entries;
    }

    public void setEntries(List<ContactBookEntryBean> entries){
        this.entries = entries;
    }

    public void addEntry(ContactBookEntryBean entry){
        entries.add(entry);
    }
}
