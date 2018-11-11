package com.nabil.musicdb.configuration;

import javax.annotation.PostConstruct;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nabil.musicdb.model.Album;
import com.nabil.musicdb.model.Artist;
import com.nabil.musicdb.model.Song;
import com.nabil.musicdb.repository.AlbumRepository;
import com.nabil.musicdb.repository.ArtistRepository;
import com.nabil.musicdb.repository.SongRepository;

@Configuration
public class AppDatabaseConsole implements CommandLineRunner{
	
	@Autowired
	private ArtistRepository artistRepository;
	
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private SongRepository songRepository;
	
	@Override
	public void run(String... args) throws Exception {
		artistRepository.save(new Artist(1, "Muse"));
		artistRepository.save(new Artist(2, "Duran Duran"));
		artistRepository.save(new Artist(3, "Van Halen"));
		
		albumRepository.save(new Album(new Artist(1), 1, "Drones", 2015));
		albumRepository.save(new Album(new Artist(1), 2, "Origin of Symmetry", 2001));
		albumRepository.save(new Album(new Artist(2), 3, "Rio", 1982));
		albumRepository.save(new Album(new Artist(3), 4, "1984", 1984));
		
		songRepository.save(new Song(new Album(1), new Artist(1), 1, 1, "Dead Inside"));
		songRepository.save(new Song(new Album(1), new Artist(1), 2, 2, "Drill Sargeant"));
		songRepository.save(new Song(new Album(1), new Artist(1), 3, 3, "Psycho"));
		songRepository.save(new Song(new Album(1), new Artist(1), 4, 4, "Mercy"));
		songRepository.save(new Song(new Album(1), new Artist(1), 5, 5, "The Handler"));
		
		songRepository.save(new Song(new Album(2), new Artist(1), 6, 1, "Intro"));
		songRepository.save(new Song(new Album(2), new Artist(1), 7, 2, "Apocolypse Please"));
		songRepository.save(new Song(new Album(2), new Artist(1), 8, 3, "Time is Running Out"));
		songRepository.save(new Song(new Album(2), new Artist(1), 9, 4, "Sing for Absolution"));
		songRepository.save(new Song(new Album(2), new Artist(1), 10, 5, "Stokholm Syndrome"));
		
		songRepository.save(new Song(new Album(3), new Artist(2), 11, 1, "Rio"));
		songRepository.save(new Song(new Album(3), new Artist(2), 12, 2, "My Own Way"));
		songRepository.save(new Song(new Album(3), new Artist(2), 13, 3, "Lonely in Your Nightmare"));
		songRepository.save(new Song(new Album(3), new Artist(2), 14, 4, "Hungry Like the Wolf"));
		songRepository.save(new Song(new Album(3), new Artist(2), 15, 5, "Hold Back the Rain"));
		
		songRepository.save(new Song(new Album(4), new Artist(3), 16, 1, "1984"));
		songRepository.save(new Song(new Album(4), new Artist(3), 17, 2, "Jump" ));
		songRepository.save(new Song(new Album(4), new Artist(3), 18, 3, "Panama"));
		songRepository.save(new Song(new Album(4), new Artist(3), 19, 4, "Top Jimmy"));
		songRepository.save(new Song(new Album(4), new Artist(3), 20, 5, "Drop Dead Legs"));
	}
	
	@PostConstruct
	public void getDbManager(){
				System.out.println("starting hsqldb....");
		String headless = System.getProperty("java.awt.headless");
		System.setProperty("java.awt.headless", "false");
		System.out.println(headless);
		DatabaseManagerSwing.main(
				new String[] { "--url", "jdbc:hsqldb:mem:musicdb", "--user", "sa", "--password", ""});	  
	}

	

}
