package com.extracodigo.pcs.helper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.blogger.Blogger;
import com.google.api.services.blogger.model.Blog;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;
import com.google.api.services.oauth2.model.Userinfoplus;

public class OAuth2GoogleApiConnection {
	
	private static final Logger logger = LoggerFactory.getLogger(OAuth2GoogleApiConnection.class);
	
	private static final String APPLICATION_NAME = "ExtraCodigo - PCS / 1.0";
	private static final java.io.File DATA_STORE_DIR =
		      new java.io.File(System.getProperty("user.home"), ".store/oauth2_sample");
	private static FileDataStoreFactory dataStoreFactory;
	private static HttpTransport httpTransport;
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final List<String> SCOPES = Arrays.asList(
			  "https://www.googleapis.com/auth/blogger",
		      "https://www.googleapis.com/auth/blogger.readonly");
	
	private static Oauth2 oauth2;
	private static GoogleClientSecrets clientSecrets;
	
	
	private static Credential authorize() throws Exception {
	    // load client secrets
	    clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
	        new InputStreamReader(OAuth2GoogleApiConnection.class.getResourceAsStream("/google_client_secrets.json")));
	    logger.info("pretty: " + clientSecrets.toPrettyString());
	    logger.info("no pretty" + clientSecrets.toPrettyString());
	    if (clientSecrets.getDetails().getClientId().startsWith("Enter")
	            || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
	          logger.info("Enter Client ID and Secret from https://code.google.com/apis/console/ "
	              + "into oauth2-cmdline-sample/src/main/resources/client_secrets.json");
	          System.exit(1);
	        }

	    // authorize
	    
//	    Credential credential = new GoogleAuthorizationCodeFlow.Builder(
//		        httpTransport, JSON_FACTORY, clientSecrets, SCOPES).setDataStoreFactory(
//		    	        dataStoreFactory).build().createAndStoreCredential(new TokenResponse(), "user");
	    
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	            httpTransport, JSON_FACTORY, clientSecrets, SCOPES).setDataStoreFactory(
	            dataStoreFactory).build();
	    
	    logger.info("obteniendo credential");
	    
	    final String authUser = "LJ_E90161";
		final String authPassword = "Liam8900178";



		Authenticator.setDefault(
		               new Authenticator() {
		                  public PasswordAuthentication getPasswordAuthentication() {
		                     return new PasswordAuthentication(
		                           authUser, authPassword.toCharArray());
		                  }
		               }
		            );
    	
    	System.setProperty("http.proxyHost", "proxym.redlink.com.ar");
    	System.setProperty("http.proxyPort", "3128");
    	System.setProperty("https.proxyHost", "proxym.redlink.com.ar");
    	System.setProperty("https.proxyPort", "3128");
	    
	    
	    Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver.Builder().setHost("extracodigo.blogspot.com").setPort( 80 ).build()).authorize("luiscarl.jordan@gmail.com");
	    logger.info("obtenido credential");
	    return credential;
	  }
	
	public static void Oauth2Connect() {
	    try {
	    	
	    	final String authUser = "LJ_E90161";
			final String authPassword = "Liam8900178";



			Authenticator.setDefault(
			               new Authenticator() {
			                  public PasswordAuthentication getPasswordAuthentication() {
			                     return new PasswordAuthentication(
			                           authUser, authPassword.toCharArray());
			                  }
			               }
			            );
	    	
	    	System.setProperty("http.proxyHost", "proxym.redlink.com.ar");
	    	System.setProperty("http.proxyPort", "3128");
	    	System.setProperty("https.proxyHost", "proxym.redlink.com.ar");
	    	System.setProperty("https.proxyPort", "3128");
	    	
	    	
	      httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	      dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
	      // authorization
	      Credential credential = authorize();
	      // set up global Oauth2 instance
	      Blogger blogger = new Blogger.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	      logger.info("este es el token: " + credential.getAccessToken());
	      logger.info("esta es la base url: " + blogger.getBaseUrl());
	      Blog blog = blogger.blogs().getByUrl("http://extracodigo.blogspot.com/").setOauthToken(credential.getAccessToken()).execute();
	      
	      logger.info(blog.getDescription() + "---" + blog.getId());
	      
//	      oauth2 = new Oauth2.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(
//	          APPLICATION_NAME).build();
//	      // run commands
//	      logger.info("este es el token: " + credential.getAccessToken());
//	      tokenInfo("AIzaSyAlJWYO9qXzecuHfA5xW6S7xZiOmNXajqA");
//	      tokenInfo(credential.getAccessToken());
//	      userInfo();
	      // success!
	      return;
	    } catch (IOException e) {
	    	logger.error(e.getMessage());
	    	e.printStackTrace();
	    } catch (Throwable t) {
	      t.printStackTrace();
	    }
	    System.exit(1);
	  }

	  private static void tokenInfo(String accessToken) throws IOException {
	    header("Validating a token");
	    Tokeninfo tokeninfo = oauth2.tokeninfo().setAccessToken(accessToken).execute();
	    logger.info("===: -->" + tokeninfo.toPrettyString());
	    if (!tokeninfo.getAudience().equals(clientSecrets.getDetails().getClientId())) {
	    	logger.error("ERROR: audience does not match our client ID!");
	    }
	  }

	  private static void userInfo() throws IOException {
	    header("Obtaining User Profile Information");
	    Userinfoplus userinfo = oauth2.userinfo().get().execute();
	    logger.info(userinfo.toPrettyString());
	  }

	  static void header(String name) {
		logger.info("");
		logger.info("================== " + name + " ==================");
		logger.info("");
	  }
	
}
