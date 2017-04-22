package avocat.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.io.IOException;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/connection.fxml"));
		primaryStage.setTitle("AVOCAT_APP");
		primaryStage.getIcons().add(new Image("http://icons.iconarchive.com/icons/atti12/tv-series-folder-5/256/Shameless-icon.png"));
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
