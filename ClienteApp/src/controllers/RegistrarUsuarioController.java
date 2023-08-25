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
import javafx.stage.Stage;
import model.Usuario;

public class RegistrarUsuarioController {

	@FXML
	private PasswordField txtContraseña;

	@FXML
	private Button btnAceptarUsuario;

	@FXML
	private TextField txtApellidos;

	@FXML
	private TextField txtCrearNombre;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtUsuario;

	@FXML
	private Button btnCancelarUsuario;

	@FXML
	private TextField txtIdentificacion;

	@FXML
	private TextField txtTelefono;

	App app = new App();
	private Stage dialogStage;
	private boolean okClicked = false;
	private Usuario usuario;

	private Alert alert;
	private DialogPane dialogPane;

	@FXML
	void aceptarUsuarioEvent(ActionEvent event) {
		aceptarUsuarioAction();
	}

	private void aceptarUsuarioAction() {
		if (isInputValid()) {
			usuario.setNombre(txtCrearNombre.getText());
			usuario.setApellidos(txtApellidos.getText());
			usuario.setCedula(txtIdentificacion.getText());
			usuario.setEmail(txtEmail.getText());
			usuario.setUsuario(txtUsuario.getText());
			usuario.setContrasenia(txtContraseña.getText());

		}

		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Creado Correctamente");
		alert.setHeaderText("Cliente creado correctamente");
		alert.setContentText("El Cliente fue creado con exito, su nombre de usuario es: " + txtUsuario.getText()
				+ " y su contraseña es: " + txtContraseña.getText());

		dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("../resources/Styles.css").toString());
		dialogPane.getStyleClass().add("dialogs");

		alert.showAndWait();

		okClicked = true;
		dialogStage.close();
	}

	/**
	 * Comprueba si los campos son validos
	 * 
	 * @return
	 */
	private boolean isInputValid() {
		String errorMensaje = "";

		if (txtCrearNombre == null || txtCrearNombre.getText().length() == 0) {
			errorMensaje += "No es valido el nombre!\n";
		}

		if (txtApellidos == null || txtApellidos.getText().length() == 0) {
			errorMensaje += "No son validos los apellidos!\n";
		}

		if (txtIdentificacion == null || txtIdentificacion.getText().length() == 0) {
			errorMensaje += "No es valida la identificación!\n";
		}

		if (txtEmail == null || txtEmail.getText().length() == 0) {
			errorMensaje += "No es valido el email!\n";
		}
		
		if (txtUsuario == null || txtUsuario.getText().length() == 0) {
			errorMensaje += "No es valido el usuario!\n";
		}

		if (txtContraseña == null || txtContraseña.getText().length() == 0) {
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

	@FXML
	void cancelarUsuarioEvent(ActionEvent event) {
		app.mostrarVentanaLogin();
	}

	public void setAplicacion(App app) {
		this.app = app;

	}

	public void mostrarDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;

	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public void mostrarUsuario(Usuario usuario) {
		this.usuario = usuario;

		txtCrearNombre.setText(usuario.getNombre());
		txtApellidos.setText(usuario.getApellidos());
		txtIdentificacion.setText(usuario.getCedula());
		txtUsuario.setText(usuario.getUsuario());
		txtEmail.setText(usuario.getEmail());
		txtContraseña.setText(usuario.getContrasenia());
		txtTelefono.setText(usuario.getTelefono());

	}

}
