package avocat.controller;

import avocat.model.Assurance;
import avocat.model.Client;
import avocat.model.Data_Base;
import avocat.model.Dossier;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class newAssurance_control {
	
	
    @FXML
    private TextField type_doss;
    @FXML
    private TextField tribunal_num;
    @FXML
    private TextField nom;
    @FXML
    private TextField CIN;
    @FXML
    private TextField num_tel;
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
        
        
        Assurance assurance = new Assurance(
        		CIN.getText()
        		,type_doss.getText(),
        		tribunal_num.getText()
        		,nom.getText(),num_tel.getText(),email.getText(),
        		date_audience.getText(),nom_adv.getText(),decision.getText());
        Data_Base.inserer("assurance", assurance);
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