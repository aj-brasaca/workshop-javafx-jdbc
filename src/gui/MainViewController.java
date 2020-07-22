package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

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
		loadView("/gui/DepartmentList.fxml");
	}
	
	@FXML
	public void onMnuItemAboutAction() {
		// Carregará o conteúdo desse fxml no fxml da janela principal em runtime.
		loadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
				
	}
	
	private void loadView(String absoluteName) {
	
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
				VBox newVbox = loader.load();
				
				// Pegando referência para a cena da janela principal (AULA 272)
				Scene mainScene = Main.getMainScene();
				// Pegando referência para a VBox da janela principal (AULA 272)
				VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
				// Pegando referência para o menu
				Node mainMenu = mainVBox.getChildren().get(0);
				// Limpando todos os filhos do mainVBox
				mainVBox.getChildren().clear();
				// Adicionando o menu novamente e os filhos do newVBox
				mainVBox.getChildren().add(mainMenu);
				mainVBox.getChildren().addAll(newVbox.getChildren());
				
				
			} catch (IOException e) {
				Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
			}
	}

}
