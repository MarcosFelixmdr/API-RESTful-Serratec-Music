package org.serratec.serratecmusic.repository;

import org.serratec.serratecmusic.domain.Playlists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistsRepository extends JpaRepository<Playlists, Long> {

}