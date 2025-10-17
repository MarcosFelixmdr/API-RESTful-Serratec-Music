package org.serratec.serratecmusic.repository;

import org.serratec.serratecmusic.domain.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

}