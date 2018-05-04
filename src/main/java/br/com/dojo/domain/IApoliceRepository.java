package br.com.dojo.domain;

public interface IApoliceRepository {

  Apolice buscar(Long numeroApolice);
  Apolice buscar(Integer id);
  void salvar(Apolice apolice);

}
