package org.sid.cinema.dao;


import org.sid.cinema.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
@RepositoryRestController
public interface VilleRepository extends JpaRepository<Ville, Long> {

}
