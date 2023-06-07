
package biblioteca.servicos;

import biblioteca.interfac.CadastrarUsuario;
import biblioteca.interfac.MenuPrincipal;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author herrmann
 */
public class BaseDeDados extends PlainDocument{
    
    public enum TipoEntrada {
        NOME, CPF, TITULO, AUTOR, CODIGO, ANO;
    };
    
    private int qtdCaracteres;
    private TipoEntrada tpEntrada;

    public BaseDeDados(int qtdCaracteres, TipoEntrada tpEntrada) {
        this.qtdCaracteres = qtdCaracteres;
        this.tpEntrada = tpEntrada;
    }

    @Override
    public void insertString(int i, String string, AttributeSet as) throws BadLocationException {
        if (string == null || getLength() == qtdCaracteres){
            return;
        }
        int totalCarac = getLength() + string.length();
        // filtro de caracteres
        String regex = "";
        switch(tpEntrada){
            case CPF: regex = "[^0-9]"; break;
            case NOME: regex = "[^\\p{IsLatin} ]"; break;
            case TITULO: regex = "[^\\p{IsLatin} ]"; break;
            case AUTOR: regex = "[^\\p{IsLatin} ]"; break;
            case CODIGO: regex = "[^0-9]"; break;
            case ANO: regex = "[^0-9]"; break;
        }
        // fazendo a substituição
        string = string.replaceAll(regex, "");
        
        if (totalCarac <= qtdCaracteres){
            super.insertString(i, string, as); //To change body of generated methods, choose Tools | Templates.
        }else{
            String nova = string.substring(0, qtdCaracteres);
            super.insertString(i, nova, as);
        }
    }
}
class FecharJanelaCadastro extends CadastrarUsuario {
    private JButton btnEntrar;

    public FecharJanelaCadastro() {
        setDefaultCloseOperation(CadastrarUsuario.EXIT_ON_CLOSE);

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastrarUsuario Cad = new CadastrarUsuario();
                Cad.dispose(); // Fecha a janela atual
                Cad.setVisible(false);

             
            }
        });

        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(btnEntrar);

        pack();
        setLocationRelativeTo(null); // Centraliza a janela na tela
    }
      public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FecharJanelaCadastro fechar = new FecharJanelaCadastro();
                fechar.setVisible(true);
            }
        });
    }
}



