package cs320lab3;

import java.util.ArrayList;
import java.util.List;

public class GuestBookBean {

    List<GuestBookEntry> entries;
    int editIndex = -1;

    public void setEditIndex(int editEntry) {
		this.editIndex = editEntry;
	}

	public GuestBookBean()
    {
        entries = new ArrayList<GuestBookEntry>();
    }

    public void setAddEntry( String dummy )
    {
        GuestBookEntry entry = new GuestBookEntry();
        entries.add( entry );
    }
    
    public GuestBookEntry getEditEntry()
    {
    	return entries.get(editIndex);
    }
    
    public void setEditEntry(GuestBookEntry entry)
    {
    	GuestBookEntry edit = entries.get(editIndex);
    	edit.setMessage(entry.getMessage());
    	edit.setName(entry.getName());
    	edit.setDate(entry.getDate());
    }

    public GuestBookEntry getLastEntry()
    {
        return entries.get( entries.size() - 1 );
    }

    public List<GuestBookEntry> getEntries()
    {
        return entries;
    }

    public void setEntries( List<GuestBookEntry> entries )
    {
        this.entries = entries;
    }

}

