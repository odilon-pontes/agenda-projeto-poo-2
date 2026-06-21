package tela;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dominio.Cidade;
import dominio.ContatoPessoal;
import repositorio.RepositorioCidade;
import servico.ServicoContatoPessoal;

public class TelaContatoPessoal extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private JTextField campoNome;
    private JComboBox<Integer> comboGrauProximidade;
    private JTextField campoTelefone;
    private JComboBox<String> comboCidade;

    private List<Cidade> cidades;

    public TelaContatoPessoal() {
        setTitle("Contatos Pessoais");
        setSize(820, 560);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 245));

        inicializarComponentes();
        carregarCidades();
        carregarTabela();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void inicializarComponentes() {
        JLabel titulo = new JLabel("Contatos Pessoais");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBounds(20, 10, 300, 30);
        getContentPane().add(titulo);

        String[] colunas = {"ID", "Nome", "Grau de Proximidade", "Cidade", "Telefones"};
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
        scroll.setBounds(20, 50, 770, 190);
        getContentPane().add(scroll);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 260, 60, 20);
        getContentPane().add(lblNome);
        campoNome = new JTextField();
        campoNome.setBounds(80, 260, 200, 25);
        getContentPane().add(campoNome);

        JLabel lblGrau = new JLabel("Grau de Proximidade:");
        lblGrau.setBounds(300, 260, 150, 20);
        getContentPane().add(lblGrau);
        comboGrauProximidade = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        comboGrauProximidade.setBounds(455, 260, 60, 25);
        getContentPane().add(comboGrauProximidade);

        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setBounds(20, 300, 60, 20);
        getContentPane().add(lblCidade);
        comboCidade = new JComboBox<>();
        comboCidade.setBounds(80, 300, 200, 25);
        getContentPane().add(comboCidade);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(300, 300, 70, 20);
        getContentPane().add(lblTelefone);
        campoTelefone = new JTextField();
        campoTelefone.setBounds(375, 300, 160, 25);
        getContentPane().add(campoTelefone);

        JButton btnAddTel = new JButton("+ Tel.");
        btnAddTel.setBounds(545, 300, 80, 25);
        btnAddTel.addActionListener(e -> adicionarTelefone());
        getContentPane().add(btnAddTel);

        JButton btnCriar = new JButton("Criar");
        btnCriar.setBounds(20, 350, 110, 30);
        btnCriar.setBackground(new Color(70, 130, 180));
        btnCriar.setForeground(Color.WHITE);
        btnCriar.addActionListener(e -> criarContato());
        getContentPane().add(btnCriar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(145, 350, 110, 30);
        btnAlterar.setBackground(new Color(60, 160, 60));
        btnAlterar.setForeground(Color.WHITE);
        btnAlterar.addActionListener(e -> alterarContato());
        getContentPane().add(btnAlterar);

        JButton btnApagar = new JButton("Apagar");
        btnApagar.setBounds(270, 350, 110, 30);
        btnApagar.setBackground(new Color(200, 60, 60));
        btnApagar.setForeground(Color.WHITE);
        btnApagar.addActionListener(e -> apagarContato());
        getContentPane().add(btnApagar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(395, 350, 110, 30);
        btnLimpar.addActionListener(e -> limparFormulario());
        getContentPane().add(btnLimpar);
    }

    private void carregarCidades() {
        comboCidade.removeAllItems();
        cidades = new RepositorioCidade().listarTodas();
        for (Cidade c : cidades) comboCidade.addItem(c.getNome());
    }

    private void carregarTabela() {
        modeloTabela.setRowCount(0);
        for (ContatoPessoal c : ServicoContatoPessoal.listarContatosPessoais()) {
            modeloTabela.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getGrauProximidade(),
                c.getCidade() != null ? c.getCidade().getNome() : "",
                String.join(", ", c.getTelefones())
            });
        }
    }

    private void preencherFormulario() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) return;
        campoNome.setText(String.valueOf(modeloTabela.getValueAt(linha, 1)));
        comboGrauProximidade.setSelectedItem(modeloTabela.getValueAt(linha, 2));
        String nomeCidade = String.valueOf(modeloTabela.getValueAt(linha, 3));
        for (int i = 0; i < comboCidade.getItemCount(); i++) {
            if (comboCidade.getItemAt(i).equals(nomeCidade)) {
                comboCidade.setSelectedIndex(i);
                break;
            }
        }
    }

    private void limparFormulario() {
        campoNome.setText("");
        comboGrauProximidade.setSelectedIndex(0);
        campoTelefone.setText("");
        tabela.clearSelection();
        if (comboCidade.getItemCount() > 0) comboCidade.setSelectedIndex(0);
    }

    private int getIdCidadeSelecionada() {
        int idx = comboCidade.getSelectedIndex();
        if (idx < 0 || idx >= cidades.size())
            throw new RuntimeException("Selecione uma cidade.");
        return cidades.get(idx).getId();
    }

    private void criarContato() {
        try {
            ServicoContatoPessoal.criarContatoPessoal(
                campoNome.getText().trim(),
                (Integer) comboGrauProximidade.getSelectedItem(),
                getIdCidadeSelecionada()
            );
            JOptionPane.showMessageDialog(this, "Contato criado com sucesso!");
            limparFormulario();
            carregarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarContato() {
        try {
            int linha = tabela.getSelectedRow();
            if (linha < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um contato na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = (int) modeloTabela.getValueAt(linha, 0);
            ServicoContatoPessoal.alterarContatoPessoal(
                id,
                campoNome.getText().trim(),
                (Integer) comboGrauProximidade.getSelectedItem(),
                getIdCidadeSelecionada()
            );
            JOptionPane.showMessageDialog(this, "Contato alterado com sucesso!");
            limparFormulario();
            carregarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void apagarContato() {
        try {
            int linha = tabela.getSelectedRow();
            if (linha < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um contato na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = (int) modeloTabela.getValueAt(linha, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Confirma a exclusão?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ServicoContatoPessoal.apagarContato(id);
                JOptionPane.showMessageDialog(this, "Contato apagado com sucesso!");
                limparFormulario();
                carregarTabela();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarTelefone() {
        try {
            int linha = tabela.getSelectedRow();
            if (linha < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um contato na tabela primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = (int) modeloTabela.getValueAt(linha, 0);
            ServicoContatoPessoal.adicionarTelefoneContato(campoTelefone.getText().trim(), id);
            JOptionPane.showMessageDialog(this, "Telefone adicionado!");
            campoTelefone.setText("");
            carregarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
