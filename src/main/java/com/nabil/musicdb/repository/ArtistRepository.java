package com.nabil.musicdb.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nabil.musicdb.model.Artist;

@Repository
@Transactional
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

}
