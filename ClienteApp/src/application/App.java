package application;

import javafx.stage.Stage;
import java.io.IOException;

import controllers.InicioSesionController;
import controllers.RegistrarUsuarioController;
import controllers.ReservasClienteController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;


/**
 * @author Juan E. Cardona
 * @author Juan E. Ramirez
 * @author Jose M. Taborda
 */


public class App extends Application {

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
		mostrarVentanaLogin();
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
	public void mostrarVentanaLogin() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("../views/InicioSesion.fxml"));

			AnchorPane rootLayout = (AnchorPane) loader.load();

			InicioSesionController inicioSesionController = loader.getController();
			inicioSesionController.setAplicacion(this);

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

	public void mostrarVentanaReservas() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("../views/ReservasCliente.fxml"));

			AnchorPane rootLayout = (AnchorPane) loader.load();

			ReservasClienteController reservasClienteController = loader.getController();
			reservasClienteController.setAplicacion(this);

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

	public void mostrarVentanaRegistrarse() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("../views/RegistrarUsuario.fxml"));

			AnchorPane rootLayout = (AnchorPane) loader.load();

			RegistrarUsuarioController registrarUsuarioController = loader.getController();
			registrarUsuarioController.setAplicacion(this);

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

