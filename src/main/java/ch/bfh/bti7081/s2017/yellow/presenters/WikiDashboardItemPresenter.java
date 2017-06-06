package ch.bfh.bti7081.s2017.yellow.presenters;

import ch.bfh.bti7081.s2017.yellow.views.DashboardItemView;
import ch.bfh.bti7081.s2017.yellow.views.WikiDashboardItemView;

public class WikiDashboardItemPresenter implements DashboardItemPresenter{
	WikiDashboardItemView wikiDashboardItemView;
	
	public WikiDashboardItemPresenter(WikiDashboardItemView wikiDashboardItemView) {
		this.wikiDashboardItemView = wikiDashboardItemView;
	}
	
	@Override
	public DashboardItemView getView() {
		return wikiDashboardItemView;
	}



}
