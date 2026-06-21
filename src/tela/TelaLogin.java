package tela;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final String USUARIO_VALIDO = "seunome";
    private static final String SENHA_VALIDA   = "suasenha";

    private JTextField campoUsuario;
    private JPasswordField campoSenha;

    public TelaLogin() {
        setTitle("Login — Agenda de Contatos");
        setSize(360, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 245));

        JLabel titulo = new JLabel("Agenda de Contatos");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBounds(80, 15, 220, 25);
        getContentPane().add(titulo);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(40, 60, 70, 20);
        getContentPane().add(lblUsuario);
        campoUsuario = new JTextField();
        campoUsuario.setBounds(120, 60, 180, 25);
        getContentPane().add(campoUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(40, 100, 70, 20);
        getContentPane().add(lblSenha);
        campoSenha = new JPasswordField();
        campoSenha.setBounds(120, 100, 180, 25);
        campoSenha.addActionListener(e -> efetuarLogin());
        getContentPane().add(campoSenha);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(120, 145, 180, 30);
        btnEntrar.setBackground(new Color(70, 130, 180));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.addActionListener(e -> efetuarLogin());
        getContentPane().add(btnEntrar);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void efetuarLogin() {
        String usuario = campoUsuario.getText().trim();
        String senha   = new String(campoSenha.getPassword());
        if (USUARIO_VALIDO.equals(usuario) && SENHA_VALIDA.equals(senha)) {
            dispose();
            new TelaPrincipal();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
            campoSenha.setText("");
        }
    }
}