package persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Habitacion;
import model.Hotel;
import model.Reserva;
import model.Usuario;




public class Persistencia {

	public static final String RUTA_ARCHIVO_USUARIOS = "src/resources/archivoUsuarios.txt";
	public static final String RUTA_ARCHIVO_RESERVAS = "src/resources/archivoReservas.txt";
	
	App app = new App();

	
	
	public static void cargarDatosArchivos(Hotel hotel) throws FileNotFoundException, IOException {
		//cargar archivos usuarios
		ObservableList<Usuario> usuariosCargados = cargarUsuarios();
		
		if (!usuariosCargados.isEmpty()) {
			if(usuariosCargados.size() > 0) {
				hotel.getListaUsuarios().addAll(usuariosCargados);
				System.out.println(hotel.getListaUsuarios());
			}
				
			
		}
		//cargar archivo objetos
		ObservableList<Reserva> reservasCargadas = cargarReservas();
		
		if (!reservasCargadas.isEmpty()) {
			if(reservasCargadas.size() > 0) {
				hotel.getListaReservas().addAll(reservasCargadas);
				System.out.println(hotel.getListaReservas());
			}
				
		}
		
		//cargar archivo empleados
		
		//cargar archivo prestamo
		
	}
	
	public static void guardarUsuarios(List<Usuario> listUsuario) throws IOException {
		
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(Usuario usuario : listUsuario) 
		{
			contenido+= usuario.getCedula() + "," + usuario.getEmail() + "," + usuario.getTelefono() +"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);
	}
	
	public static void guardarReservas(List<Reserva> listUsuario) throws IOException {
		
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(Reserva reserva : listUsuario) 
		{
			contenido+= reserva.getUsuario().getCedula() + "," + reserva.getId() + "," + reserva.getFechaEntrada() + "," + reserva.getFechaSalida() +  "\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_RESERVAS, contenido, false);
	}
	
	
	
//	----------------------LOADS------------------------
	
	private static ObservableList<Usuario> cargarUsuarios() throws IOException {
		
		ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
		
		ObservableList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
		
		String linea="";
		
		if (!contenido.isEmpty()) {
			for (int i = 0; i < contenido.size(); i++)
			{
				linea = contenido.get(i);
				Usuario usuario = new Usuario();
				usuario.setCedula(linea.split(",")[0]);
				usuario.setEmail(linea.split(",")[1]);
				usuario.setTelefono(linea.split(",")[2]);
				usuarios.add(usuario);
			}
		}
		return usuarios;
	}
	
	private static ObservableList<Reserva> cargarReservas() throws IOException {
		
		ObservableList<Reserva> reservas = FXCollections.observableArrayList();
		
		ObservableList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
		
		String linea="";
		
		if (!contenido.isEmpty()) {
			for (int i = 0; i < contenido.size(); i++)
			{
				linea = contenido.get(i);
				Reserva reserva = new Reserva();
				reserva.getUsuario().setCedula(linea.split(",")[0]);
				reserva.setId(linea.split(",")[1]);
				reserva.setFechaEntrada(LocalDate.parse(linea.split(",")[2]));
				reserva.setFechaSalida(LocalDate.parse(linea.split(",")[3]));
				reservas.add(reserva);
			}
		}
		return reservas;
	}
	
		
//	----------------------SAVES------------------------
	
//	/**
//	 * Guarda en un archivo de texto todos la informaci�n de las personas almacenadas en el ArrayList
//	 * @param objetos
//	 * @param ruta
//	 * @throws IOException
//	 */
//	
//	public static void guardarObjetos(ArrayList<Empleado> listaEmpleados, String ruta) throws IOException  {
//		String contenido = "";
//		
//		for(Empleado empleadoaux:listaEmpleados) {
//			contenido+= empleadoaux.getNombre()+","+empleadoaux.getApellido()+","+empleadoaux.getId()+"\n";
//		}
//		ArchivoUtil.guardarArchivo(ruta, contenido, true);
//	}


	

}
