package com.nabil.musicdb.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "artist", catalog = "musicdb", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Artist implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Date created;
	private Date lastModified;
	private Set<Album> albums = new HashSet<Album>(0);
	private Set<Song> songs = new HashSet<Song>(0);

	public Artist() {
	}

	public Artist(Integer id) {
		this.id = id;
	}

	public Artist(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", length = 19)
	public Date getcreated() {
		return this.created;
	}

	public void setcreated(Date created) {
		this.created = created;
	}

	@Column(name = "name", unique = true, nullable = false, length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified", length = 19)
	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "artist", cascade = CascadeType.REMOVE)
	public Set<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "artist", cascade = CascadeType.REMOVE)
	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	@PrePersist
	public void setCreatedModifiedDate() {
		this.created = new Date();
		this.lastModified = new Date();
	}

}
