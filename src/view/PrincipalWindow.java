package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.swing.*;

import model.Usuario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrincipalWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JDesktopPane desktopPane;
    JMenuBar menuBar;
    JMenuItem itemConnectionReplication;
    JMenuItem itemReplicationTable;
    JMenu menuCadastros;
    JMenu menuReplica;

    ReplicationFrm rf;
    JLabel lbUsuarioHora;

    public static void main(String[] args){
        PrincipalWindow pw = new PrincipalWindow();
        pw.setVisible(true);
    }


    public PrincipalWindow() {
        // Define o tamanho da janela.
        setSize(800, 600);
        setMinimumSize(new Dimension(800, 600));
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        // Define o titulo da janela.
        setTitle("Menu Principal");

        // Seta o layout a ser utilizado (NULL significa que nï¿½o irï¿½ utilizar nenhum).
        getContentPane().setLayout(null);

        // Define que nï¿½o poderï¿½ ser alterado as dimensï¿½es da tela.
        setResizable(true);

        // Define o mï¿½todo de fechamento da janela.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.black);

        setLocationRelativeTo(null);

        criarComponentes();

        setContentPane(CreateContentPane());
    }

    /**
     * Método responsavel pela imagem do fundo do Neo Editor Writer.
     * @return
     */
    public
    Container		CreateContentPane()
    {
        //
        // Criação do painel.
        //
        JPanel
                contentPane		= 	new JPanel(new BorderLayout());

        //
        // Carrega a imagem.
        //
        desktopPane		=	new JDesktopPane()
        {
            Image
                    im		= 	new ImageIcon().getImage();

            public
            void		paintComponent(Graphics g)
            {
                g.drawImage(im,0,0,this);
            }
        };
        desktopPane.setBackground(Color.BLACK);

        //
        // Cria o painel OPACO.
        //
        contentPane.setOpaque(true);

        //
        // Adiciona ao JDesktopPane.
        //
        contentPane.add(desktopPane);

        //
        // Retorna o painel.
        //
        return contentPane;
    }

    private void criarComponentes() {

//		lbUsuarioHora = new JLabel("asd", JLabel.RIGHT);
//		lbUsuarioHora.addHierarchyBoundsListener(new HierarchyBoundsListener() {
//
//			@Override
//			public void ancestorResized(HierarchyEvent e) {
//				Dimension d = getSize();
//				lbUsuarioHora.setBounds(((i/nt) d.getWidth()) - 370, ((int) d.getHeight()) - 85, 350, 20);
//			}
//
//			@Override
//			public void ancestorMoved(HierarchyEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//		});

        // Configuraï¿½ï¿½o do DesktopPane
        desktopPane = new JDesktopPane();
        // desktopPane.setSize(800,600);
        desktopPane.addHierarchyBoundsListener(new HierarchyBoundsListener() {

            @Override
            public void ancestorResized(HierarchyEvent arg0) {
                Dimension d = getSize();
                d.setSize(d.getWidth() - 20, d.getHeight() - 60);
                desktopPane.setSize(d);
            }

            @Override
            public void ancestorMoved(HierarchyEvent arg0) {
                // TODO Auto-generated method stub

            }
        });

        // Comando da Barra de Menu
        menuBar = new JMenuBar();

        menuCadastros = new JMenu("Cadastros");
        menuReplica = new JMenu("Replicacao");

        itemConnectionReplication = new JMenuItem("Conexoes");
        itemReplicationTable = new JMenuItem("temp");
        //icones dos itens

        //itemUsuariosSist.setIcon(new ImageIcon(PlansFrm.class.getResource("/view/images/aplicacao.png")));
        menuCadastros.add(itemConnectionReplication);
        menuCadastros.add(itemReplicationTable);

        menuBar.add(menuCadastros);

        //Aï¿½ï¿½es para os item abaixo
        //Sistema
        
        itemReplicationTable.addActionListener(new ActionListener() {
        		ReplicationTableFrm rtf;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JanelaVerificar(ReplicationDirectionFrm.class.getName())) {
                    
                }
                else {
                	rtf = new ReplicationTableFrm();
                	rtf.setName(ReplicationFrm.class.getName());
                	rtf.setLocation(1, 1);
                    desktopPane.add(rtf);
                    rtf.setVisible(true);
                }


            }

            private void JanelaFocar(ReplicationFrm us) {
                try
                {
                    us.setSelected(true);
                }
                catch	(PropertyVetoException	ex) {
                    ex.printStackTrace();
                }

            }
        });

        
        itemConnectionReplication.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (JanelaVerificar(ReplicationDirectionFrm.class.getName())) {
                    JanelaFocar(rf);
                }
                else {
                	rf = new ReplicationFrm();
                	rf.setName(ReplicationFrm.class.getName());
                	rf.setLocation(1, 1);
                    desktopPane.add(rf);
                    rf.setVisible(true);
                }


            }

            private void JanelaFocar(ReplicationFrm us) {
                try
                {
                    us.setSelected(true);
                }
                catch	(PropertyVetoException	ex) {
                    ex.printStackTrace();
                }

            }
        });


        setJMenuBar(menuBar);
        getContentPane().add(desktopPane);

    }

    private boolean JanelaVerificar(String ls_nome)
    {
        //
        // Pega todas as janelas existentes no Desktop Pane
        //
        JInternalFrame[] la_janelas = desktopPane.getAllFrames();

        //
        // Varre o array das janelas
        //
        for	(JInternalFrame lo_frame : la_janelas) {

            //
            // Se encontrou a janela.
            //
            if	(lo_frame.getName().equalsIgnoreCase(ls_nome)) {
                return true;
            }
        }

        //
        // Se não encontrou a janela
        //
        return false;
    }

}