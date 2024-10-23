package com.koreait.exam.springbatch_10_app.app.song.repository;

import com.koreait.exam.springbatch_10_app.app.song.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
