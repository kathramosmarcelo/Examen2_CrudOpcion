package com.cibertec.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.cibertec.bean.Opcion;
import com.cibertec.bean.Usuario;

public class CapaDAOImpl implements CapaDAO{

	SqlSessionFactory sqlMapper = null;
	{
		String archivo = "ConfiguracionIbatis.xml";
		try {
			Reader reader = Resources.getResourceAsReader(archivo);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Usuario> login(Usuario bean) throws Exception {
		List<Usuario> lista = new ArrayList<Usuario>();
		SqlSession sesion = null; 
		try {
			sesion =  sqlMapper.openSession();
			lista = sesion.selectList("SQL_login", bean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			sesion.close();
		}
		return lista;
	}
	
	@Override
	public List<Opcion> traerEnlacesDeUsuario(String idUsuario) throws Exception {
		List<Opcion> lista = new ArrayList<Opcion>();
		SqlSession sesion = null; 
		try {
			sesion =  sqlMapper.openSession();
			lista = sesion.selectList("SQL_traerEnlacesDeUsuario", idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			sesion.close();
		}
		return lista;
	}

	// ELIMINAR OPCION---------------------------------------------------------------------
	@Override
	public int eliminaOpcion(int idopcion) throws Exception {
		int salida = -1;
		SqlSession session = null;
		try {
			 session = sqlMapper.openSession();
			 salida = session.delete("SQL_elimina_Opcion", idopcion);
			 session.commit();
		} catch (Exception e) {
			 e.printStackTrace();
			 session.rollback();
		} finally{
			 session.close();
		}
		return salida;
	}
	
	
	

	// INSERTAR OPCION---------------------------------------------------------------------
	
	@Override
	public int insertaOpcion(Opcion obj) throws Exception {
		int salida = -1;
		SqlSession session = null;
		try {
			 session = sqlMapper.openSession();
			 salida = session.insert("SQL_inserta_Opcion", obj);
			 session.commit();
		} catch (Exception e) {
			 e.printStackTrace();
			 session.rollback();
		} finally{
			 session.close();
		}
		return salida;
	}
	

	// ACTUALIZAR OPCION---------------------------------------------------------------------
	@Override
	public int actualizaOpcion(Opcion obj) throws Exception {
		int salida = -1;
		SqlSession session = null;
		try {
			 session = sqlMapper.openSession();
			 salida = session.update("SQL_actualiza_Opcion", obj);
			 session.commit();
		} catch (Exception e) {
			 e.printStackTrace();
			 session.rollback();
		} finally{
			 session.close();
		}
		return salida;
	}
	

	// CONSULTA OPCION---------------------------------------------------------------------
	@Override
	public List<Opcion> listaOpcion(String filtro) throws Exception {
		List<Opcion> salida = null;
		SqlSession session = null;
		try {
			//Se crea la conexión(Session) a la BD
			session = sqlMapper.openSession();
			//Se aplica al sentecia en la BD
			salida = session.selectList("SQL_lista_Opcion", filtro+"%");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return salida;
	}
	
	
	
}
