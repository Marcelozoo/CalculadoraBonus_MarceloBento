package Presenter;

import Model.DadosPeso;
import Model.Resultado;
import View.ResultadoCalculosView;
import Services.CalculadoraEstatisticaService;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;


public class ResultadosCalculosPresenter {

    final private ResultadoCalculosView resultadosCalculos;
    final private CalculadoraEstatisticaService calculadoraService = new CalculadoraEstatisticaService();
    private ArrayList<Resultado> resultadosNovos;
    private DadosPeso peso;
    private ArrayList<Double> resultadosTotais;
    private JComboBox comboBox;

    public ResultadosCalculosPresenter() {

        this.resultadosCalculos = new ResultadoCalculosView();
        this.resultadosTotais = new ArrayList();
        this.comboBox = this.resultadosCalculos.getjComboBox();
        
        fechar();

    }
    
    public void realizarCalculos(ArrayList<Integer> dados) {
        ArrayList<Double> dadosNovos = new ArrayList();
      
        
        for(int i =0;i<dados.size();i++){
            dadosNovos.add((double) dados.get(i));
        }
        this.peso = new DadosPeso(dadosNovos);
        this.calculadoraService.calcular(this.peso);
        
        resultadosNovos = this.peso.getResultados();
        
        this.comboBox.addItem(peso.getResultados().get(0).getData());
        
        armazenaResultados(peso);
        visualizarCalculos(dadosNovos);
              
    }

    public void visualizarCalculos(ArrayList<Double> resultados) {

        this.resultadosCalculos.setVisible(true);
        this.resultadosCalculos.setLocationRelativeTo(this.resultadosCalculos.getParent());
        this.resultadosCalculos.setLocationRelativeTo(this.resultadosCalculos.getParent());
        
        DefaultTableModel model = (DefaultTableModel) this.resultadosCalculos.getjTable1().getModel();
        model.setRowCount(0);
        
        int i;

        for (i = 0; i < this.resultadosNovos.size(); i++) {
            model.addRow(new Object[]{resultadosNovos.get(i).getNome(), resultadosNovos.get(i).getValor() });
        }
        
    }

    private void armazenaResultados(DadosPeso peso) {
        ArrayList<Resultado> resultado = peso.getResultados();

        for (int i = 0; i < resultado.size(); i++) {
            this.resultadosTotais.add(resultado.get(i).getValor());
        }

    }
    
    private void fechar(){
        
        this.resultadosCalculos.getJButton1().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resultadosCalculos.dispose();

                
            }
        });
    }
   
}