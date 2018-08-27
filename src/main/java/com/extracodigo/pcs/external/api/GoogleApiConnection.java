package com.extracodigo.pcs.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

public interface GoogleApiConnection {
	
	public void setProxy();
	public HttpTransport getHttpTransport();
	public Credential getCredential();
	public String getApplicationName();
	public JsonFactory getJsonFactory();
}
