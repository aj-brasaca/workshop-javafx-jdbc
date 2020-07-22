package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.services.DepartmentService;

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
	
	// NO MÉTODO loadView FOI UTILIZADO GENERICS PARA NÃO
	// PRECISAR FAZER DUAS VERSÕES DESSE MÉTODO, JÁ QUE
	// HÁ A NECESSIDADE DE EXECUTAR UMA AÇÃO NA INICIALIZAÇÃO
	// E A OUTRA NÃO.
	
	@FXML
	public void onMnuItemDepartmentAction() {
		
		// O segundo parâmetro é uma ação de inicialização de
		// DepartmentListController (é uma função lambda passada paro método loadView)
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMnuItemAboutAction() {
		// Carregará o conteúdo desse fxml no fxml da janela principal em runtime.
		// Como aqui não há ação alguma a ser realizada no carregamento da view About,
		// então o segundo argumento é uma função que não realiza nada.
		loadView("/gui/About.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
				
	}
		
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
	
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
				
				// AS DUAS LINHAS ABAIXO IRÃO EXECUTAR A FUNÇÃO PASSADA COMO ARGUMENTO
				// NO SEGUNDO PARÂMETRO DA CHAMADA A ESTE MÉTODO
				
				// Método getController() irá retornar o tipo do controller
				// que for passado no segundo parâmetro deste método
				// O "T" indica o tipo do controler e é genérico então (será do tipo do controller passado)
				T controller = loader.getController();
				// Para executar a função passada no segundo parâmetro
				initializingAction.accept(controller);
				
				
			} catch (IOException e) {
				Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
			}
	}

}
