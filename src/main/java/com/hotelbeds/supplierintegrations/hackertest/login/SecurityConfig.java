package com.hotelbeds.supplierintegrations.hackertest.login;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;
import com.hotelbeds.supplierintegrations.hackertest.util.IPUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private HackerDetector detector;

	private static Logger LOGGER = null;
	static {
		LOGGER = Logger.getLogger(SecurityConfig.class.getName());

		FileHandler fh;

		try {

			fh = new FileHandler(Paths.get("").toAbsolutePath() + "/src/main/resources/logs/login.log");
			LOGGER.addHandler(fh);

			SimpleFormatter formatter = new SimpleFormatter() {
				private static final String format = "%3$s %n";

				@Override
				public synchronized String format(LogRecord lr) {
					return String.format(format, new Date(lr.getMillis()), lr.getLevel().getLocalizedName(),
							lr.getMessage());
				}
			};
			fh.setFormatter(formatter);

		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}

	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/fecha/**").hasRole("USER")
				.anyRequest().authenticated().and().formLogin()
				// .loginPage("/login")
				.successHandler(new AuthenticationSuccessHandler() {

					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						LOGGER.info(IPUtil.getIpFromRequest(request) + "," + System.currentTimeMillis()
								+ ",SIGNIN_SUCCESS" + "," + request.getParameter("username"));

						detector.parseLine(IPUtil.getIpFromRequest(request) + "," + System.currentTimeMillis()
								+ ",SIGNIN_SUCCESS" + "," + request.getParameter("username"));
						if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString()
								.contains("ADMIN")) {
							response.sendRedirect(request.getContextPath() + "/admin");
						} else {
							response.sendRedirect(request.getContextPath() + "/fecha");
						}

					}
				}).failureHandler(new AuthenticationFailureHandler() {

					@Override
					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
							AuthenticationException exception) throws IOException, ServletException {
						LOGGER.warning(IPUtil.getIpFromRequest(request) + "," + System.currentTimeMillis()
								+ ",SIGNIN_FAILURE" + "," + request.getParameter("username"));
						detector.parseLine(IPUtil.getIpFromRequest(request) + "," + System.currentTimeMillis()
								+ ",SIGNIN_FAILURE" + "," + request.getParameter("username"));
						response.sendRedirect(request.getContextPath() + "/login?error");
					}
				});

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER").and().withUser("admin")
				.password("{noop}admin").roles("ADMIN");

	}

}
