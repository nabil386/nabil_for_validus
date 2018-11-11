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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "album", catalog = "musicdb")
public class Album implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Artist artist;
	private String name;
	private Integer yearReleased;
	private Date lastModified;
	private Set<Song> songs = new HashSet<Song>(0);

	public Album() {
	}

	public Album(Integer id) {
		this.id = id;
	}

	public Album(Artist artist, Integer id, String name, Integer yearReleased) {
		this.artist = artist;
		this.id = id;
		this.name = name;
		this.yearReleased = yearReleased;
	}

	public Album(Artist artist, String name, Integer yearReleased, Date lastModified) {
		this.artist = artist;
		this.name = name;
		this.yearReleased = yearReleased;
		this.lastModified = lastModified;
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

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "artist_id", nullable = false)
	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	@Column(name = "name", nullable = false, length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "yearReleased", nullable = false, length = 0)
	public Integer getYearReleased() {
		return this.yearReleased;
	}

	public void setYearReleased(Integer yearReleased) {
		this.yearReleased = yearReleased;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified", nullable = false, length = 19)
	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	@PrePersist
	public void setModifiedDate() {
		this.lastModified = new Date();
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "album", cascade = CascadeType.REMOVE)
	public Set<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}

}
