package tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    public TelaPrincipal() {
        setTitle("Agenda de Contatos");
        setSize(520, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        getContentPane().setLayout(new BorderLayout());

        montarMenu();
        montarConteudo();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void montarMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuCadastros = new JMenu("Cadastros");

        JMenuItem itemCidades = new JMenuItem("Cidades");
        itemCidades.addActionListener(e -> new TelaCidade());
        menuCadastros.add(itemCidades);

        JMenuItem itemContatoPessoal = new JMenuItem("Contatos Pessoais");
        itemContatoPessoal.addActionListener(e -> new TelaContatoPessoal());
        menuCadastros.add(itemContatoPessoal);

        JMenuItem itemContatoComercial = new JMenuItem("Contatos Comerciais");
        itemContatoComercial.addActionListener(e -> new TelaContatoComercial());
        menuCadastros.add(itemContatoComercial);

        menuCadastros.addSeparator();

        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> System.exit(0));
        menuCadastros.add(itemSair);

        menuBar.add(menuCadastros);
        setJMenuBar(menuBar);
    }

    private void montarConteudo() {
        JLabel titulo = new JLabel("Agenda de Contatos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        getContentPane().add(titulo, BorderLayout.CENTER);

        JLabel subtitulo = new JLabel(
            "Use o menu \"Cadastros\" para gerenciar cidades e contatos.", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 13));
        getContentPane().add(subtitulo, BorderLayout.SOUTH);
    }
}
