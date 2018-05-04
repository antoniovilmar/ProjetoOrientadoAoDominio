package br.com.dojo.domain;

public interface ISeguradoRepository {

  Segurado buscar(Integer id);
  void salvar(Segurado apolice);

}
