package tela;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dominio.Cidade;
import servico.ServicoCidade;

public class TelaCidade extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JTextField campoNome;

    public TelaCidade() {
        setTitle("Cidades");
        setSize(500, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 245));

        inicializarComponentes();
        carregarTabela();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void inicializarComponentes() {
        JLabel titulo = new JLabel("Cidades");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBounds(20, 10, 300, 30);
        getContentPane().add(titulo);

        String[] colunas = {"ID", "Nome"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabela = new JTable(modeloTabela);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) preencherFormulario();
        });
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 50, 440, 220);
        getContentPane().add(scroll);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 285, 60, 20);
        getContentPane().add(lblNome);
        campoNome = new JTextField();
        campoNome.setBounds(80, 285, 280, 25);
        getContentPane().add(campoNome);

        JButton btnCriar = new JButton("Criar");
        btnCriar.setBounds(20, 325, 100, 30);
        btnCriar.setBackground(new Color(70, 130, 180));
        btnCriar.setForeground(Color.WHITE);
        btnCriar.addActionListener(e -> criarCidade());
        getContentPane().add(btnCriar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(130, 325, 100, 30);
        btnAlterar.setBackground(new Color(60, 160, 60));
        btnAlterar.setForeground(Color.WHITE);
        btnAlterar.addActionListener(e -> alterarCidade());
        getContentPane().add(btnAlterar);

        JButton btnApagar = new JButton("Apagar");
        btnApagar.setBounds(240, 325, 100, 30);
        btnApagar.setBackground(new Color(200, 60, 60));
        btnApagar.setForeground(Color.WHITE);
        btnApagar.addActionListener(e -> apagarCidade());
        getContentPane().add(btnApagar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(350, 325, 100, 30);
        btnLimpar.addActionListener(e -> limparFormulario());
        getContentPane().add(btnLimpar);
    }

    private void carregarTabela() {
        modeloTabela.setRowCount(0);
        List<Cidade> cidades = ServicoCidade.listarCidades();
        for (Cidade c : cidades) {
            modeloTabela.addRow(new Object[]{ c.getId(), c.getNome() });
        }
    }

    private void preencherFormulario() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) return;
        campoNome.setText(String.valueOf(modeloTabela.getValueAt(linha, 1)));
    }

    private void limparFormulario() {
        campoNome.setText("");
        tabela.clearSelection();
    }

    private void criarCidade() {
        try {
            ServicoCidade.criarCidade(campoNome.getText().trim());
            JOptionPane.showMessageDialog(this, "Cidade criada com sucesso!");
            limparFormulario();
            carregarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarCidade() {
        try {
            int linha = tabela.getSelectedRow();
            if (linha < 0) {
                JOptionPane.showMessageDialog(this, "Selecione uma cidade na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = (int) modeloTabela.getValueAt(linha, 0);
            ServicoCidade.atualizarCidade(id, campoNome.getText().trim());
            JOptionPane.showMessageDialog(this, "Cidade atualizada com sucesso!");
            limparFormulario();
            carregarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void apagarCidade() {
        try {
            int linha = tabela.getSelectedRow();
            if (linha < 0) {
                JOptionPane.showMessageDialog(this, "Selecione uma cidade na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = (int) modeloTabela.getValueAt(linha, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Confirma a exclusão?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ServicoCidade.apagarCidade(id);
                JOptionPane.showMessageDialog(this, "Cidade apagada com sucesso!");
                limparFormulario();
                carregarTabela();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
