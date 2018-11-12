package com.nabil.musicdb.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nabil.musicdb.model.Song;

@Repository
@Transactional
public interface SongRepository extends JpaRepository<Song, Integer>{
	@Modifying
	@Query("delete Song s where s.id = ?1")
	public void deleteSongById(Integer id);
}
