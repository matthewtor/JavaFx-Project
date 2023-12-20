package view;

import java.time.LocalDate;
import java.util.jar.Attributes.Name;

import controller.ControllerAdmin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Admin;

public class PaneAdmin extends Tab {
	private final int TABLEWIDTH = 900;
	private final int TABLEHEIGHT = 250;
	private final int BUTTONWIDTH = 300;
	private final int BUTTONHEIGHT = 80;

	public PaneAdmin() {
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

		Label labelPhone = new Label("Phone:");
		TextField textfieldPhone = new TextField();
		textfieldPhone.setPromptText("Phone Number");


		ObservableList<Admin> observablelist = FXCollections.observableArrayList(ControllerAdmin.getAdminList());
		TableColumn<Admin, Name> columnName = new TableColumn<>("Name");
		columnName.setCellValueFactory(new PropertyValueFactory<Admin, Name>("name"));
		TableColumn<Admin, String> columnId = new TableColumn<>("Id");
		columnId.setCellValueFactory(new PropertyValueFactory<Admin, String>("id"));
		TableColumn<Admin, LocalDate> columnStartDate = new TableColumn<>("StartDate");
		columnStartDate.setCellValueFactory(new PropertyValueFactory<Admin, LocalDate>("startDate"));
		TableColumn<Admin, Double> columnSalary = new TableColumn<>("Salary");
		columnSalary.setCellValueFactory(new PropertyValueFactory<Admin, Double>("salary"));
		TableColumn<Admin, Integer> columnPhone = new TableColumn<>("Phone");
		columnPhone.setCellValueFactory(new PropertyValueFactory<Admin, Integer>("phone"));


		TableView<Admin> table = new TableView<Admin>();
		table.setMinWidth(TABLEWIDTH);
		table.setMaxHeight(TABLEHEIGHT);
		table.setMaxWidth(Region.USE_PREF_SIZE);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getColumns().addAll(columnName, columnId, columnPhone, columnSalary, columnStartDate);
		table.setItems(observablelist);


		Button buttonAdd = new Button("Add");
		buttonAdd.setMinWidth(BUTTONWIDTH);
		buttonAdd.setMinHeight(BUTTONHEIGHT);
		buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ControllerAdmin.addAdmin(
					textfieldFirst.getText(),
					textfieldMiddle.getText(),
					textfieldLast.getText(),
					datepickerStartDate.getValue(),
					Double.parseDouble(textfieldSalary.getText()),
					Integer.parseInt(textfieldPhone.getText())
				);

				ObservableList<Admin> observablelist = FXCollections
						.observableArrayList(ControllerAdmin.getAdminList());
				table.setItems(observablelist);

				textfieldFirst.clear();
				textfieldMiddle.clear();
				textfieldLast.clear();
				datepickerStartDate.getEditor().clear();
				textfieldSalary.clear();
				textfieldPhone.clear();
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
				Admin selectedAdmin = (Admin) table.getSelectionModel().getSelectedItem();
					ControllerAdmin.removeAdmin(selectedAdmin);
					table.getItems().remove(selectedTableItem);
				}
			}
		);


		Button buttonRaise = new Button("Give 5% Raise");
		buttonRaise.setMinWidth(BUTTONWIDTH);
		buttonRaise.setMinHeight(BUTTONHEIGHT);
		buttonRaise.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Admin selectedAdmin = (Admin) table.getSelectionModel().getSelectedItem();
				ControllerAdmin.giveRaise(selectedAdmin);
				ObservableList<Admin> observablelist = FXCollections.observableArrayList(ControllerAdmin.getAdminList());
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
		gridpaneInput.addRow(5, labelPhone, textfieldPhone);
		gridpaneInput.setMinWidth(400);
		gridpaneInput.setMaxWidth(400);
		gridpaneInput.setVgap(8);
		gridpaneInput.setHgap(32);

		HBox hboxButtons = new HBox();
		hboxButtons.getChildren().addAll(buttonAdd, buttonRemove, buttonRaise);
		hboxButtons.setSpacing(0);
		hboxButtons.setPadding(new Insets(16, 0, 16, 0));
		hboxButtons.setAlignment(Pos.CENTER);

		setText("Admins");
		VBox vboxLayout = new VBox(gridpaneInput, hboxButtons, table);
		setContent(vboxLayout);

	}
}