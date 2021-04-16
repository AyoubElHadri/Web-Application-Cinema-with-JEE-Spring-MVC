package org.sid.cinema.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
public class CinemaRestController {
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@GetMapping(path = "/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable (name = "id")Long id) throws Exception{
	Film f=filmRepository.findById(id).get();
	String photoName=f.getPhoto();
	File file = new File (System.getProperty("user.home")+"/cinema/images/"+photoName);
	Path path = Paths.get(file.toURI());
	return Files.readAllBytes(path);
	}
	@PostMapping(value = "/payerTickets",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ticket> payerTickets(@RequestBody @Valid TicketForm ticketForm,Errors errors) {
		if(errors.hasErrors()) {
			return new ArrayList<>();
		}//,"tickets":[1,2,3,4]
		List<Ticket> listTickets=new ArrayList<>();
		ticketForm.getTickets().forEach(idTicket->{
			System.out.println(idTicket);
			Optional<Ticket> ticket= ticketRepository.findById(Long.parseLong(idTicket+""));
			if(ticket.isPresent()) {
				Ticket storedTeckit = ticket.get();
				storedTeckit.setNomClient(ticketForm.getNomClient());
				storedTeckit.setReserve(true);
				storedTeckit.setCodePayement(ticketForm.getCodePayement());
			ticketRepository.save(storedTeckit);
			listTickets.add(storedTeckit);
			}
		});
		return listTickets;
	}

}


@Data @AllArgsConstructor @NoArgsConstructor
class TicketForm{
	@NotNull
	private String nomClient;
	@NotNull
	private int codePayement;
	@NotNull
	private List<Integer> tickets;
}