package ch.bfh.bti7081.s2017.yellow.views;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class WikiDashboardItemViewImpl extends VerticalLayout implements WikiDashboardItemView{

	public WikiDashboardItemViewImpl() {
		Label wikiEntryTitle = new Label("Wiki Titel");
        addComponent(wikiEntryTitle);
        Label wikiEntryContent = new Label("Wiki content: Lorem Ipsum "
        		+ "is simply dummy text of the printing and typesetting "
        		+ "industry. Lorem Ipsum has been the industry's standard "
        		+ "dummy text ever since the 1500s, when an unknown printer "
        		+ "took a galley of type and scrambled it to make a type "
        		+ "specimen book. It has survived not only five centuries, "
        		+ "but also the leap into electronic typesetting, remaining "
        		+ "essentially unchanged. It was popularised in the 1960s "
        		+ "with the release of Letraset sheets containing Lorem "
        		+ "Ipsum passages, and more recently with desktop publishing "
        		+ "software like Aldus PageMaker including versions of "
        		+ "Lorem Ipsum.");
        addComponent(wikiEntryContent);
        Label wikiEntryAuthor = new Label("- Hans Fritz");
        addComponent(wikiEntryAuthor);
	}
	
}
