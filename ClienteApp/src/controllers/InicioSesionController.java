package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Hotel;
import model.Usuario;

public class InicioSesionController {

	App app = new App();

	@FXML
	private Button btnIniciarSesion;

	@FXML
	private Button btnSalir;

	@FXML
	private Button btnRegistrarse;

	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField txtContrasenia;
	
	private Alert alert;
	private DialogPane dialogPane;

	@FXML
	void iniciarSesionEvent(ActionEvent event) {
		iniciarSesionAction();
	}

	@FXML
	void registrarseEvent(ActionEvent event) {
		registrarseAction();
	}

	@FXML
	void salirEvent(ActionEvent event) {
		System.exit(0);
	}

	public void setAplicacion(App app) {
		this.app = app;

	}
	
	/**
	 * Este boton inicia sesion de un usuario
	 */
	private void iniciarSesionAction() {

		if (isInputValid()) {
			String contrasenia = txtContrasenia.getText();
			String usuario = txtUsuario.getText();
			boolean usuarioValido = app.hotel.verificarCuenta(usuario, contrasenia);
			System.out.println(usuarioValido);

			if (usuarioValido) {
				Usuario usuarioActual = app.hotel.getUsuario(usuario, contrasenia);
				Hotel.setUsuarioActual(usuarioActual);
				app.mostrarVentanaReservas();
			} else {
				txtUsuario.setText("");
				txtContrasenia.setText("");

				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Usuario no existe");
				alert.setHeaderText("Por favor ingrese un usuario y una contraseña validos");
				
				dialogPane = alert.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("../resources/Styles.css").toString());
				dialogPane.getStyleClass().add("dialogs");

				alert.showAndWait();
			}
		}

	}
	
	/**
	 * Este metodo compruba que los campos sean validos y no estén vacios
	 * 
	 * @return
	 */
	private boolean isInputValid() {
		String errorMensaje = "";

		if (txtUsuario == null || txtUsuario.getText().length() == 0) {
			errorMensaje += "No es valido el usuario!\n";
		}

		if (txtContrasenia == null || txtContrasenia.getText().length() == 0) {
			errorMensaje += "No es valida la contraseña!\n";
		}

		if (errorMensaje.length() == 0) {
			return true;
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Campos Invalidos");
			alert.setHeaderText("Por favor corrija los campos incorrectos");
			alert.setContentText(errorMensaje);
			
			dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../resources/Styles.css").toString());
			dialogPane.getStyleClass().add("dialogs");

			alert.showAndWait();

			return false;
		}
	}
	
	
	/**
	 * Este boton registra un usuario nuevo
	 */
	private void registrarseAction() {
		Usuario tempUsuario = new Usuario("", "", "", "", "", "", "");
		boolean okClicked = app.mostrarVentanaRegistrarUsuario(tempUsuario);

		if (okClicked) {
			app.hotel.getListaUsuarios().add(tempUsuario);
			txtUsuario.setText(tempUsuario.getUsuario());
			txtContrasenia.setText(tempUsuario.getContrasenia());
			btnIniciarSesion.setDisable(false);
		}
	}

}
