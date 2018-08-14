package com.extracodigo.pcs.controller.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

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
import com.extracodigo.pcs.service.SourceService;

@Controller("domApiImpl")
public class DomApiImpl implements DomApi{
	
	private static final Logger logger = LoggerFactory.getLogger(DomApiImpl.class);
	
	@Autowired
	@Qualifier("sourceServiceImpl")
	private SourceService sourceService;

	@Override
	public List<Post> getPostsBySource(Source source) {
		List<Post> posts = new ArrayList<Post>();
		try {
			//Document dom = Jsoup.connect(source.getUrl()).get(); // SIN PROXY
			Document dom = Jsoup.parse(connectToProxy(source.getUrl())); // CON PROXY
			Elements container = dom.select(source.getSelectorContainer());
			posts = container.stream().map((el) -> getPostFromElement(el, source)).collect(Collectors.toList());	
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return posts;
	}
	
	private Post getPostFromElement(Element element, Source source) {
		Post post = new Post();
		post.setTitle(element.select(source.getSelectorTitle()).text());
		post.setDescription(element.select(source.getSelectorDescription()).text());
		post.setUrlToImage(element.select(source.getSelectorImg()).attr("src"));
		post.setUrl(element.select(source.getSelectorTitle()).attr("href"));
		post.setSource(source);
		
		if (post.getUrlToImage() == "") post.setUrlToImage("sin imagen");
		
		logger.info(post.toString());
		
		return post;
	}
	
	private String connectToProxy (String sourceUrl) throws IOException {
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

		 URL url = new URL(sourceUrl);
		  Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxym.redlink.com.ar", 3128)); // or whatever your proxy is
		  HttpURLConnection uc = (HttpURLConnection)url.openConnection(proxy);

		  uc.connect();

		    String line = null;
		    StringBuffer tmp = new StringBuffer();
		    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		    while ((line = in.readLine()) != null) {
		      tmp.append(line);
		    }
		    return String.valueOf(tmp);
	}

	public void setSourceService(SourceService sourceService) {
		this.sourceService = sourceService;
	}

}
