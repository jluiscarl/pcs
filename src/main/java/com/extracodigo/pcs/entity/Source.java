package com.extracodigo.pcs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="sources")
public class Source extends AuditModel  {
	
	private static final long serialVersionUID = 2070364557464491455L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String url;
	
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
	
	@Override
	public String toString() {
		return "Source{"
				+ "id:'"+this.getId()+"', "
				+ "name:'"+this.getName()+"', "
				+ "url:'"+this.getUrl()+"', "
				+ "createAt:'"+this.getCreatedAt()+"', "
				+ "updatedAt:'"+this.getUpdatedAt()+"'}";
	}

}
