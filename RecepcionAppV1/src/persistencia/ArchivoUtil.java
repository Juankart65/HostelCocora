package persistencia;


import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Esta clase teine metodo estaticos que permite usarlos sin crear instancias de la clase
 * Lo que se hizo fue crear esta libreria para el manejo de los archivos
 * @author Admin
 *
 */
public  class ArchivoUtil {

	static String fechaSistema = "";
	/**
	 * Este metodo recibe una cadena con el contenido que se quiere guardar en el archivo
	 * @param ruta es la ruta o path donde esta ubicado el archivo
	 * @throws IOException
	 */
	public static void guardarArchivo(String ruta, String contenido, Boolean flagAnexarContenido) throws IOException {

		FileWriter fw = new FileWriter(ruta, flagAnexarContenido);
		BufferedWriter bfw = new BufferedWriter(fw);
		bfw.write(contenido);
		bfw.close();
		fw.close();
	}

	/**
	 * ESte metodo retorna el contendio del archivo ubicado en una ruta,con la lista de cadenas.
	 * @param ruta
	 * @return
	 * @throws IOException
	 */
	public static ObservableList<String> leerArchivo(String ruta) throws IOException {

		ObservableList<String>  contenido = FXCollections.observableArrayList();
		FileReader fr=new FileReader(ruta);
		BufferedReader bfr=new BufferedReader(fr);
		String linea="";
		while((linea = bfr.readLine())!=null)
		{
			contenido.add(linea);
		}
		bfr.close();
		fr.close();
		return contenido;
	}



}
