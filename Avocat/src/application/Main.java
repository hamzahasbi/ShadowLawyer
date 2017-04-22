package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.io.IOException;
import avocat.model.Data_Base;
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		 Data_Base db=new Data_Base();

		Parent root = FXMLLoader.load(getClass().getResource("../avocat/view/connection.fxml"));
		primaryStage.setTitle("ShadowLawyer");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../avocat/Ressources/icone2.jpg")));
		Scene sc=new Scene(root, 970, 600);
		//sc.getStylesheets().add("@Login.css");
		primaryStage.setMaxWidth(970);
		primaryStage.setMaxHeight(600);
		primaryStage.setScene(sc);
		primaryStage.show();
	}

	public static void main(String[] args) {
		
		launch(args);
	}
}
