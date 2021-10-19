package br.unicesumar.aula.controle;

import br.unicesumar.aula.exceptions.DadoConsultadoException;
import br.unicesumar.aula.modelo.Projeto;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjetoImpl implements ProjetoDAO {

    //Collection que irá armazenar todos os projetos
       private static Set<Projeto> projetos = new HashSet<>();

    @Override
    public void adicionar(Projeto projeto) {

        projetos.add(projeto);
    } 
    @Override
    public List<Projeto> listar() {
        List<Projeto> projetoList = new ArrayList<>();
        projetoList.addAll(projetos);
        return projetoList;
    } 
    @Override
    public Projeto consultarPorNome(String nome) throws DadoConsultadoException {
        for(Projeto pj:projetos){
            if(pj.getNome().equalsIgnoreCase(nome)){
                return pj;
            }
        }
        throw new DadoConsultadoException("ERRO! - Desculpe! Não foi encontrado um projeto com este nome: "+nome);
    } 
    @Override
    public Projeto alterar(String nome, Projeto projeto) throws DadoConsultadoException {
        for(Projeto pj:projetos)
            if (pj.getNome().equalsIgnoreCase(nome)) {
                Projeto encontrarProjeto = consultarPorNome(nome);
                encontrarProjeto.setNome(projeto.getNome());
                encontrarProjeto.setObjetivo(projeto.getObjetivo());
                encontrarProjeto.setNecessidades(projeto.getNecessidades());
                encontrarProjeto.setDataInicio(projeto.getDataInicio());
                encontrarProjeto.setDataFinal(projeto.getDataFinal());
                encontrarProjeto.setStatus(projeto.getStatus());
                return encontrarProjeto;
            }
        throw new DadoConsultadoException("ERRO! - Desculpe! Não foi encontrado um projeto com este nome: "+nome);
    }
    @Override
    public void excluir(Projeto projeto) throws DadoConsultadoException, UnsupportedOperationException {

        for(Projeto pj:projetos){
            if(pj.getNome().equalsIgnoreCase(projeto.getNome())){
                projetos.remove(projeto);
                return;
            }
        }

        throw new DadoConsultadoException("Desculpe! Não foi possível excluir o projeto, pois não foi encontrado o projeto para exclusão ");
    } 
    @Override
    public void excluir(String nome) throws DadoConsultadoException, UnsupportedOperationException {
        Projeto projeto=consultarPorNome(nome);
        excluir(projeto);
    }
}