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
	
	// NO M�TODO loadView FOI UTILIZADO GENERICS PARA N�O
	// PRECISAR FAZER DUAS VERS�ES DESSE M�TODO, J� QUE
	// H� A NECESSIDADE DE EXECUTAR UMA A��O NA INICIALIZA��O
	// E A OUTRA N�O.
	
	@FXML
	public void onMnuItemDepartmentAction() {
		
		// O segundo par�metro � uma a��o de inicializa��o de
		// DepartmentListController (� uma fun��o lambda passada paro m�todo loadView)
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMnuItemAboutAction() {
		// Carregar� o conte�do desse fxml no fxml da janela principal em runtime.
		// Como aqui n�o h� a��o alguma a ser realizada no carregamento da view About,
		// ent�o o segundo argumento � uma fun��o que n�o realiza nada.
		loadView("/gui/About.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
				
	}
		
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
	
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
				VBox newVbox = loader.load();
				
				// Pegando refer�ncia para a cena da janela principal (AULA 272)
				Scene mainScene = Main.getMainScene();
				// Pegando refer�ncia para a VBox da janela principal (AULA 272)
				VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
				// Pegando refer�ncia para o menu
				Node mainMenu = mainVBox.getChildren().get(0);
				// Limpando todos os filhos do mainVBox
				mainVBox.getChildren().clear();
				// Adicionando o menu novamente e os filhos do newVBox
				mainVBox.getChildren().add(mainMenu);
				mainVBox.getChildren().addAll(newVbox.getChildren());
				
				// AS DUAS LINHAS ABAIXO IR�O EXECUTAR A FUN��O PASSADA COMO ARGUMENTO
				// NO SEGUNDO PAR�METRO DA CHAMADA A ESTE M�TODO
				
				// M�todo getController() ir� retornar o tipo do controller
				// que for passado no segundo par�metro deste m�todo
				// O "T" indica o tipo do controler e � gen�rico ent�o (ser� do tipo do controller passado)
				T controller = loader.getController();
				// Para executar a fun��o passada no segundo par�metro
				initializingAction.accept(controller);
				
				
			} catch (IOException e) {
				Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
			}
	}

}
