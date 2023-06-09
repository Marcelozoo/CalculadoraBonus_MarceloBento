package services;


import models.DadosPeso;
import models.interfaces.IOperacao;
import java.util.ArrayList;
import services.operacoes.*;

public class CalculadoraEstatisticaService {
        
    final private ArrayList<IOperacao> operacoes;
        
    public CalculadoraEstatisticaService(){
        operacoes = new ArrayList<>();
        operacoes.add(new MaiorOperacao());
        operacoes.add(new MediaOperacao());
        operacoes.add(new SomatorioOperacao());
        operacoes.add(new MenorOperacao());
        operacoes.add(new VarianciaOperacao());
        operacoes.add(new DesvioPadraoOperacao());
        operacoes.add(new QuantidadeOperacao());
           
    }
        
    public void calcular(DadosPeso peso){
        for (IOperacao operacao : operacoes){
            operacao.calcular(peso);
        }

    }

}