package avocat.model;

public class Assurance {
	
	private String id_dossier;
	private String type_doss,tribunal_num,nom,num_tel,email,date_audience,nom_adv,decision;
	
	public Assurance(String id_dossier, String type_doss, String tribunal_num, String nom, String num_tel,
			String email, String date_audience, String nom_adv, String decision) {
		super();
		this.id_dossier = id_dossier;
		this.type_doss = type_doss;
		this.tribunal_num = tribunal_num;
		this.nom = nom;

		this.num_tel = num_tel;
		this.email = email;
		this.date_audience = date_audience;
		this.nom_adv = nom_adv;
		this.decision = decision;
	}
	
	public String getId_dossier() {
		return id_dossier;
	}
	public void setId_dossier(String id_dossier) {
		this.id_dossier = id_dossier;
	}
	public String getType_doss() {
		return type_doss;
	}
	public void setType_doss(String type_doss) {
		this.type_doss = type_doss;
	}
	public String getTribunal_num() {
		return tribunal_num;
	}
	public void setTribunal_num(String tribunal_num) {
		this.tribunal_num = tribunal_num;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNum_tel() {
		return num_tel;
	}
	public void setNum_tel(String num_tel) {
		this.num_tel = num_tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate_audience() {
		return date_audience;
	}
	public void setDate_audience(String date_audience) {
		this.date_audience = date_audience;
	}
	public String getNom_adv() {
		return nom_adv;
	}
	public void setNom_adv(String nom_adv) {
		this.nom_adv = nom_adv;
	}
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	
}
