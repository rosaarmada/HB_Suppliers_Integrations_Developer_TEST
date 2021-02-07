package com.hotelbeds.supplierintegrations.hackertest.login;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class LoginController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String WelcomePagePublic() {
		return "[ZONA PUBLICA] Bienvenido, estás en la parte pública de la web!";
	}

	@RequestMapping(value = "/fecha", method = RequestMethod.GET)
	@ResponseBody
	public String userPage(Authentication authentication) {
		return "[ZONA PRIVADA] Bienvenido a la sección de USER";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@ResponseBody
	public String userTest() {
		return "[ZONA PRIVADA] Mostraremos el log";
	}

}
