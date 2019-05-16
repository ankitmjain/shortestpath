package com.dhs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "planet", schema = "sorttestpath")
public @Data class Planet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long planetID;
	@Column(unique=true)
	private String planetNode;
	private String planetName;
}
