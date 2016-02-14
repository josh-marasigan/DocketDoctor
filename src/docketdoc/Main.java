package docketdoc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	
	// Global Scenes
	public static Scene homeScene;
	
	// Scene Dividers
	public static VBox homeDiv;
	public static VBox patientSceneTop;
	public static VBox patientSceneBottom;
	public static VBox hLeft;
	public static VBox hRight;
	public static HBox containHome;
	public static BackgroundFill myBG;
	
	// Global stage
	Stage main;
	public static int width;
	public static int height;

	public static Label nameShow;
	
	public void start(Stage primaryStage) throws Exception {
		main = primaryStage;
		
		// Main BG
		myBG = new BackgroundFill(Color.ALICEBLUE, new CornerRadii(1),new Insets(0.0,0.0,0.0,0.0));
		
		// Set screen size to maximum desktop screen.
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		main.setX(bounds.getMinX()/1.5);
		main.setY(bounds.getMinY()/2);
		main.setWidth(bounds.getWidth()/2);
		width = (int) (bounds.getWidth()/2);
		main.setHeight(bounds.getHeight()/1.5);
		height = (int) (bounds.getHeight()/1.5);

		// Main dividor
		VBox login = new VBox();
		
		// Banner
		Image logo = new Image("file:src/docketdoc/res/DD_Banner.png");
		ImageView logoBan = new ImageView(logo);
		VBox logoBanner = new VBox();
		logoBanner.setPadding(new Insets(10, 10, 10, 10));
		
		// Label
		Label l1 = new Label("A FHIR native medical desktop application");
		l1.setStyle(
				"-fx-font: 20px Futura;" + 
				"-fx-text-fill: #66CDAA;"
				);
		
		// Get logo elements
		logoBanner.setAlignment(Pos.CENTER);
		logoBanner.getChildren().addAll(logoBan, l1);
		
		// Login Texfield
		TextField logField = new TextField();
		logField.setStyle(
				"-fx-max-height: 50px;" + 
				"-fx-max-width: 200px;" +
				"-fx-text-fill: #505050;" + 
				"-fx-font: 12px Futura;" + 
				"-fx-prompt-text-fill: #505050;");
		logField.setPromptText("Username");
		
		// Password Texfield
		PasswordField passField = new PasswordField();
		passField.setStyle(
				"-fx-max-height: 50px;" + 
				"-fx-max-width: 200px;" +
				"-fx-text-fill: #505050;" + 
				"-fx-font: 12px Futura;" + 
				"-fx-prompt-text-fill: #505050;");
		passField.setPromptText("Password");
		
		// Interact Button
		Button getStart = new Button("Login");
		getStart.setStyle(
				"-fx-background-radius: 0;" +
				"-fx-font: 16px Futura;" + 
				"-fx-font-weight: bold;" +
				"-fx-text-fill: white;" + 
				"-fx-background-color: #FF7F50;");
		
		VBox logfields = new VBox(10);
		logfields.setAlignment(Pos.CENTER);
		logfields.getChildren().addAll(logField, passField, getStart);
		
		// Hover animation.
		getStart.setOnMouseEntered(e -> {
			getStart.setOpacity(.5);
		});
		getStart.setOnMouseExited(e -> {
			getStart.setOpacity(2);
		});
		
		VBox titleDiv = new VBox(10);
		titleDiv.setBackground(new Background(myBG));		
		titleDiv.getChildren().addAll(
				logoBanner,
				login,
				logfields);
		titleDiv.setAlignment(Pos.CENTER);
		
		// Set Scene then show scene
		Scene mainS = new Scene(titleDiv);
		
		// Reveal stage
		main.setScene(mainS);
		main.show();
		
		/* ---------------- */
		/* Scene navigation */
		/* ---------------- */
		getStart.setOnAction(e -> {
			main.setScene(homeScene);
			logField.setText(null);
			passField.setText(null);
		});
		
		// Establish all scenes
		Home.establishScene();
		
	}
		
	public static void main(String[] args) {
		launch(args);
	}
	
}
