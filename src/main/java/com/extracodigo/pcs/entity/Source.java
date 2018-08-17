package com.extracodigo.pcs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="sources")
public class Source extends AuditModel  {
	
	private static final long serialVersionUID = 2070364557464491455L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@NotNull
	@Column(name="name")
	private String name;
	
	@NotBlank
	@NotNull
	@Column(name="url", unique=true)
	private String url;
	
	@NotBlank
	@NotNull
	@Column(name="selector_container")
	private String selectorContainer;
	
	@NotBlank
	@NotNull
	@Column(name="selector_title")
	private String selectorTitle;
	
	@NotBlank
	@NotNull
	@Column(name="selector_content")
	private String selectorContent;
	
	@NotBlank
	@NotNull
	@Column(name="selector_img")
	private String selectorImg;
	
	@NotBlank
	@NotNull
	@Column(name="selector_description")
	private String selectorDescription;
	
	public Source () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getSelectorContainer() {
		return selectorContainer;
	}

	public void setSelectorContainer(String selectorContainer) {
		this.selectorContainer = selectorContainer;
	}

	public String getSelectorTitle() {
		return selectorTitle;
	}

	public void setSelectorTitle(String selectorTitle) {
		this.selectorTitle = selectorTitle;
	}

	public String getSelectorContent() {
		return selectorContent;
	}

	public void setSelectorContent(String selectorContent) {
		this.selectorContent = selectorContent;
	}
	
	public String getSelectorDescription() {
		return selectorDescription;
	}

	public void setSelectorDescription(String selectorDescription) {
		this.selectorDescription = selectorDescription;
	}

	public String getSelectorImg() {
		return selectorImg;
	}

	public void setSelectorImg(String selectorImg) {
		this.selectorImg = selectorImg;
	}

	@Override
	public String toString() {
		return "Source{"
				+ "id:'"+this.getId()+"', "
				+ "name:'"+this.getName()+"', "
				+ "url:'"+this.getUrl()+"', "
				+ "selectorContainer:'"+this.getSelectorContainer()+"', "
				+ "selectorTitle:'"+this.getSelectorTitle()+"', "
				+ "selectorContent:'"+this.getSelectorContent()+"', "
				+ "selectorImg:'"+this.getSelectorImg()+"', "
				+ "createAt:'"+this.getCreatedAt()+"', "
				+ "updatedAt:'"+this.getUpdatedAt()+"'}";
	}

}
