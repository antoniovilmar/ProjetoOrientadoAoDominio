package br.com.dojo.domain;

public interface IFaturaRepository {

  Fatura buscar(Long numeroApolice);

  Fatura buscar(Integer id);

  void salvar(Fatura fatura);

}
