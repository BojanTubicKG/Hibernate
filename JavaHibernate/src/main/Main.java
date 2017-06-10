package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {

        JFrame f = new JFrame("Firma");
        f.setLayout(new FlowLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 300);
        f.getContentPane().setBackground(Color.GREEN);

        JMenuBar bar = new JMenuBar();
        JMenu jm = new JMenu("Zaposleni");
        JMenuItem jmi1 = new JMenuItem("Prikaz svih zaposlenih");
        jmi1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Session s = HibernateUtil.createSessionFactory().openSession();
                Transaction tx = null;

                String hql = "from Zaposleni as zaposleni";
                Query query = s.createQuery(hql);
                List<Zaposleni> zapp = null;

                try {
                    tx = s.beginTransaction();

                    zapp = query.list();

                    tx.commit();

                } catch (HibernateException ex) {
                    if (tx != null) {
                        tx.rollback();
                    }
                    System.out.println(ex);
                } finally {
                    HibernateUtil.close();
                }

                for (Zaposleni zap : zapp) {
                    System.out.println(zap);
                }
            }
        });
        JMenuItem jmi2 = new JMenuItem("Prikaz po selekciji");
        jmi2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f1 = new JFrame("Prikazi po selekciji : ");
                f1.setLayout(new FlowLayout());
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f1.setSize(300, 100);
                f1.setLocationRelativeTo(null);
                f1.getContentPane().setBackground(Color.CYAN);
                JLabel l = new JLabel("Id trazenog zaposlenog");
                JTextField tf = new JTextField(5);
                JButton button = new JButton("Prikazi");
                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        Session session3 = HibernateUtil.createSessionFactory().openSession();
                        Transaction tx = null;

                        try {
                            tx = session3.beginTransaction();

                            Zaposleni zaposleni = (Zaposleni) session3.load(Zaposleni.class, Integer.valueOf(tf.getText()));
                            System.out.println(zaposleni);

                            tx.commit();

                        } catch (HibernateException ex) {
                            if (tx != null) {
                                tx.rollback();
                            }
                            System.out.println("Zaposleni sa trazenim ID-em ne postoji u bazi podataka!!!");
                            JOptionPane.showMessageDialog(null, "Zaposleni sa trazenim ID-em ne postoji u bazi podataka!!!");
                            System.exit(0);
                        } finally {
                            HibernateUtil.close();
                        }
                    }
                });
                f1.add(l);
                f1.add(tf);
                f1.add(button);
                f1.setVisible(true);
            }
        });
        JMenuItem jmi3 = new JMenuItem("Izmena zaposlenog");
        jmi3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Izmeni zaposlenog");
                frame.setSize(300, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new FlowLayout());
                frame.setLocationRelativeTo(null);
                frame.getContentPane().setBackground(Color.CYAN);

                JLabel la1 = new JLabel("Id zaposlenog za izmenu : ");
                JTextField t1 = new JTextField(10);
                JLabel la2 = new JLabel("Novo ime zaposlenog : ");
                JTextField t2 = new JTextField(10);
                JLabel la3 = new JLabel("Godine   zaposlenog : ");
                JTextField t3 = new JTextField(10);
                JLabel la4 = new JLabel("Adresa   zaposlenog : ");
                JTextField t4 = new JTextField(10);
                JLabel la5 = new JLabel("Zarada   zaposlenog : ");
                JTextField t5 = new JTextField(10);
                JButton bb = new JButton("Izmeni");
                bb.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        Session session2 = HibernateUtil.createSessionFactory().openSession();
                        Transaction tx = null;

                        try {
                            tx = session2.beginTransaction();

                            Zaposleni za = (Zaposleni) session2.load(Zaposleni.class, Integer.valueOf(t1.getText()));
                            za.setIme(t2.getText());
                            za.setGodine(Integer.valueOf(t3.getText()));
                            za.setAdresa(t4.getText());
                            za.setZarada(t5.getText());

                            session2.update(za);
                            System.out.println(za);

                            tx.commit();

                        } catch (HibernateException ex) {
                            if (tx != null) {
                                tx.rollback();
                            }
                            System.out.println("Zaposleni sa trazenim ID-em ne postoji u bazi podataka");
                            JOptionPane.showMessageDialog(null, "Zaposleni sa trazenim ID-em ne postoji u bazi podataka");
                            System.exit(0);
                        } finally {
                            HibernateUtil.close();
                        }
                        JOptionPane.showMessageDialog(null, "Uspesno ste izmenili podatake zaposlenog!!!");

                    }
                });
                frame.add(la1);
                frame.add(t1);
                frame.add(la2);
                frame.add(t2);
                frame.add(la3);
                frame.add(t3);
                frame.add(la4);
                frame.add(t4);
                frame.add(la5);
                frame.add(t5);
                frame.add(bb);
                frame.setVisible(true);
            }
        });
        JMenuItem jmi4 = new JMenuItem("Brisanje zaposlenog");
        jmi4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f1 = new JFrame("Izaberi id zaposlenog za brisanje");
                f1.setLayout(new FlowLayout());
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f1.setSize(400, 100);
                f1.setLocationRelativeTo(null);
                f1.getContentPane().setBackground(Color.CYAN);
                JLabel l = new JLabel("Id zaposlenog za brisanje : ");
                JTextField tf1 = new JTextField(5);
                JButton button1 = new JButton("Izbrisi");
                button1.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        Session session1 = HibernateUtil.createSessionFactory().openSession();
                        Transaction tx = null;

                        try {
                            tx = session1.beginTransaction();

                            Zaposleni zap = (Zaposleni) session1.load(Zaposleni.class, Integer.valueOf(tf1.getText()));
                            session1.delete(zap);

                            tx.commit();
                        } catch (HibernateException ex) {
                            if (tx != null) {
                                tx.rollback();
                            }
                            System.out.println("Zaposleni sa trazenim ID-em ne postoji u bazi podataka !!!");
                            JOptionPane.showMessageDialog(null, "Zaposleni  sa trazenim Id-em ne postoji u bazi podataka !!!");
                            System.exit(0);
                        } finally {
                            HibernateUtil.close();
                        }
                        JOptionPane.showMessageDialog(null, "Uspesno ste obrisali zaposlenog!!!");

                    }
                });

                f1.add(l);
                f1.add(tf1);
                f1.add(button1);
                f1.setVisible(true);
            }
        });
        JMenuItem jmi5 = new JMenuItem("Dodavanje zaposlenog");
        jmi5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f2 = new JFrame("Dodavanje zaposlenog");
                f2.setSize(300, 300);
                f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f2.setLayout(new FlowLayout());
                f2.setLocationRelativeTo(null);
                f2.getContentPane().setBackground(Color.CYAN);

                JLabel l1 = new JLabel("Ime novog zaposlenog :   ");
                JTextField jtf1 = new JTextField(10);
                JLabel l2 = new JLabel("Godine novog zaposlenog : ");
                JTextField jtf2 = new JTextField(10);
                JLabel l3 = new JLabel("Adresa novog zaposlenog : ");
                JTextField jtf3 = new JTextField(10);
                JLabel l4 = new JLabel("Zarada novog zaposlenog : ");
                JTextField jtf4 = new JTextField(10);
                JButton b1 = new JButton("Dodaj zaposlenog");
                b1.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        Zaposleni z = new Zaposleni(jtf1.getText(), Integer.valueOf(jtf2.getText()), jtf3.getText(), jtf4.getText());

                        Session session = HibernateUtil.createSessionFactory().openSession();
                        Transaction tx = null;

                        try {
                            tx = session.beginTransaction();

                            session.persist(z);
                            System.out.println(z);

                            tx.commit();

                        } catch (HibernateException ex) {
                            if (tx != null) {
                                tx.rollback();
                            }
                            System.out.println(ex);
                        } finally {
                            HibernateUtil.close();
                        }
                        JOptionPane.showMessageDialog(null, "Uspesno ste dodali zaposlenog!!!");
                    }
                });

                f2.add(l1);
                f2.add(jtf1);
                f2.add(l2);
                f2.add(jtf2);
                f2.add(l3);
                f2.add(jtf3);
                f2.add(l4);
                f2.add(jtf4);
                f2.add(b1);
                f2.setVisible(true);
            }
        });

        jm.add(jmi1);
        jm.add(jmi2);
        jm.add(jmi3);
        jm.add(jmi4);
        jm.add(jmi5);
        bar.add(jm);
        f.setJMenuBar(bar);
        f.setVisible(true);
    }
}
