package com.extracodigo.pcs.helper.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import com.extracodigo.pcs.helper.AppConfiguration;

@Controller("appConfigurationImpl")
public class AppConfigurationImpl implements AppConfiguration {
		
	private Properties properties = new Properties();
	
	private String googleAppname;
	private String googleDataStoreDir;
	private String googleClientSecretsFilename;
	private String googleUser;
	
	private String isProxy;
	private String isProxyAuth;
	private String proxyUsername;
	private String proxyPassword;
	private String proxyHttpHost;
	private String proxyHttpPort;
	private String proxyHttpsHost;
	private String proxyHttpsPort;
	
	private String bloggerId;
	private String bloggerCreditsTag;
	
	@PostConstruct
	public void init() {
		try {
			properties.load(new FileInputStream(AppConfigurationImpl.class.getClassLoader().getResource("application.properties").getFile()));
			setAllProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setAllProperties() {
		/*
		 * PROPERTIES TO GOOGLE
		 */
		googleAppname = properties.getProperty("google.appname");
		googleDataStoreDir = properties.getProperty("google.data.store.dir");
		googleClientSecretsFilename = properties.getProperty("google.client.secrets.filename");
		googleUser = properties.getProperty("google.user");
		
		
		/*
		 * PROPERTIES TO PROXY
		 */
		isProxy = properties.getProperty("is.proxy","false");
		isProxyAuth = properties.getProperty("is.proxy.auth","false");
		proxyUsername = properties.getProperty("proxy.username");
		proxyPassword = properties.getProperty("proxy.password");
		proxyHttpHost = properties.getProperty("proxy.http.host");
		proxyHttpPort = properties.getProperty("proxy.http.port");
		proxyHttpsHost = properties.getProperty("proxy.https.host");
		proxyHttpsPort = properties.getProperty("proxy.https.port");
		
		/*
		 * PROPERTIES TO BLOGGER
		 */
		bloggerId = properties.getProperty("blogger.id");
		bloggerCreditsTag = properties.getProperty("blogger.credits.tag");
	}

	@Override
	public String getGoogleAppname() {
		return googleAppname;
	}

	@Override
	public String getGoogleDataStoreDir() {
		return googleDataStoreDir;
	}

	@Override
	public String getGoogleClientSecretsFilename() {
		return googleClientSecretsFilename;
	}

	@Override
	public String getGoogleUser() {
		return googleUser;
	}

	@Override
	public String getIsProxy() {
		return isProxy;
	}

	@Override
	public String getIsProxyAuth() {
		return isProxyAuth;
	}

	@Override
	public String getProxyUsername() {
		return proxyUsername;
	}

	@Override
	public String getProxyPassword() {
		return proxyPassword;
	}

	@Override
	public String getProxyHttpHost() {
		return proxyHttpHost;
	}

	@Override
	public String getProxyHttpPort() {
		return proxyHttpPort;
	}

	@Override
	public String getProxyHttpsHost() {
		return proxyHttpsHost;
	}

	@Override
	public String getProxyHttpsPort() {
		return proxyHttpsPort;
	}
	
	@Override
	public String getBloggerId() {
		return bloggerId;
	}
	
	@Override
	public String getBloggerCreditsTag() {
		return bloggerCreditsTag;
	}
	
}
