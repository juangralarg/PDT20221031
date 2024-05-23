package beans;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.services.ItrBeanRemote;
import com.services.UsuarioBeanRemote;

public class BeanIntances {

	static {
		try {
			usuarioBean = InitialContext.doLookup("/ProyectoFinalServidor/UsuarioBean!com.services.UsuarioBeanRemote");
			itrBean = InitialContext.doLookup("/ProyectoFinalServidor/ItrBean!com.services.ItrBeanRemote");
		} catch (NamingException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static UsuarioBeanRemote usuarioBean;
	private static ItrBeanRemote itrBean;

	public static UsuarioBeanRemote user() {
		return usuarioBean;
	}

	public static ItrBeanRemote itr() {
		return itrBean;
	}
}
