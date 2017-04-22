package avocat.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.application.Application;
import javafx.stage.Stage;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.JSplitPane;

import avocat.model.Calendrier;
import avocat.model.Data_Base;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfxtras.samples.JFXtrasSampleBase;
import jfxtras.scene.control.LocalDateTimeTextField;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import jfxtras.scene.control.agenda.Agenda.LocalDateTimeRange;
import jfxtras.scene.control.agenda.AgendaSkinSwitcher;
import jfxtras.scene.layout.GridPane;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.util.Duration;
import jfxtras.animation.Timer;
import jfxtras.labs.samples.SampleBase;
import jfxtras.util.NodeUtil;


public class Calendar {
	public SplitPane sp;
	public Agenda agenda ;
	public HBox lGridPane;
	public Calendar() {
		try {
			agenda = new Agenda();
			Map<String, Agenda.AppointmentGroup> lAppointmentGroupMap = new TreeMap<String, Agenda.AppointmentGroup>();
			// accept new appointments
			agenda.newAppointmentCallbackProperty().set(new Callback<Agenda.LocalDateTimeRange, Agenda.Appointment>()
			{
				@Override
				public Agenda.Appointment call(LocalDateTimeRange dateTimeRange)
				{
					System.out.println("add..");
					// we can save added date here :p
					Calendrier calendrier = new Calendrier(1,
							dateTimeRange.getStartLocalDateTime().toString(),
							dateTimeRange.getEndLocalDateTime().toString(),
							"test",
							"test",
							"group8"
					);
					Data_Base.inserer("calendrier", calendrier);
					return new Agenda.AppointmentImplLocal()
							.withStartLocalDateTime( dateTimeRange.getStartLocalDateTime() )
							.withEndLocalDateTime( dateTimeRange.getEndLocalDateTime() )
							.withSummary("Audience")
							.withDescription("abdellah elouassif ")
							.withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group8"));
//
				}
			});
			// load data from Data base :p
			ArrayList<Calendrier> loadList = new ArrayList<Calendrier>();
			loadList = Data_Base.selectAll("calendrier");
			for(int i=0;i<loadList.size();i++){
				agenda.appointments().add(loadList.get(i).getAppointment());
			}


			int idx = 0;

//
			lGridPane = new HBox();

			LocalDateTimeTextField lLocalDateTimeTextField = new LocalDateTimeTextField();
//	            lGridPane.add(lLocalDateTimeTextField);
			lGridPane.getChildren().add(lLocalDateTimeTextField);
			lLocalDateTimeTextField.localDateTimeProperty().bindBidirectional(agenda.displayedLocalDateTime());
//			}
			//action on appointment :p
			agenda.setActionCallback( (appointment) -> {
				agenda.appointments().remove(appointment);
				Calendrier temp= new Calendrier(1,
						appointment.getStartLocalDateTime().toString(),
						appointment.getEndLocalDateTime().toString(),
						appointment.getSummary(),
						appointment.getDescription(),
						appointment.getAppointmentGroup().getStyleClass()
				);

				Data_Base.delete("calendrier", temp);

				return null;
			});

			sp = new SplitPane();
			sp.setOrientation(Orientation.HORIZONTAL);
			sp.getItems().add(agenda);
			sp.getItems().add(lGridPane);
			sp.setDividerPositions(0.7f, 0.2f);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
