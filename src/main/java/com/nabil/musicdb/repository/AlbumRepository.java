package com.nabil.musicdb.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nabil.musicdb.model.Album;

@Repository
@Transactional
public interface AlbumRepository extends JpaRepository<Album, Integer> {
	public Optional<Album> findById(Integer id);
	public List<Album> findByName(String name);
}