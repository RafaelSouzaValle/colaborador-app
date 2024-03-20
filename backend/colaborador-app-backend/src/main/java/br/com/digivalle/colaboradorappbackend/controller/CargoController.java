package br.com.digivalle.colaboradorappbackend.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import br.com.digivalle.colaboradorappbackend.domain.ColaboradorCargo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cargos")
public class CargoController {

    @GetMapping
    public List<String> listarCargos() {
        return Arrays.asList(
                ColaboradorCargo.DIRETOR.name(),
                ColaboradorCargo.GERENTE.name(),
                ColaboradorCargo.COORDENADOR.name(),
                ColaboradorCargo.SUPERVISOR.name()
        );
    }
}

