package com.extracodigo.pcs.external.api.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.extracodigo.pcs.configuration.AppConfiguration;
import com.extracodigo.pcs.external.api.BloggerApi;
import com.extracodigo.pcs.external.api.DomApi;
import com.extracodigo.pcs.external.api.GoogleApiConnection;
import com.extracodigo.pcs.service.PostService;
import com.google.api.services.blogger.Blogger;
import com.google.api.services.blogger.model.Post;

@Controller("bloggerApiImpl")
public class BloggerApiImpl implements BloggerApi {
	
	private static final Logger logger = LoggerFactory.getLogger(BloggerApiImpl.class);
	
	@Autowired
	@Qualifier("oAuth2GoogleApiConnectionImpl")
	private GoogleApiConnection googleApiConnection;
	
	@Autowired
	@Qualifier("appConfigurationImpl")
	private AppConfiguration appConfiguration;
	
	@Autowired
	@Qualifier("domApiImpl")
	private DomApi domApi;
	
	@Autowired
	@Qualifier("postServiceImpl")
	private PostService postService;
	
	private Blogger blogger;
	
	@PostConstruct
	public void init(){
		blogger = new Blogger
				.Builder(
						googleApiConnection.getHttpTransport(),
						googleApiConnection.getJsonFactory(),
						googleApiConnection.getCredential())
			    .setApplicationName(googleApiConnection.getApplicationName())
			    .build();
	}

	@Override
	public void post(com.extracodigo.pcs.entity.Post postEntity) {
		if (appConfiguration.getIsProxy().equals("true")) googleApiConnection.setProxy();
		
		try {
			Post post = postEntityToPostBlogger(postEntity);
			if (post != null) {
				blogger.posts()
					   .insert(appConfiguration.getBloggerId(), post)
	        	   	   .setOauthToken(googleApiConnection.getCredential().getAccessToken())
	        	   	   .execute();
				logger.info(post.toPrettyString());
				
				postEntity.setPublished(true);
				postService.update(postEntity);
			}
			
		} catch (IOException e) {
			logger.error(e.getStackTrace().toString());
		}
		
	}
	
	@Override
	public void post(List<com.extracodigo.pcs.entity.Post> postEntities) {
		postEntities.stream().forEach((postEntity) -> post(postEntity));
	}
	
	
	private Post postEntityToPostBlogger(com.extracodigo.pcs.entity.Post postEntity) {
		String content = domApi.getContentByPost(postEntity);
		if (content == "") return null;
		
		Post post = new Post();
		try {
			post.setContent(new String(content.getBytes("ISO-8859-15"), "UTF-8"));
			post.setTitle(new String(postEntity.getTitle().getBytes("ISO-8859-15"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return post;
	}

}
