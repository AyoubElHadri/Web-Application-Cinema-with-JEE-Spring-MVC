package org.sid.cinema.dao;


import org.sid.cinema.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
@RepositoryRestController
public interface SalleRepository extends JpaRepository<Salle, Long> {

}
