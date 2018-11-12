package com.nabil.musicdb.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nabil.musicdb.model.Album;
import com.nabil.musicdb.model.Artist;
import com.nabil.musicdb.model.Song;
import com.nabil.musicdb.repository.AlbumRepository;
import com.nabil.musicdb.repository.ArtistRepository;
import com.nabil.musicdb.repository.SongRepository;
import com.nabil.musicdb.service.MusicDBService;

@RestController
@RequestMapping("/musicdb")
@ControllerAdvice
public class MusicDBController {

	@Autowired
	SongRepository songRepo;

	@Autowired
	ArtistRepository artistRepo;

	@Autowired
	AlbumRepository albumRepo;

	@Autowired
	MusicDBService musicService;

	// Create Operations

	@PostMapping("/artist/new")
	public Artist createAtrist(@RequestBody Artist artist) {
		return musicService.addArtist(artist);
	}

	@PostMapping("artist/{artist_id}/album/new")
	public Album createAlbum(@Valid @RequestBody Album album, @PathVariable final Integer artist_id) {
		return musicService.addAlbum(artist_id, album);
	}

	@PostMapping("/artist/{artist_id}/album/{album_id}/song/new")
	public Song createSong(@PathVariable final Integer artist_id, @PathVariable final Integer album_id,
			@Valid @RequestBody Song song) {
		return musicService.addSong(artist_id, album_id, song);
	}

	// Retrieve Operations

	@GetMapping(value = { "", "artists" })
	public Iterable<Artist> getAllArtists() {
		Iterable<Artist> ret = musicService.getAllArtists();
		return ret;
	}

	@GetMapping("artist/{id}")
	public ResponseEntity<Artist> getArtistById(@PathVariable("id") final Integer id) {
		Optional<Artist> artist = musicService.getArtistById(id);
		return artist.isPresent() ? new ResponseEntity<Artist>(artist.get(), HttpStatus.OK)
				: new ResponseEntity<Artist>(new Artist(), HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = { "albums" })
	public Iterable<Album> getAllAlbums() {
		return musicService.getAllAlbums();
	}

	@GetMapping("album/{id}")
	public ResponseEntity<Album> getAlbumById(@PathVariable("id") final Integer id) {
		Optional<Album> album = musicService.getAlbumById(id);
		return album.isPresent() ? new ResponseEntity<Album>(album.get(), HttpStatus.OK)
				: new ResponseEntity<Album>(new Album(), HttpStatus.NOT_FOUND);
	}

	@GetMapping("songs")
	public Iterable<Song> getAllSongs() {
		return musicService.getAllSongs();
	}

	@GetMapping(value = { "song/{id}" })
	public ResponseEntity<Song> getSongsById(@PathVariable("id") final Integer id) {
		Optional<Song> song = songRepo.findById(id);
		return song.isPresent() ? new ResponseEntity<Song>(song.get(), HttpStatus.OK)
				: new ResponseEntity<Song>(new Song(), HttpStatus.NOT_FOUND);
	}

	// Update Operations

	@PutMapping("/artist/{id}")
	public Artist updateArtist(@PathVariable("id") final Integer id, @RequestBody Artist artist) {
		return musicService.updateArtist(id, artist);
	}

	@PutMapping("/album/{id}")
	public Album updateAlbum(@PathVariable("id") final Integer id, @RequestBody Album album) {
		return musicService.updateAlbum(id, album);
	}

	@PutMapping("/song/{id}")
	public Song updateSong(@PathVariable("id") final Integer id, @RequestBody Song song) {
		return musicService.updateSong(id, song);
	}

	// Delete Operations

	@DeleteMapping("/artist/{id}")
	public ResponseEntity<String> deleteArtist(@PathVariable("id") final Integer id) {
		try {
			musicService.deleteArtist(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<String>("record id: " + id + " is not found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("Atrist deleted successfully.", HttpStatus.OK);
	}

	@DeleteMapping("/album/{id}")
	public ResponseEntity<String> deleteAlbum(@PathVariable("id") final Integer id) {
		try {
			musicService.deleteAlbum(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<String>("record id: " + id + " is not found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("Album deleted successfully.", HttpStatus.OK);
	}

	@DeleteMapping("/song/{id}")
	public ResponseEntity<String> deleteSong(@PathVariable("id") final Integer id) {
		try {
			musicService.deleteSong(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<String>("record id: " + id + " is not found", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("Song deleted successfully.", HttpStatus.OK);
	}
	
	@RequestMapping(value="/", method=RequestMethod.OPTIONS, produces=MediaType.TEXT_PLAIN_VALUE)
	public String getInfo() {
		
		return 	"Restful API:\r\n" 
				+"\r\n" + 
				"Create Endpoints\r\n" + 
				"\r\n" + 
				"POST /musicdb/artist/new\r\n" + 
				"\r\n" + 
				"POST /musicdb/artist/{artist_id}/album/new\r\n" + 
				"\r\n" + 
				"POST /musicdb/artist/{artist_id}/album/{album_id}/song/new\r\n" + 
				"\r\n" + 
				"Get Endpoints\r\n" + 
				"\r\n" + 
				"GET /musicdb/artists\r\n" + 
				"\r\n" + 
				"GET /musicdb/albums\r\n" + 
				"\r\n" + 
				"GET /musicdb/songs\r\n" + 
				"\r\n" + 
				"Update Endpoints\r\n" + 
				"\r\n" + 
				"PUT /musicdb/artist/{id}\r\n" + 
				"\r\n" + 
				"PUT /musicdb/album/{id}\r\n" + 
				"\r\n" + 
				"PUT /musicdb/song/{id}\r\n" + 
				"\r\n" + 
				"Delete Endpoints\r\n" + 
				"\r\n" + 
				"DELETE /musicdb/song/{Id}\r\n" + 
				"\r\n" + 
				"DELETE /musicdb/album/{id}\r\n" + 
				"\r\n" + 
				"DELETE /musicdb/artist/{Id}";
	}

}
