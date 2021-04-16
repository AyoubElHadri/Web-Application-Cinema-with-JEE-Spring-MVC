package org.sid.cinema.dao;


import org.sid.cinema.entities.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface SeanceRepository extends JpaRepository<Seance, Long> {

}
