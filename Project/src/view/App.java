// 

package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class App extends Application {
	private final int HEIGHT = 650;
	private final int WIDTH = 900;

	@Override
	public void start(Stage primaryStage) {
		try {
			Button buttonQuit = new Button("Quit");
			buttonQuit.setMinWidth(64);
			buttonQuit.setOnAction(e -> {
				primaryStage.close();
			});

			TabPane tabs = new TabPane();
			tabs.setTabMinWidth(80);
			tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
			tabs.getTabs().add(new PaneHome());
			tabs.getTabs().add(new PaneAdmin());
			tabs.getTabs().add(new PaneEngineer());
			tabs.getTabs().add(new PaneIntern());

			BorderPane currentPane = new BorderPane();
			Scene scene = new Scene(currentPane, WIDTH, HEIGHT);

			currentPane.setBottom(buttonQuit);
			currentPane.setCenter(tabs);
			currentPane.prefHeightProperty().bind(scene.heightProperty());
			currentPane.prefWidthProperty().bind(scene.widthProperty());

			primaryStage.getIcons().add(new Image("file:mtu.png"));
			primaryStage.setTitle("Staff Directory");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}