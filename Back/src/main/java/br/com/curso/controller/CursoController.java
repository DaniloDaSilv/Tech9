package br.com.curso.controller;

import java.time.LocalDate;
import java.util.List;

import br.com.curso.entities.Curso;
import br.com.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Request.CursoPost;

@RestController
@RequestMapping("/curso")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursoController {

	@Autowired
	private CursoService service;

	@GetMapping
	public ResponseEntity<List<Curso>> GetAll(@RequestParam(required = false) String descricao,
                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
		List<Curso> curso = service.GetAll(descricao, dataInicio, dataFim);
		return ResponseEntity.ok().body(curso);
	}

	@GetMapping("/{idCurso}")
	public ResponseEntity<Curso> GetById(@PathVariable long idCurso) {
		return service.GetById(idCurso);
	}

	@PostMapping
	public ResponseEntity<?> post(@RequestBody CursoPost curso) {

		try {
			service.cadastro(curso);
			return ResponseEntity.status(HttpStatus.OK).body("Curso cadastrado");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Atenção: " + e.getMessage());
		}

	}

	@PutMapping
	public ResponseEntity<?> put(@RequestBody Curso curso) {
		try {
			service.atualizar(curso);
			return ResponseEntity.status(HttpStatus.OK).body("Curso atualizado");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Atenção: " + e.getMessage());
		}

	}

	@DeleteMapping("/{idCurso}")
	public ResponseEntity<String> delete(@PathVariable long idCurso) {
		return service.deletar(idCurso);

	}

}
