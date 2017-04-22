package avocat.model;

import java.time.LocalDateTime;

import jfxtras.scene.control.agenda.Agenda;

public class Calendrier {
	private int id ;
	private String startLocalDateTime;
	private String endLocalDateTime;
	private String summary;
	private String description;
	private String StyleClass;
	private Agenda.Appointment appointment;
	
	public Calendrier(int id , String startLocalDateTime,String endLocalDateTime,String summary,String description,String StyleClass){
		this.setId(id);
		this.startLocalDateTime = startLocalDateTime;
		this.endLocalDateTime = endLocalDateTime;
		this.setSummary(summary);
		this.setDescription(description);
		this.setStyleClass(StyleClass);
		appointment = new Agenda.AppointmentImplLocal()
				.withStartLocalDateTime( LocalDateTime.parse(startLocalDateTime) )
				.withEndLocalDateTime( LocalDateTime.parse(endLocalDateTime) )
				.withSummary(summary)
				.withDescription(description)
				.withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass(StyleClass));
	}
	public Agenda.Appointment getAppointment(){
		return appointment;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStyleClass() {
		return StyleClass;
	}
	public void setStyleClass(String styleClass) {
		StyleClass = styleClass;
	}
	public String getStartLocalDateTime() {
		return startLocalDateTime;
	}
	public void setStartLocalDateTime(String startLocalDateTime) {
		this.startLocalDateTime = startLocalDateTime;
	}
	public String getEndLocalDateTime() {
		return endLocalDateTime;
	}
	public void setEndLocalDateTime(String endLocalDateTime) {
		this.endLocalDateTime = endLocalDateTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
