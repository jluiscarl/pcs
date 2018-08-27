package com.extracodigo.pcs.controller.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.extracodigo.pcs.controller.GoogleApiConnection;
import com.extracodigo.pcs.helper.AppConfiguration;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

@Controller("oAuth2GoogleApiConnectionImpl")
public class OAuth2GoogleApiConnectionImpl implements GoogleApiConnection {
		
	
	@Autowired
	@Qualifier("appConfigurationImpl")
	private AppConfiguration appConfiguration;
	
	private static String APPLICATION_NAME;
	private static java.io.File DATA_STORE_DIR;;
	private static FileDataStoreFactory dataStoreFactory;
	private static HttpTransport httpTransport;
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final List<String> SCOPES = Arrays.asList(
			  "https://www.googleapis.com/auth/blogger",
		      "https://www.googleapis.com/auth/blogger.readonly");
	
	private static GoogleClientSecrets clientSecrets;
	
	private static Credential credential;
	
	@Override
	public void setProxy() {
		if (appConfiguration.getIsProxyAuth().equals("true")) {
			System.setProperty("http.proxyUser", appConfiguration.getProxyUsername());
			System.setProperty("http.proxyPassword", appConfiguration.getProxyPassword());
			System.setProperty("https.proxyUser", appConfiguration.getProxyUsername());
			System.setProperty("https.proxyPassword", appConfiguration.getProxyPassword());
		}
		
		System.setProperty("http.proxyHost", appConfiguration.getProxyHttpHost());
		System.setProperty("http.proxyPort", appConfiguration.getProxyHttpPort());
		System.setProperty("https.proxyHost", appConfiguration.getProxyHttpsHost());
		System.setProperty("https.proxyPort", appConfiguration.getProxyHttpsPort());
	}
	
	
	@PostConstruct
	public void init() {
		APPLICATION_NAME = appConfiguration.getGoogleAppname();
		DATA_STORE_DIR =
				new File(System.getProperty("user.home"),appConfiguration.getGoogleDataStoreDir());
		
		  if (appConfiguration.getIsProxy().equals("true")) setProxy();
		  
		  try {
			dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
			httpTransport = new NetHttpTransport.Builder().build();
			  clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
					  new InputStreamReader(OAuth2GoogleApiConnectionImpl.class.getResourceAsStream(appConfiguration.getGoogleClientSecretsFilename())));

			  GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow
					  .Builder(httpTransport, JSON_FACTORY, clientSecrets,SCOPES)
					  .setDataStoreFactory(dataStoreFactory)
					  .setAccessType("offline")
					  .setApprovalPrompt("force")
					  .build();

			  credential =
			      new AuthorizationCodeInstalledApp(
			    		  flow,
			    		  new LocalServerReceiver
			    		  	.Builder()
			    		  	.setPort(8888)
			    		  	.build())
			      .authorize(appConfiguration.getGoogleUser());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public HttpTransport getHttpTransport() {
		return httpTransport;
	}

	@Override
	public Credential getCredential() {
		return credential;
	}

	@Override
	public String getApplicationName() {
		return APPLICATION_NAME;
	}

	@Override
	public JsonFactory getJsonFactory() {
		return JSON_FACTORY;
	}

	
}
