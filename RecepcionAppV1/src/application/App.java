package application;

import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import controllers.FormularioController;
import controllers.RecepcionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.Hotel;
import model.Reserva;


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

	public Reserva mostrarVentanaFormularioReserva(Reserva reserva, RecepcionController recepcionController) {
		
		FormularioController formularioController = new FormularioController();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("../views/FormularioReserva.fxml"));

			AnchorPane rootLayout = (AnchorPane) loader.load();

			formularioController = loader.getController();
			formularioController.setAplicacion(this);
			formularioController.setController(recepcionController);
			formularioController.mostrarReserva(reserva);
			

			Scene scene = new Scene(rootLayout);

			// Establecer el color de relleno del Scene a transparente
			scene.setFill(Color.TRANSPARENT);
			// Agregar el archivo de estilos style.css
			scene.getStylesheets().add(getClass().getResource("../resources/Styles.css").toString());
			
			 // Configurar el DatePicker para bloquear fechas anteriores
	        DatePicker datePickerEntrada = formularioController.getDpFechaSalida(); // Aseg�rate de que el controlador tenga una referencia al DatePicker
	        datePickerEntrada.setDayCellFactory(getDayCellFactory());
	        
	        DatePicker datePickerSalida = formularioController.getDpFechaLlegada(); // Aseg�rate de que el controlador tenga una referencia al DatePicker
	        datePickerSalida.setDayCellFactory(getDayCellFactory());

			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formularioController.reserva;		
		
	}
	
	private Callback<DatePicker, DateCell> getDayCellFactory() {
        return datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                
                // Bloquear fechas anteriores a la actual
                if (item.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
    }
	
	public static String generateRandomId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
	


}

