package org.serratec.serratecmusic.repository;

import org.serratec.serratecmusic.domain.Musicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicasRepository extends JpaRepository<Musicas, Long> {

}