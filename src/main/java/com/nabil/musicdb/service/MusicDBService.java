package com.nabil.musicdb.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nabil.musicdb.model.Album;
import com.nabil.musicdb.model.Artist;
import com.nabil.musicdb.model.Song;
import com.nabil.musicdb.repository.AlbumRepository;
import com.nabil.musicdb.repository.ArtistRepository;
import com.nabil.musicdb.repository.SongRepository;

@Service
public class MusicDBService {

	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private SongRepository songRepository;

	@Transactional
	public Artist addArtist(Artist artist) {
		return artistRepository.save(artist);
	}

	@Transactional
	public Album addAlbum(Integer srtist_id, Album album) {
		Artist singer = artistRepository.getOne(srtist_id);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		return albumRepository.save(new Album(singer, album.getName(), album.getYearReleased(), ts));
	}

	@Transactional
	public Song addSong(Integer srtist_id, Integer album_id, Song song) {
		Artist singer = artistRepository.getOne(srtist_id);
		Album record = albumRepository.getOne(album_id);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		song.setArtist(singer);
		song.setAlbum(record);
		song.setLastModified(ts);
		return songRepository.save(song);
	}

	// get operations

	@Transactional(readOnly=true)
	public Iterable<Artist> getAllArtists() {
		Iterable<Artist> ret = artistRepository.findAll();
		return ret;
	}

	@Transactional(readOnly=true)
	public Optional<Artist> getArtistById(Integer id) {
		return artistRepository.findById(id);
	}

	@Transactional(readOnly=true)
	public Iterable<Album> getAllAlbums() {
		return albumRepository.findAll();
	}

	@Transactional(readOnly=true)
	public Optional<Album> getAlbumById(Integer id) {
		return albumRepository.findById(id);
	}

	@Transactional(readOnly=true)
	public Iterable<Song> getAllSongs() {
		return songRepository.findAll();
	}

	@Transactional(readOnly=true)
	public Optional<Song> getSongById(Integer id) {
		return songRepository.findById(id);
	}

	// update operations

	@Transactional
	public Artist updateArtist(final Integer id, Artist artist) {
		return artistRepository.save(artist);
	}

	@Transactional
	public Album updateAlbum(final Integer id, Album album) {
		Optional<Album> existing = albumRepository.findById(id);
		album.setId(id);
		if(album.getYearReleased()==null)
			album.setYearReleased(existing.get().getYearReleased());
		else
			album.setYearReleased(album.getYearReleased());
		album.setArtist(existing.get().getArtist());
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		album.setLastModified(ts);
		return albumRepository.save(album);
	}

	@Transactional
	public Song updateSong(final Integer id, Song song) {
		Optional<Song> existing = songRepository.findById(id);
		Artist singer = existing.get().getArtist();
		Album record = existing.get().getAlbum();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		song.setId(id);
		if(song.getTrack() == null)
			song.setTrack(existing.get().getTrack());
		else
			song.setTrack(song.getTrack());
		song.setArtist(singer);
		song.setAlbum(record);
		song.setCreated(existing.get().getCreated());
		song.setLastModified(ts);
		return songRepository.save(song);
	}

	@Transactional
	public void deleteArtist(final Integer id) {
		artistRepository.deleteById(id);
	}

	@Transactional
	public void deleteAlbum(final Integer id) {
		albumRepository.deleteById(id);
	}

	@Transactional
	public void deleteSong(final Integer id) {
		songRepository.deleteById(id);
	}
}
