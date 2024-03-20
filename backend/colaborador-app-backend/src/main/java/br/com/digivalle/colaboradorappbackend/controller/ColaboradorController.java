package br.com.digivalle.colaboradorappbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.digivalle.colaboradorappbackend.domain.Colaborador;
import br.com.digivalle.colaboradorappbackend.service.ColaboradorService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @PostMapping
    public ResponseEntity<Colaborador> criarColaborador(@RequestBody Colaborador colaborador) {
        Colaborador novoColaborador = colaboradorService.criarColaborador(colaborador);
        return new ResponseEntity<>(novoColaborador, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> atualizarColaborador(@PathVariable Long id, @RequestBody Colaborador colaborador) {
        Colaborador colaboradorAtualizado = colaboradorService.atualizarColaborador(id, colaborador);
        return ResponseEntity.ok(colaboradorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarColaborador(@PathVariable Long id) {
        colaboradorService.deletarColaborador(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> buscarColaboradorPorId(@PathVariable Long id) {
        Colaborador colaborador = colaboradorService.buscarColaboradorPorId(id);
        return ResponseEntity.ok(colaborador);
    }

    @GetMapping
    public ResponseEntity<List<Colaborador>> buscarTodosColaboradores() {
        List<Colaborador> colaboradores = colaboradorService.buscarTodosColaboradores();
        return ResponseEntity.ok(colaboradores);
    }
}
