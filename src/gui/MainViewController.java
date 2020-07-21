package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem mnuItemSeller;
	
	@FXML
	private MenuItem mnuItemDepartment;
	
	@FXML
	private MenuItem mnuItemAbout;
	
	@FXML
	public void onMnuItemSellerAction() {
		System.out.println("onMnuItemSellerAction");
	}
	
	@FXML
	public void onMnuItemDepartmentAction() {
		System.out.println("onMnuItemDepartmentAction");
	}
	
	@FXML
	public void onMnuItemAboutAction() {
		System.out.println("onMnuItemAboutAction");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
				
	}

}
