package org.llin.demo.browserDOM.config;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.azure.core.http.HttpClient;
import com.azure.core.http.jdk.httpclient.JdkHttpClientBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;

@Configuration
public class AzureConfig {
	
	@Bean
	@Primary
	public SecretClient secretClient() throws Exception {
		
	    // Trust-all manager (local dev / self-signed cert only!)
	    TrustManager[] trustAllCerts = new TrustManager[]{
	            new X509TrustManager() {
	                public X509Certificate[] getAcceptedIssuers() { return null; }
	                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
	                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
	            }
	    };
	
	    SSLContext sslContext = SSLContext.getInstance("TLS");
	    sslContext.init(null, trustAllCerts, new SecureRandom());
	
	    // Build the JDK HttpClient.Builder (NOT the final HttpClient) with the custom SSL context
	    java.net.http.HttpClient.Builder jdkBuilder = java.net.http.HttpClient.newBuilder()
	            .sslContext(sslContext);
	
	    // Wrap it for Azure SDK
	    HttpClient azureHttpClient = new JdkHttpClientBuilder(jdkBuilder).build();
	
	    return new SecretClientBuilder()
	            .vaultUrl("https://localhost:4997")
	            .httpClient(azureHttpClient)
	            .credential(new DummyTokenCredential())   // see class below
	            .buildClient();
	}
}