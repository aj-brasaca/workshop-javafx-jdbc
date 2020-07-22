package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListController implements Initializable {

	@FXML
	private Button btnNew;
	
	@FXML
	private TableView<Department> tvwDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tclDepartmentId;
	
	@FXML
	private TableColumn<Department, String> tclDepartmentName;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
			
		
	}
	
	private void initializeNodes() {
		tclDepartmentId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tclDepartmentName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		// Fazer a TableView acompanhar a altura do Stage (janela)
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tvwDepartment.prefHeightProperty().bind(stage.heightProperty());
	}

	@FXML
	public void onBtnNewAction() {
		System.out.println("onBtnNewAction");
	}

}
