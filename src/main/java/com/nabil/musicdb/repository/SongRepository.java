package com.nabil.musicdb.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nabil.musicdb.model.Song;

@Repository
@Transactional
public interface SongRepository extends JpaRepository<Song, Integer>{

}
