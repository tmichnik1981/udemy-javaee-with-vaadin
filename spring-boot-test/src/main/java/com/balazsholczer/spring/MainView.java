package com.balazsholczer.spring;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;


//tells Spring to call the class when /ui is requested
@SuppressWarnings("serial")
@SpringUI(path="/ui")
@Title("This is the title")
@Theme("valo")
public class MainView extends UI{

	@Override
	protected void init(VaadinRequest request) {
		
		HorizontalLayout root = new HorizontalLayout();
		
		root.setWidth("100%");
		TabSheet tabs = new TabSheet();
		tabs.setWidth("50%");
		
		HorizontalLayout layout1 = new HorizontalLayout();
		layout1.addComponent(new Label("This is just the layout1"));
		
		HorizontalLayout layout2 = new HorizontalLayout();
		layout2.addComponent(new Label("This is just the layout2"));
		
		tabs.addTab(layout1, "TAB1");
		tabs.addTab(layout2, "TAB2");
		
        root.addComponent(tabs);
        root.setComponentAlignment(tabs, Alignment.TOP_CENTER);
        
		setContent(root);
	}

}
