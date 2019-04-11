package upb.sparql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Bean
	public QueryBean queryBean() {
		return new QueryBean();
	}
	
	@Bean
	public StoreBean storeBean() {
		return new StoreBean();
	}
}
