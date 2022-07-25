package br.com.alura.linguagensapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LinguagemController {

    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping(value = "/hello")
    public String getHello(){
        return "Olá Mundo Spring! Eureca!!";
    }

    @GetMapping("/ranking-linguagens")
    public List<Linguagem> getRanking(){
        return repositorio.findAll();
    }

    @PostMapping("/ranking-linguagens")
    public Linguagem cadastraLinguagem(@RequestBody Linguagem l){
        return repositorio.save(l);
    }

    @GetMapping("/ranking-linguagens/{id}")
    public Linguagem getLinguagemById(@PathVariable String id){
        Optional<Linguagem> linguagem = repositorio.findById(id);
        return linguagem.get();
    }

    @PutMapping("/ranking-linguagens/atualiza/{id}")
    public Linguagem atualizarLinguagem(@RequestBody Linguagem l, @PathVariable String id){
        Optional<Linguagem> linguagem = repositorio.findById(id);
        l.setId(linguagem.get().getId());
        return repositorio.save(l);
    }

    @DeleteMapping("/ranking-linguagens/delete/{id}")
    public String deletarLinguagem(@PathVariable String id){
        if(repositorio.existsById(id)){
            repositorio.deleteById(id);
            return "Arquivo deletado com sucesso!";
        }else{
            return "Não existe linguagem cadastrada com este Id";
        }
    }
}
