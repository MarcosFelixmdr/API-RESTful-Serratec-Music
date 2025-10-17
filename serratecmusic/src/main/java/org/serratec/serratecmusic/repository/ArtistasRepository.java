package org.serratec.serratecmusic.repository;

import org.serratec.serratecmusic.domain.Artistas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistasRepository extends JpaRepository<Artistas, Long> {

}