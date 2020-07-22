package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {

	private DepartmentService service;
	
	@FXML
	private Button btnNew;
	
	@FXML
	private TableView<Department> tvwDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tclDepartmentId;
	
	@FXML
	private TableColumn<Department, String> tclDepartmentName;
	
	private ObservableList<Department> obsList;
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
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
	
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tvwDepartment.setItems(obsList);
			
	}

}
