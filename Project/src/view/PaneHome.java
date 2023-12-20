package view;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PaneHome extends Tab {
	private final String IMAGE = "file:mtu_transparent.png";
	
	public PaneHome() {
        Image image = new Image(IMAGE);
        ImageView  imageView=new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        BorderPane borderpaneImage = new BorderPane();
        borderpaneImage.setCenter(imageView);

        
		Label labelName = new Label("Name:");
        labelName.setStyle("-fx-font: 16 Verdana;");
        Text textName = new Text("Mattia Torcasio");
        textName.setStyle("-fx-font: 16 Verdana;");
		textName.setFill(Color.DARKGREY);

		Label labelId = new Label("Student ID:");
        labelId.setStyle("-fx-font: 16 Verdana;");
        Text textId = new Text("R00187460");
        textId.setStyle("-fx-font: 16 Verdana;");
		textId.setFill(Color.DARKGREY);

		Label labelAssignment = new Label("Assignment:");
        labelAssignment.setStyle("-fx-font: 16 Verdana;");
        Text textAssignment = new Text(" OOP - Java FX - Staff Directory");
        textAssignment.setStyle("-fx-font: 16 Verdana;");
		textAssignment.setFill(Color.DARKGREY);
		
		GridPane gridpane = new GridPane();
		gridpane.addRow(0, labelName, textName);
		gridpane.addRow(1,  labelId, textId);
		gridpane.addRow(2,  labelAssignment, textAssignment);
		gridpane.setMinWidth(300);
		gridpane.setVgap(8);
		gridpane.setHgap(32);
        
		setText("Home");
		FlowPane flowpaneLayout = new FlowPane(Orientation.HORIZONTAL, borderpaneImage, gridpane);
		flowpaneLayout.setHgap(32);
        flowpaneLayout.setAlignment(Pos.CENTER);
		setContent(flowpaneLayout);
	}
	
}