package org.docto.web;

import java.util.List;

import org.docto.dao.DocteurRepository;
import org.docto.entites.Docteur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;




@RestController
@RequestMapping("/docteurs")
public class DocteurRestService {
	@Autowired
	private DocteurRepository docteurRepository;
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public List <Docteur> getDocteurs(){
		return docteurRepository.findAll();
	}
	
	@RequestMapping(value="/doct/{id}",method=RequestMethod.DELETE)
	public boolean supprimer(@PathVariable Long iduser){
		docteurRepository.delete(iduser);
		return true;
	}
	@RequestMapping(value="/doct/{id}",method=RequestMethod.PUT)
	public Docteur save(@PathVariable Long iduser,@RequestBody Docteur d){
		d.setIduser(iduser);
		return docteurRepository.save(d);
	}
	

	@RequestMapping(value="/doct",method=RequestMethod.POST)
	public Docteur save(@RequestBody Docteur d){
		return docteurRepository.save(d);
	}
		  
	@Transactional(readOnly = true)
	@CrossOrigin	
	@RequestMapping(value="/login/{login}/{mdp}",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> findAllByStream(@PathVariable("login")String login,@PathVariable("mdp")String mdp) {
		
		return new ResponseEntity<Object>(docteurRepository.getUserByLoginAndPass(login,mdp), HttpStatus.OK);
	}
	
	@RequestMapping(value="/chercher",method=RequestMethod.GET)
	public Page <Docteur> chercher(
			@RequestParam(name="mc",defaultValue="")String mc,	
			@RequestParam(name="ms",defaultValue="")String ms,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		return docteurRepository.chercher("%"+mc+"%",ms+"%", new PageRequest(page,size));
				  
	}
	
	
}
