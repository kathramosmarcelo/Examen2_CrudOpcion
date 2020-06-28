package com.cibertec.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.cibertec.bean.Opcion;
import com.cibertec.dao.CapaDAO;
import com.cibertec.dao.CapaDAOImpl;
import com.opensymphony.xwork2.ActionSupport;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ParentPackage("dawi")
public class OpcionAction extends ActionSupport {

	private static final Log log = LogFactory.getLog(OpcionAction.class);
	//Para la consulta
	private @Getter @Setter List<Opcion> lstOpcion= new ArrayList<Opcion>();
	
	private @Getter @Setter String filtro ="";
	
	//para el registrar y actualizar
	
	private @Getter @Setter Opcion opcion;
	
	//para eliminar
	private @Getter @Setter String id;
	
	//
	private CapaDAO service = new CapaDAOImpl(); 
	
	@Action(value="/eliminaOpcion",results={@Result(name="success", location="/crudOpcion.jsp")	})
	public String elimina(){
		log.info("En eliminar Opcion");
		try {
			service.eliminaOpcion(Integer.parseInt(id));
			lstOpcion =  service.listaOpcion(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value="/actualizaOpcion",results={@Result(name="success", location="/crudOpcion.jsp")})
	public String actualiza(){
		log.info("En actualizar Opcion");	
		try {
			service.actualizaOpcion(opcion);
			lstOpcion =  service.listaOpcion(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value="/registraOpcion",results={@Result(name="success", location="/crudOpcion.jsp")})
	public String registra(){
		log.info("En registrar concurso");		
		try {
			service.insertaOpcion(opcion);
			lstOpcion =  service.listaOpcion(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value="/consultaOpcion",results={@Result(name="success", location="/crudOpcion.jsp")})
	public String listar(){
		log.info("En listar opcion");	
		try {
			lstOpcion =  service.listaOpcion(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
}

