package docketdoc;

import java.util.List;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.client.IGenericClient;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Home {
	
	public static String namess = new String();
	public static String agess = new String();
	public static String sexess = new String();
	
	public static Label nameShow = new Label();
	public static Label ageShow = new Label();
	public static Label sexShow = new Label();
	public static Label ccShow = new Label();
	
	public static HBox namePat = new HBox();
	public static HBox sexPat = new HBox();
	public static HBox agePat = new HBox();
	
	public static VBox patientData = new VBox();
	
	public static Label page = new Label();
	public static Label psex = new Label();
	public static Label pname = new Label();
	
	// Histories scroll pane
	public static ScrollPane scrollSocial = new ScrollPane();
	public static ScrollPane scrollMedical = new ScrollPane();
	public static ScrollPane scrollHistory = new ScrollPane();
	public static VBox soScroll = new VBox();
	public static VBox medScroll = new VBox();
	public static VBox hisScroll = new VBox();
	
	public static void establishScene() {		
		initScrollData();
		Main.hLeft = new VBox(20);
		Main.hLeft.setEffect(new DropShadow());
		Main.hLeft.setAlignment(Pos.CENTER);
		Main.hLeft.setBackground(new Background(Main.myBG));
		Main.hLeft.setMinHeight(Main.height);
		Main.hLeft.setMinWidth(Main.width/2-100);
		
		Main.hRight = new VBox(18);
		Main.hRight.setAlignment(Pos.TOP_CENTER);
		Main.hRight.setMinHeight(Main.height);
		Main.hRight.setMinWidth(Main.width/2+100);
		Main.hRight.setPadding(new Insets(20, 0, 0, 20));
		
		// Create Dividers
		VBox stats = new VBox(10);
		stats.setAlignment(Pos.CENTER);
		
		// Patient Portal
		Image pp = new Image("file:src/docketdoc/res/PD_Logo.png");
		ImageView pap = new ImageView(pp);
		VBox patientPortal = new VBox();
		patientPortal.setAlignment(Pos.CENTER);
		patientPortal.setPadding(new Insets(10, 10, 10, 10));
		
		// Label
		Label lp = new Label("Patient Portal");
		lp.setStyle(
				"-fx-font: 25px Futura;" + 
				"-fx-text-fill: #66CDAA;"
				);
		
		patientPortal.getChildren().addAll(pap, lp);
		
		// Search Patient
		TextField searchTF = new TextField();
		searchTF.setStyle(
				"-fx-max-height: 50px;" + 
				"-fx-max-width: 200px;" +
				"-fx-text-fill: #505050;" + 
				"-fx-font: 12px Futura;" + 
				"-fx-prompt-text-fill: #505050;");
		searchTF.setPromptText("First Name");
		
		TextField searchTFlast = new TextField();
		searchTFlast.setStyle(
				"-fx-max-height: 50px;" + 
				"-fx-max-width: 200px;" +
				"-fx-text-fill: #505050;" + 
				"-fx-font: 12px Futura;" + 
				"-fx-prompt-text-fill: #505050;");
		searchTFlast.setPromptText("Last Name");
		
		// Interact Button
		Button searchName = new Button("Search Name");
		searchName.setStyle(
				"-fx-background-radius: 0;" +
				"-fx-font: 16px Futura;" + 
				"-fx-font-weight: bold;" +
				"-fx-text-fill: white;" + 
				"-fx-background-color: #FF7F50;");
		
		// Hover animation.
		searchName.setOnMouseEntered(e -> {
			searchName.setOpacity(.5);
		});
		searchName.setOnMouseExited(e -> {
			searchName.setOpacity(2);
		});
		
		// Button Search for Name
		searchName.setOnAction(e -> {
			/* ------------------------------------ */
			/* Search patiend directory by FHIR API */
			/* ------------------------------------ */
			new FhirContext();
			// Create a client (only needed once)
			 FhirContext ctx = FhirContext.forDstu2();
			 IGenericClient client = ctx.newRestfulGenericClient("http://fhir2.healthintersections.com.au/open");
			 
			 String search = new String(searchTF.getText() + " " + searchTFlast.getText());
			 
			 // Invoke the client
			 Bundle bundle = client.search()
			         .forResource(Patient.class)
			         .where(Patient.NAME.matchesExactly().value(search))
			         .encodedJson()
			         .execute();
			 
			 System.out.println("patients count=" + bundle.size());
			 List<Patient> list = bundle.getResources(Patient.class);
			 for (Patient p : list) {
			    namess = p.getNameFirstRep().getText();
			    agess = p.getBirthDateElement().toHumanDisplay();
			    sexess = p.getGender();
			    
			 }
			 	// Add to grid then display
			 
			 	// Update Name
				nameShow = new Label(namess.toUpperCase());
				namePat.getChildren().remove(nameShow);
				patientData.getChildren().remove(namePat);
				nameShow.setStyle(
						"-fx-font: 15px Futura;" + 
						"-fx-text-fill: #ff0080;"
						);
				namePat.getChildren().addAll(nameShow);
				patientData.getChildren().add(namePat);
				
				// Update Age
				ageShow = new Label(agess.toUpperCase());
				agePat.getChildren().remove(ageShow);
				patientData.getChildren().remove(agePat);
				ageShow.setStyle(
						"-fx-font: 15px Futura;" + 
						"-fx-text-fill: #ff0080;"
						);
				agePat.getChildren().addAll(ageShow);
				patientData.getChildren().add(agePat);
				
				// Update Sex
				sexShow = new Label(sexess.toUpperCase());
				sexPat.getChildren().remove(sexShow);
				patientData.getChildren().remove(sexPat);
				sexShow.setStyle(
						"-fx-font: 15px Futura;" + 
						"-fx-text-fill: #ff0080;"
						);
				sexPat.getChildren().addAll(sexShow);
				patientData.getChildren().add(sexPat);
				
				addScrollData();
				searchTF.setText(null);
				searchTFlast.setText(null);
		});
		
		/* ----------------------------------------- */
		/* Right Side contained within a scroll Pane */
		/* ----------------------------------------- */
		patientData = new VBox(10);
		
		// Name Divisors
		pname = new Label("NAME:\t");
		pname.setStyle(
				"-fx-font: 15px Futura;" + 
				"-fx-text-fill: #66CDAA;" + 
				"-fx-font-weight: bold;"
				);
		namePat = new HBox(10);
		namePat.getChildren().addAll(pname, nameShow);
		
		// Age Divisors
		page = new Label("DOB:\t");
		page.setStyle(
				"-fx-font: 15px Futura;" + 
				"-fx-text-fill: #66CDAA;" + 
				"-fx-font-weight: bold;"
				);
		agePat = new HBox(10);
		agePat.getChildren().addAll(page, ageShow);
		
		// Gender Divisors
		psex = new Label("SEX:\t\t");
		psex.setStyle(
				"-fx-font: 15px Futura;" + 
				"-fx-text-fill: #66CDAA;" + 
				"-fx-font-weight: bold;"
				);
		sexPat = new HBox(10);
		sexPat.getChildren().addAll(psex, sexShow);
		
		patientData.getChildren().addAll(namePat, agePat, sexPat);
		
		// Scrolls
		scrollSocial.setContent(soScroll);
		scrollMedical.setContent(medScroll);
		scrollHistory.setContent(hisScroll);
		
		// Add search results to right side
		Main.hRight.getChildren().addAll(
				patientData,
				scrollMedical,
				scrollSocial,
				hisScroll);
		
		// Add elements to Left Side
		stats.getChildren().addAll(
				patientPortal,
				searchTF, 
				searchTFlast, 
				searchName);
		
		// Set Main stage to home scene
		
		Main.hLeft.getChildren().addAll(stats);
		Main.containHome = new HBox();
		Main.containHome.getChildren().addAll(Main.hLeft, Main.hRight);
		Main.homeScene = new Scene(Main.containHome);
	}
	
	/* Place relevant data findings to screen */
	private static void initScrollData() {
		
		scrollSocial.setMaxSize(370, 100);
		scrollMedical.setMaxSize(370, 100);
		scrollHistory.setMaxSize(370, 100);
		
		Label mh = new Label("MEDICAL HISTORY BY RECENT: ");
		mh.setStyle(
				"-fx-font: 15px Futura;" + 
				"-fx-text-fill: #ff0080;" + 
				"-fx-font-weight: bold;"
				);
		
		
		medScroll.getChildren().addAll(mh);
		
		Label sh = new Label("SOCIAL HISTORY: ");
		sh.setStyle(
				"-fx-font: 15px Futura;" + 
				"-fx-text-fill: #ff0080;" + 
				"-fx-font-weight: bold;"
				);
		
		soScroll.getChildren().addAll(sh);
		
	}
	
	/* Place relevant data findings to screen */
	private static void addScrollData() {
		
		scrollSocial.setMaxSize(370, 100);
		scrollMedical.setMaxSize(370, 100);
		scrollHistory.setMaxSize(370, 100);
		
		Label mh = new Label("MEDICAL HISTORY BY RECENT: ");
		mh.setStyle(
				"-fx-font: 15px Futura;" + 
				"-fx-text-fill: #ff0080;" + 
				"-fx-font-weight: bold;"
				);
		
		// Social histories input
		Text medText = new Text();
		medText.setStyle(
				"-fx-font: 12px Futura;" + 
				"-fx-text-fill: #ff0080;"
						);
		medText.setText(
				"\t- "
						+ "9/17/2015 | Emergency | Northern Hospital  | Peterson MD, Marc\n"
						+ "\t- "
						+ "5/21/2015 | Emergency | Western Hospital  | Bermann MD, Jason\n"
						+ "\t- "
						+ "11/4/2014 | Emergency | Northern Hospital | Middleton MD, Lisa\n"
						+ "\t- "
						+ "7/19/2014 | Emergency | Southern Hospital | Johnson MD, Alex\n"
						+ "\t- "
						+ "3/9/2014 | Emergency | Western Hospital  | Walton MD, Jonathan\n"
						+ "\t- "
						+ "10/18/2013 | Emergency | Eastern Hospital | Campbell MD, Ivan\n"
						+ "\t- "
						+ "4/5/2013 | Emergency | Southern Hospital  | Thomas MD, Judy \n"
						+ "\t- "
						+ "12/23/2012 | Emergency | Western Hospital  | Johnson MD, Alex\n"

				);
		medScroll.getChildren().addAll(medText);
		
		Label sh = new Label("SOCIAL HISTORY: ");
		sh.setStyle(
				"-fx-font: 15px Futura;" + 
				"-fx-text-fill: #ff0080;" + 
				"-fx-font-weight: bold;"
				);
		
		// Social histories input
		Text socialText = new Text();
		socialText.setStyle(
				"-fx-font: 12px Futura;" + 
				"-fx-text-fill: #ff0080;"
						);
		socialText.setText(
				"\t- " + 
				"Eats in fast food outlets\n"
				+ "\t- "
				+ "Ex-smoker\n"
				+ "\t- "
				+ "Uninsured medical expenses\n"
				+ "\t- "
				+ "Independent housing, lives alone\n"
				+ "\t- "
				+ "Does not drive a car\n"
				+ "\t- "
				+ "Employed in paid casual work\n"
				+ "\t- "
				+ "Widower\n"
				);
		
		soScroll.getChildren().addAll(socialText);
		
	}

	
	
	
	
	
	
	
	
	
	

	
	
}
