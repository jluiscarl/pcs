package com.extracodigo.pcs.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/properties/requests_mapping.properties")
public class RequestsMapping {
	
	@Value("{$SERVICE}")
	public static String SERVICE;
	
	@Value("{$NEWS}")
	public static String NEWS;
}
