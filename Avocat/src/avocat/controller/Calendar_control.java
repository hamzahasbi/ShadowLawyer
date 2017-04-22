package avocat.controller;
import avocat.view.Calendar;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import jfxtras.scene.layout.VBox;
public class Calendar_control {
	@FXML AnchorPane agendaTop;
	@FXML AnchorPane agendabottom;
	
	public void initialize() {
		Calendar c = new Calendar();
		agendaTop.getChildren().add(c.agenda);
		agendabottom.getChildren().add(c.lGridPane);
	}
	public Calendar_control(){
		
	}
}
