package application;

import javafx.stage.Stage;
import java.io.IOException;

import controllers.FormularioController;
import controllers.RecepcionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import model.Hotel;


/**
 * @author Juan E. Cardona
 * @author Juan E. Ramirez
 * @author Jose M. Taborda
 */


public class App extends Application {
	
	public Hotel hotel = new Hotel();

	// Declaracion de variables
	private Stage primaryStage;
	public Stage dialogStage;

	/**
	 * Metodo start
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.initStyle(StageStyle.TRANSPARENT);
		this.primaryStage.centerOnScreen();
		mostrarVentanaRecepcion();
	}

	/**
	 * Metodo principal del proyecto
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		App.launch(new String[0]);
	}

	/**
	 * Muestra la ventana para que el usuario inicie sesion
	 */
	public void mostrarVentanaRecepcion() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("../views/RecepcionView.fxml"));

			AnchorPane rootLayout = (AnchorPane) loader.load();

			RecepcionController recepcionController = loader.getController();
			recepcionController.setAplicacion(this);

			Scene scene = new Scene(rootLayout);

			// Establecer el color de relleno del Scene a transparente
			scene.setFill(Color.TRANSPARENT);
			// Agregar el archivo de estilos style.css
			scene.getStylesheets().add(getClass().getResource("../resources/Styles.css").toString());

			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mostrarVentanaFormularioReserva() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("../views/FormularioReserva.fxml"));

			AnchorPane rootLayout = (AnchorPane) loader.load();

			FormularioController formularioController = loader.getController();
			formularioController.setAplicacion(this);

			Scene scene = new Scene(rootLayout);

			// Establecer el color de relleno del Scene a transparente
			scene.setFill(Color.TRANSPARENT);
			// Agregar el archivo de estilos style.css
			scene.getStylesheets().add(getClass().getResource("../resources/Styles.css").toString());

			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}


}

