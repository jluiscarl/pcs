package com.extracodigo.pcs.controller.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.extracodigo.pcs.controller.DomApi;
import com.extracodigo.pcs.entity.Post;
import com.extracodigo.pcs.entity.Source;
import com.extracodigo.pcs.helper.AppConfiguration;
import com.extracodigo.pcs.service.SourceService;

@Controller("domApiImpl")
public class DomApiImpl implements DomApi{
	
	private static final Logger logger = LoggerFactory.getLogger(DomApiImpl.class);
	
	@Autowired
	@Qualifier("sourceServiceImpl")
	private SourceService sourceService;
	
	@Autowired
	@Qualifier("appConfigurationImpl")
	private AppConfiguration appConfiguration;

	@Override
	public List<Post> getPostsBySource(Source source) {
		List<Post> posts = new ArrayList<Post>();
		try {
			Document dom = (appConfiguration.getIsProxy().equals("true")) ?
					Jsoup.parse(connectToProxy(source.getUrl())) :
					Jsoup.connect(source.getUrl()).get();
			Elements container = dom.select(source.getSelectorContainer());
			posts = container.stream().map((el) -> getPostFromElement(el, source)).collect(Collectors.toList());	
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return posts;
	}
	
	private Post getPostFromElement(Element element, Source source) {
		Post post = new Post();
		post.setTitle(element.select(source.getSelectorTitle()).text());
		post.setDescription(element.select(source.getSelectorDescription()).text());
		post.setUrlToImage(element.select(source.getSelectorImg()).attr("src"));
		post.setUrl(element.select(source.getSelectorTitle()).attr("href"));
		post.setPublished(false);
		post.setSource(source);
						
		return post;
	}
	
	private String connectToProxy (String sourceUrl) throws IOException {
		logger.info("appConfiguration.getIsProxyAuth()" + appConfiguration.getIsProxyAuth());
		if (appConfiguration.getIsProxyAuth().equals("true")) {
			logger.info("appConfiguration.getProxyUsername()" + appConfiguration.getProxyUsername());
			logger.info("appConfiguration.getProxyPassword()" + appConfiguration.getProxyPassword());
			final String authUser = appConfiguration.getProxyUsername();
			final String authPassword = appConfiguration.getProxyPassword();
			Authenticator.setDefault(
	           new Authenticator() {
	              public PasswordAuthentication getPasswordAuthentication() {
	                 return new PasswordAuthentication(
	                       authUser, authPassword.toCharArray());
	              }
	           }
	        );
		}
		
		URL url = new URL(sourceUrl);
		StringBuffer tmp = new StringBuffer();
		if (url.getProtocol().toLowerCase().equals("http")) {
			logger.info("appConfiguration.getProxyHttpHost()" + appConfiguration.getProxyHttpHost());
			logger.info("appConfiguration.getProxyHttpPort()" + appConfiguration.getProxyHttpPort());
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
							appConfiguration.getProxyHttpHost(),
							Integer.parseInt(appConfiguration.getProxyHttpPort())));
			
			HttpURLConnection uc = (HttpURLConnection) url.openConnection(proxy);

			uc.connect();
			
			String line = null;
		    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		    while ((line = in.readLine()) != null) {
		      tmp.append(line);
		    }
			
		} else {
			logger.info("appConfiguration.getProxyHttpsHost()" + appConfiguration.getProxyHttpsHost());
			logger.info("appConfiguration.getProxyHttpsPort()" + appConfiguration.getProxyHttpsPort());
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
							appConfiguration.getProxyHttpsHost(),
							Integer.parseInt(appConfiguration.getProxyHttpsPort())));
			
			HttpsURLConnection uc = (HttpsURLConnection) url.openConnection(proxy);

			uc.connect();
			
			String line = null;
		    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		    while ((line = in.readLine()) != null) {
		      tmp.append(line);
		    }
		}

		return String.valueOf(tmp);
	}
	
	@Override
	public String getContentByPost(Post post) {
		String content = "";
		try {
			Document dom = (appConfiguration.getIsProxy().equals("true")) ?
					Jsoup.parse(connectToProxy(post.getUrl())) :
					Jsoup.connect(post.getUrl()).get();
			Element container = dom.select(post.getSource().getSelectorContent()).first();
			if (container != null) {
				content = (new StringFormattedMessage(appConfiguration.getBloggerCreditsTag(), post.getUrl(), post.getUrl())).toString();
				content += container.html();
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return content;
	}

}
