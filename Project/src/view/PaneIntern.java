package view;

import java.time.LocalDate;
import java.util.jar.Attributes.Name;

import controller.ControllerIntern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.Intern;
import controller.ControllerEngineer;

public class PaneIntern extends Tab {
	private final int TABLEWIDTH = 900;
	private final int TABLEHEIGHT = 250;
	private final int BUTTONWIDTH = 300;
	private final int BUTTONHEIGHT = 80;

	public PaneIntern() {

		Label labelFirstName = new Label("First Name:");
		TextField textfieldFirst = new TextField();
		textfieldFirst.setPromptText("First Name");

		Label labelMiddleName = new Label("Middle Name:");
		TextField textfieldMiddle = new TextField();
		textfieldMiddle.setPromptText("Middle Name");

		Label labelLastName = new Label("Last Name:");
		TextField textfieldLast = new TextField();
		textfieldLast.setPromptText("Last Name");

		Label labelStartDate = new Label("Start Date");
		DatePicker datepickerStartDate = new DatePicker();
		datepickerStartDate.setPromptText("Start Date");
		datepickerStartDate.getEditor().setDisable(true);

		Label labelSalary = new Label("Salary:");
		TextField textfieldSalary = new TextField();
		textfieldSalary.setPromptText("Salary (euro)");

        Label labelUniversity = new Label("University:");
		TextField textfieldUniversity = new TextField();
		textfieldUniversity.setPromptText("University");

        Label labelMentor = new Label("Mentor:");
        ComboBox comboboxMentor = new ComboBox();
		comboboxMentor.getItems().addAll(ControllerEngineer.getEngineerList());
		comboboxMentor.setPromptText("Engineer");


		ObservableList<Intern> observablelist = FXCollections.observableArrayList(ControllerIntern.getInternList());
		TableColumn<Intern, Name> columnName = new TableColumn<>("Name");
		columnName.setCellValueFactory(new PropertyValueFactory<Intern, Name>("name"));
		TableColumn<Intern, String> columnId = new TableColumn<>("Id");
		columnId.setCellValueFactory(new PropertyValueFactory<Intern, String>("id"));
        TableColumn<Intern, String> columnMentor = new TableColumn<>("Mentor");
		columnMentor.setCellValueFactory(new PropertyValueFactory<Intern, String>("mentorId"));
        TableColumn<Intern, String> columnUniversity = new TableColumn<>("University");
		columnUniversity.setCellValueFactory(new PropertyValueFactory<Intern, String>("university"));
		TableColumn<Intern, Double> columnSalary = new TableColumn<>("Salary");
		columnSalary.setCellValueFactory(new PropertyValueFactory<Intern, Double>("salary"));
		TableColumn<Intern, LocalDate> columnStartDate = new TableColumn<>("StartDate");
		columnStartDate.setCellValueFactory(new PropertyValueFactory<Intern, LocalDate>("startDate"));


		TableView<Intern> table = new TableView<Intern>();
		table.setMinWidth(TABLEWIDTH);
		table.setMaxHeight(TABLEHEIGHT);
		table.setMaxWidth(Region.USE_PREF_SIZE); // dynamic max width
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // sum of column widths equal table width
		table.getColumns().addAll(columnName, columnId, columnMentor, columnUniversity, columnSalary, columnStartDate);
		table.setItems(observablelist);


		Button buttonAdd = new Button("Add");
		buttonAdd.setMinWidth(BUTTONWIDTH);
		buttonAdd.setMinHeight(BUTTONHEIGHT);
		buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ControllerIntern.addIntern(
					textfieldFirst.getText(),
					textfieldMiddle.getText(),
					textfieldLast.getText(),
					datepickerStartDate.getValue(),
					Double.parseDouble(textfieldSalary.getText()),
					textfieldUniversity.getText(),
					comboboxMentor.getValue().toString()
				);

				ObservableList<Intern> observablelist = FXCollections
						.observableArrayList(ControllerIntern.getInternList());
				table.setItems(observablelist);

				textfieldFirst.clear();
				textfieldMiddle.clear();
				textfieldLast.clear();
				datepickerStartDate.getEditor().clear();
				textfieldSalary.clear();
				textfieldUniversity.clear();
				comboboxMentor.getSelectionModel().clearSelection(); // ! the prompt is also removed
				}
			}
		);


		Button buttonRemove = new Button("Remove");
		buttonRemove.setMinWidth(BUTTONWIDTH);
		buttonRemove.setMinHeight(BUTTONHEIGHT);
		buttonRemove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Object selectedTableItem = table.getSelectionModel().getSelectedItem();
				Intern selectedIntern = (Intern) table.getSelectionModel().getSelectedItem();
				ControllerIntern.removeIntern(selectedIntern);
				table.getItems().remove(selectedTableItem);
				}
			}
		);


		Button buttonRaise = new Button("Give 2% Raise");
		buttonRaise.setMinWidth(BUTTONWIDTH);
		buttonRaise.setMinHeight(BUTTONHEIGHT);
		buttonRaise.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Intern selectedIntern = (Intern) table.getSelectionModel().getSelectedItem();
				ControllerIntern.giveRaise(selectedIntern);
				ObservableList<Intern> observablelist = FXCollections.observableArrayList(ControllerIntern.getInternList());
				table.setItems(observablelist);
				}
			}
		);

		GridPane gridpaneInput = new GridPane();
		gridpaneInput.addRow(0, labelFirstName, textfieldFirst);
		gridpaneInput.addRow(1, labelMiddleName, textfieldMiddle);
		gridpaneInput.addRow(2, labelLastName, textfieldLast);
		gridpaneInput.addRow(3, labelStartDate, datepickerStartDate);
		gridpaneInput.addRow(4, labelSalary, textfieldSalary);
        gridpaneInput.addRow(5, labelUniversity, textfieldUniversity);
        gridpaneInput.addRow(6, labelMentor, comboboxMentor);
		gridpaneInput.setMinWidth(400);
		gridpaneInput.setMaxWidth(400);
		gridpaneInput.setVgap(8);
		gridpaneInput.setHgap(32);

		HBox hboxButtons = new HBox();
		hboxButtons.getChildren().addAll(buttonAdd, buttonRemove, buttonRaise);
		hboxButtons.setSpacing(0);
		hboxButtons.setPadding(new Insets(16, 0, 16, 0));
		hboxButtons.setAlignment(Pos.CENTER);

		setText("Interns");
		VBox vboxLayout = new VBox(gridpaneInput, hboxButtons, table);
		setContent(vboxLayout);

	}
}