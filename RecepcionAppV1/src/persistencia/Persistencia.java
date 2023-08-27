package persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Hotel;
import model.Usuario;




public class Persistencia {

	public static final String RUTA_ARCHIVO_USUARIOS = "src/resources/archivoUsuarios.txt";
	
	App app = new App();

	
	
	public static void cargarDatosArchivos(Hotel hotel) throws FileNotFoundException, IOException {
		//cargar archivos empleados
		ObservableList<Usuario> usuariosCargados = cargarUsuarios();
		
		if (!usuariosCargados.isEmpty()) {
			if(usuariosCargados.size() > 0) {
				hotel.getListaUsuarios().addAll(usuariosCargados);
				System.out.println(hotel.getListaUsuarios());
			}
				
			
		}
		//cargar archivo objetos
		
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
