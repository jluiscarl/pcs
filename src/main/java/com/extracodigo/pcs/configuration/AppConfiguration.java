package com.extracodigo.pcs.configuration;

public interface AppConfiguration {
	
	public String getGoogleAppname();
	public String getGoogleDataStoreDir();
	public String getGoogleClientSecretsFilename();
	public String getGoogleUser();
	public String getIsProxy();
	public String getIsProxyAuth();
	public String getProxyUsername();
	public String getProxyPassword();
	public String getProxyHttpHost();
	public String getProxyHttpPort();
	public String getProxyHttpsHost();
	public String getProxyHttpsPort();
	public String getBloggerId();
	public String getBloggerCreditsTag();
}
