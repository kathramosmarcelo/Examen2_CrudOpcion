package com.cibertec.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.cibertec.bean.Opcion;
import com.cibertec.bean.Usuario;
import com.cibertec.dao.CapaDAO;
import com.cibertec.dao.CapaDAOImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ParentPackage("dawi")
public class LoginAction extends ActionSupport {

	private static final Log log = LogFactory.getLog(LoginAction.class);
	private @Getter @Setter Usuario usuario;
	private @Getter @Setter String mensaje;
	private Map<String, Object> session = ActionContext.getContext().getSession();

	@Action(value = "/loginUsuario", results = { @Result(name = "success", location = "/intranetHome.jsp"), @Result(name = "error", location = "/intranetLogin.jsp") })
	public String login() {
		log.info("En registrar login");
		CapaDAO service = new CapaDAOImpl();
		try {
			List<Usuario> aux = service.login(usuario);
			if (aux == null || aux.size() ==0) {
				mensaje = "El usuario no existe";
				return ERROR;
			} else {
				List<Opcion> menus = service.traerEnlacesDeUsuario(aux.get(0).getIdUsuario());
				session.put("objMenus", menus);
				session.put("objUsuario", aux.get(0));
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	

	
	
	@Action(value = "/logout", results = { @Result(name = "success", location = "/intranetLogin.jsp") })
	public String logout() {
		log.info("En listar logout ");

		session.clear();
		// esponse.setHeader("Cache-control", "no-cache");
		// response.setHeader("Expires", "0");
		// response.setHeader("Pragma", "no-cache");
		mensaje = "El usuario salió de sesión";
		return SUCCESS;
	}

}
