package com.cibertec.dao;

import java.util.List;

import com.cibertec.bean.Opcion;
import com.cibertec.bean.Usuario;

public interface CapaDAO {

		
	//Seguridad
	public List<Usuario> login(Usuario bean) throws Exception;
	public List<Opcion> traerEnlacesDeUsuario(String idUsuario)	throws Exception ;
	
	
	//opcion
	
	public abstract int eliminaOpcion(int idopcion) throws Exception;
	public abstract int insertaOpcion(Opcion obj) throws Exception;
	public abstract int actualizaOpcion(Opcion obj) throws Exception;
	public abstract List<Opcion> listaOpcion(String filtro) throws Exception;
	
	
	
	
}
