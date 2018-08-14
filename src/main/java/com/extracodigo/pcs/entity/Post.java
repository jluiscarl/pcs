package com.extracodigo.pcs.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="posts")
public class Post extends AuditModel {

	private static final long serialVersionUID = 5970296457710940462L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String urlToImage;
	
	@NotBlank
	private String url;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, optional=false)
	private Source source;
	
	public Post () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = (description.length() > 140) ? description.substring(0,140) : description;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}
	
	@Override
	public String toString() {
		return "Post{"
				+ "id:'"+this.getId()+"', "
				+ "title:'"+this.getTitle()+"', "
				+ "description:'"+this.getDescription()+"', "
				+ "urlToImg:'"+this.getUrlToImage()+"', "
				+ "url:'"+this.getUrl()+"', "
				+ "source:'"+this.getSource()+"', "
				+ "createAt:'"+this.getCreatedAt()+"', "
				+ "updatedAt:'"+this.getUpdatedAt()+"'}";
	}
}
