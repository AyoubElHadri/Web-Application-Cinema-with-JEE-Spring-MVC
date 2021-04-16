package org.sid.cinema.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data @NoArgsConstructor @AllArgsConstructor 
public class Salle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -519680639010145043L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 private int nombrePlace;
 @ManyToOne
 @JsonIgnore
 private Cinema cinema;
 @OneToMany(mappedBy = "salle")
 @JsonIgnore
 private Collection<Place> places;
 @OneToMany(mappedBy = "salle")
 @JsonIgnore
 private Collection<Projection> projections;
	
}
