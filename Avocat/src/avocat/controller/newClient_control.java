package avocat.controller;

import avocat.model.Client;
import avocat.model.Dossier;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class newClient_control {
	@FXML
    private Label id_dossier;
	
    @FXML
    private TextField type_doss;
    @FXML
    private TextField tribunal_num;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField CIN;
    @FXML
    private TextField num_tel;
    @FXML
    private TextField profession;
    @FXML
    private TextField email;
    @FXML
    private TextField date_audience;
    @FXML
    private TextField nom_adv;
    
    @FXML
    private TextArea decision;
    @FXML
    private Button cancel;
    
    static Connection_control cc=new Connection_control();

    private Stage dialogStage;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Sets the stage of this dialog.
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    
    @FXML
    private void handleOk() {
        System.out.println(cc.current_avc);
        // add in dossier table
        Dossier dossier = new Dossier(0,tribunal_num.getText() , type_doss.getText(), nom_adv.getText() 
    		  , decision.getText() , cc.current_avc,CIN.getText() ,date_audience.getText() );
      	dossier.insert_query();
      	
      	// add in clients table 
		Client client = new Client(CIN.getText() , nom.getText() , prenom.getText() , num_tel.getText()
				, email.getText() , profession.getText() , 0 );
		client.insert_query();
        
		//close window
		Stage stage = (Stage) cancel.getScene().getWindow();
     	stage.close();
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        System.out.println("cancel");
        Stage stage = (Stage) cancel.getScene().getWindow();
     	stage.close();
//        dialogStage.close();
    }

}