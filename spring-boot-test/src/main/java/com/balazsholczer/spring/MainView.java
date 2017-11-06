package com.balazsholczer.spring;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

//tells Spring to call the class when /ui is requested
@SpringUI(path="/ui")
@Title("This is the title")
@Theme("valo")
public class MainView extends UI{

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout verticalLayout = new VerticalLayout();
		
		verticalLayout.addComponent(new Label("Welcome to spring boot woth Vaadin..."));
		Button button = new Button("Click me!");
		
		verticalLayout.addComponent(button);
		
		button.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				verticalLayout.addComponent(new Label("Button has been clicked..."));
				
			}
		});
		
		
		setContent(verticalLayout);
	}

}
