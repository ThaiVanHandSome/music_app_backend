package vn.iostar.springbootbackend;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import vn.iostar.springbootbackend.config.TwilioConfig;
import vn.iostar.springbootbackend.entity.Provider;
import vn.iostar.springbootbackend.entity.Role;
import vn.iostar.springbootbackend.entity.User;
import vn.iostar.springbootbackend.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Optional;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableSwagger2
@EnableWebMvc
@EnableConfigurationProperties
public class SpringbootBackendApplication {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	private TwilioConfig twilioConfig;

	@Autowired
	private UserService userService;

	public SpringbootBackendApplication(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	@PostConstruct
	public void setup() {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
						.addMapping("/**")
						.allowedOrigins("http://localhost:3000", "http://10.0.2.2:8989", "http://192.168.1.245:8989")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true);;
			}
		};
	}
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createUserIfNeeded();
	}

	private void createUserIfNeeded() {
		Optional<User> user = userService.getUserByEmail("danitbadao1234@gmail.com");
		if(user.isEmpty()) {
			User admin = new User();
			admin.setEmail("danitbadao1234@gmail.com");
			admin.setFirstName("Thai");
			admin.setLastName("Van");
			admin.setPassword(passwordEncoder.encode("Vanbs1234qq!!"));
			admin.setRole(Role.ADMIN);
			admin.setProvider(Provider.DATABASE);
			admin.setActive(true);
			admin.setAvatar("");
			admin.setNickname("");
			admin.setPhoneNumber("0123456789");
			userService.save(admin);
		}
	}

}
