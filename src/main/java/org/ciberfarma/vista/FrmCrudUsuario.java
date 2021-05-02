package org.ciberfarma.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmCrudUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtcod;
	private JTextField txtnom;
	private JTextField txtape;
	private JTextField txtusu;
	private JTextField txtfecha;
	private JTextField txttipo;
	private JTextField txtclave;
	private JTextField txtest;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudUsuario frame = new FrmCrudUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmCrudUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCodigo = new JLabel("Codigo: ");
		lblCodigo.setBounds(10, 28, 46, 14);
		contentPane.add(lblCodigo);

		txtcod = new JTextField();
		txtcod.setBounds(62, 25, 86, 20);
		contentPane.add(txtcod);
		txtcod.setColumns(10);

		JLabel lblCodigo_1 = new JLabel("Nombre: ");
		lblCodigo_1.setBounds(10, 57, 46, 14);
		contentPane.add(lblCodigo_1);

		JLabel lblCodigo_2 = new JLabel("Apellidos:");
		lblCodigo_2.setBounds(10, 82, 46, 14);
		contentPane.add(lblCodigo_2);

		JLabel lblCodigo_3 = new JLabel("Usuario :");
		lblCodigo_3.setBounds(10, 107, 46, 14);
		contentPane.add(lblCodigo_3);

		JLabel lblCodigo_4 = new JLabel("F. Nac:");
		lblCodigo_4.setBounds(10, 132, 46, 14);
		contentPane.add(lblCodigo_4);

		JLabel lblCodigo_5 = new JLabel("Tipo:");
		lblCodigo_5.setBounds(243, 157, 46, 14);
		contentPane.add(lblCodigo_5);

		JLabel lblCodigo_6 = new JLabel("Clave:");
		lblCodigo_6.setBounds(10, 157, 46, 14);
		contentPane.add(lblCodigo_6);

		JLabel lblCodigo_7 = new JLabel("Estado:");
		lblCodigo_7.setBounds(243, 132, 46, 14);
		contentPane.add(lblCodigo_7);

		txtnom = new JTextField();
		txtnom.setColumns(10);
		txtnom.setBounds(62, 54, 178, 20);
		contentPane.add(txtnom);

		txtape = new JTextField();
		txtape.setColumns(10);
		txtape.setBounds(62, 79, 178, 20);
		contentPane.add(txtape);

		txtusu = new JTextField();
		txtusu.setColumns(10);
		txtusu.setBounds(62, 104, 86, 20);
		contentPane.add(txtusu);

		txtfecha = new JTextField();
		txtfecha.setColumns(10);
		txtfecha.setBounds(62, 129, 86, 20);
		contentPane.add(txtfecha);

		txttipo = new JTextField();
		txttipo.setColumns(10);
		txttipo.setBounds(307, 154, 86, 20);
		contentPane.add(txttipo);

		txtclave = new JTextField();
		txtclave.setColumns(10);
		txtclave.setBounds(62, 154, 86, 20);
		contentPane.add(txtclave);

		txtest = new JTextField();
		txtest.setColumns(10);
		txtest.setBounds(307, 129, 86, 20);
		contentPane.add(txtest);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registro();
			}
		});
		btnRegistrar.setBounds(304, 24, 89, 23);
		contentPane.add(btnRegistrar);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultar();
			}
		});
		btnConsultar.setBounds(304, 53, 89, 23);
		contentPane.add(btnConsultar);

		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listar();
			}
		});
		btnListar.setBounds(304, 82, 89, 23);
		contentPane.add(btnListar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 182, 414, 251);
		contentPane.add(scrollPane);

		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
	}

	void listar() {
		//Obtener listado
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		List<Usuario> lstUsuarios;	
		if(txttipo.getText().isEmpty()) {
			lstUsuarios = em.createNamedQuery("Usuario.finAll", Usuario.class).getResultList();
		}else {
			int tipo = Integer.parseInt(txttipo.getText());
			lstUsuarios = em.createNamedQuery("Usuario.finAllxTipo", Usuario.class).
					setParameter("xtipo", tipo).getResultList();
		}
				
		//muestro listado en txt/pdf
		txtS.setText("Listado de Usuario\n");
		for(Usuario u : lstUsuarios){
			txtS.append(u.getCodigo() + "\t" + u.getNombre() + "\t" +  u.getApellido() + "\n");
		}

	}

	void consultar() {
		// obtener codigo
		int codigo = Integer.parseInt(txtcod.getText());
		// buscar codigo
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();

		Usuario u = em.find(Usuario.class, codigo);
		if (u == null) {
			JOptionPane.showMessageDialog(this, "Usuario no registrado :");
		} else {
			txtnom.setText(u.getNombre());
			txtape.setText(u.getApellido());
			txtusu.setText(u.getUsuario());
			txtclave.setText(u.getClave());
			txtfecha.setText(u.getFnacim());
			txttipo.setText(u.getTipo() + "");
			txtest.setText(u.getEstado() + "");
		}
	}

	void registro() {
		// TODO Auto-generated method stub
		String nombre = txtnom.getText();
		String apellido = txtape.getText();
		String usuario = txtusu.getText();
		String clave = txtclave.getText();
		String fecha = txtfecha.getText();
		int tipo = Integer.parseInt(txttipo.getText());
		int estado = Integer.parseInt(txtest.getText());

		Usuario u = new Usuario();
		u.setNombre(nombre);
		u.setApellido(apellido);
		u.setUsuario(usuario);
		u.setClave(clave);
		u.setFnacim(fecha);
		u.setTipo(tipo);
		u.setEstado(estado);

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
			JOptionPane.showMessageDialog(this, "Usuario Registrado");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al registrar :" + e.getMessage());
		} finally {
			em.close();
		}

	}
}
