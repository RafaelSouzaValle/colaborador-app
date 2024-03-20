package br.com.digivalle.colaboradorappbackend.service;

import br.com.digivalle.colaboradorappbackend.domain.Colaborador;
import br.com.digivalle.colaboradorappbackend.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private PasswordEncryptorService passwordEncryptorService;

    public ColaboradorService(
            ColaboradorRepository colaboradorRepositoryMock, PasswordEncryptorService passwordEncryptorServiceMock) {
    }

    public Colaborador criarColaborador(Colaborador colaborador) {
        String senhaCriptografada = passwordEncryptorService.encryptPassword(colaborador.getSenha());
        String[] strength = PasswordStrengthService.evaluatePasswordStrength(colaborador.getSenha());
        colaborador.setSenhaScore(Integer.parseInt(strength[0]));
        colaborador.setComplexidade(new String(strength[1]).split(" - ")[0]);
        colaborador.setSenha(senhaCriptografada);
        return colaboradorRepository.save(colaborador);
    }

    public Colaborador atualizarColaborador(Long id, Colaborador colaborador) {
        Colaborador colaboradorExistente = colaboradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado com o ID: " + id));

        colaboradorExistente.setNome(colaborador.getNome());
        colaboradorExistente.setCargo(colaborador.getCargo());

        String senhaCriptografada = passwordEncryptorService.encryptPassword(colaborador.getSenha());
        colaboradorExistente.setSenha(senhaCriptografada);

        // Avaliar a força da nova senha
        String[] strength = PasswordStrengthService.evaluatePasswordStrength(colaborador.getSenha());
        colaboradorExistente.setSenhaScore(Integer.parseInt(strength[0]));
        colaboradorExistente.setComplexidade(strength[1].split(" - ")[0]);

        // Salvar e retornar o colaborador atualizado
        return colaboradorRepository.save(colaboradorExistente);
    }

    public void deletarColaborador(Long id) {
        colaboradorRepository.findById(id).ifPresentOrElse(
                colaboradorRepository::delete,
                () -> { throw new RuntimeException("Colaborador não encontrado com o ID: " + id); }
        );
    }

    public Colaborador buscarColaboradorPorId(Long id) {
        return colaboradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado com o ID: " + id));
    }

    public List<Colaborador> buscarTodosColaboradores() {
        return colaboradorRepository.findAllColaboradores();
    }
}
