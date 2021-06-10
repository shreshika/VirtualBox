import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.io.File;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import java.nio.file.Files;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

class Student extends JFrame{
    Classroom cr;
    int clicked=0;
    Student(Classroom cr){
      this.cr = cr;
    }
    public void frame(){
      this.setSize(640, 960);
      this.setLayout(null);
      this.setResizable(false);
      this.login();
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
  
    public void login(){
      SLogin slog = new SLogin(cr, this);
      slog.check();
    }
  
    public void loggedIn(int i){
      this.setVisible(true);
      JLabel title = new JLabel("<html>  Welcome "+cr.sdb[i].name+" !<br/>Happy Studying...</html>");
      title.setFont(new Font("Marlett", Font.ITALIC, 26));
      JButton edit = new JButton(" Edit ID/Pswd",new ImageIcon(System.getProperty("user.dir") + "/Assets/edit.png"));
      JButton mat = new JButton("  Material",new ImageIcon(System.getProperty("user.dir") + "/Assets/books1.png"));
      JButton qa = new JButton("  Que/Ans",new ImageIcon(System.getProperty("user.dir")+"/Assets/qa.png"));
      JButton lgt = new JButton("  Logout",new ImageIcon(System.getProperty("user.dir") + "/Assets/logout.jpg"));
  
      
      title.setBounds(210, 170, 300, 80);
      edit.setBounds(230, 300, 180, 50);
      mat.setBounds(230, 370, 180, 50);
      qa.setBounds(230, 440, 180, 50);
      lgt.setBounds(230, 510, 180, 50);
  
      try
      {
        BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/students login.jpg"));
        this.setContentPane(new backImage(bf));
      } 
      catch (IOException e)
      {
        System.out.println(e); 
      }

      Student s = this;
      edit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          SEdit e = new SEdit(cr, s);
          dispose();
          e.frame(i);
        }
      });
      mat.addActionListener(new ActionListener(){
        int clicked=0;
        public void actionPerformed(ActionEvent event){
          
          clicked++;
          String x=Integer.toString(clicked);
          System.out.print(x);
          SMaterial m = new SMaterial(cr);
          m.frame();
        }
      });
      qa.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          SQaA q = new SQaA(cr, s);
          q.frame();
        }
      });
      lgt.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          Student s = new Student(cr);
          dispose();
          s.frame();
        }
      });
      this.add(title);
      this.add(edit);
      this.add(mat);
      this.add(qa);
      this.add(lgt);
    }
  
    public void edit(int i, String name, String pass){
      cr.sdb[i] = new SUser(name, pass);
    }
  
    public void addQ(String q){
      int i=0;
      while(cr.qa[i] != null){
        i++;
      }
      cr.qa[i] = new QA(q, "Still not Answered");
    }
  
    public void re(String n, String p){
      int i=0;
      while(cr.req[i] != null){
        i++;
      }
      cr.req[i] = new Request('S', n, p);
    }
  
  }
  //---------------------------------------------------------------

  class SLogin extends JFrame{
    Classroom cr;
    Student s;
    SLogin(Classroom cr, Student s){
      this.cr = cr;
      this.s = s;
    }
  
    public void check(){
      this.setSize(500, 500);
      this.setLayout(null);
      this.setVisible(true);
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  
      JLabel ln = new JLabel("");
      JLabel lp = new JLabel("");
      JLabel li = new JLabel("User Login");
      li.setFont(new Font("Marlett", Font.BOLD, 17));
      JTextField name = new JTextField("");
      JPasswordField pass = new JPasswordField("");
      JCheckBox sp = new JCheckBox(" Show Password");
      JButton submit = new JButton("Login");
      JButton signup = new JButton("Sign Up");
      JButton back = new JButton("Back");

      JSeparator z = new JSeparator(); 
      z.setVisible(true);
      z.setBounds(0, 40, 300, 3);
      z.setOrientation(SwingConstants.HORIZONTAL); 

      ln.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Assets/sicon.jpg"));
      ln.setVisible(true);
      
      lp.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Assets/pw.jpeg"));
      lp.setVisible(true);

      try
      {
        BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/stu.jpg"));
        this.setContentPane(new backImage(bf));
      } 
      catch (IOException e)
      {
        System.out.println(e);
      }   
      
      li.setBounds(375, 5, 150, 30);
      ln.setBounds(100, 125, 100, 50);
      lp.setBounds(100, 185, 100, 50);
      name.setBounds(210, 135, 180, 35);
      pass.setBounds(210, 195, 180, 35); 
      pass.setEchoChar('*');
      sp.setBounds(210, 240, 180, 25);
      sp.setOpaque(false);
      submit.setBounds(70, 290, 160, 45);
      signup.setBounds(250, 290, 160, 45);
      back.setBounds(160, 350, 160, 45);
      signup.setToolTipText("Not a User? Register");
  
      submit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          int i=0;
          int k=0;
          while(cr.sdb[i] != null){
            if( cr.sdb[i].name.equals(name.getText()) && cr.sdb[i].pass.equals(pass.getText()) ){
              k=1;
              dispose();
              s.loggedIn(i);
            }
            i++;
          }
          if(k==0){
            JOptionPane.showMessageDialog(null,"Incorrect Details.","Login error",JOptionPane.ERROR_MESSAGE);
            name.setText("");
            pass.setText("");
          }
        }
      });
      back.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          dispose();
        }
      });
      signup.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          dispose();
          SSignUp su = new SSignUp(cr, s);
          su.frame();
        }
      });
      sp.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
              pass.setEchoChar((char)0);
            } else {
              pass.setEchoChar('*');
            }
        }
    });
    
      this.add(li);
      this.add(z);
      this.add(ln);
      this.add(lp);
      this.add(name);
      this.add(pass);
      this.add(sp);
      this.add(submit);
      this.add(signup);
      this.add(back);

    }
  }
  //---------------------------------------------------------------
  
  class SEdit extends JFrame{
    Classroom cr;
    Student s;
    JTextField name, pass;
  
    SEdit(Classroom cr, Student s){
      this.cr = cr;
      this.s = s;
    }
  
    public void frame(int i){
      this.setUndecorated(true);
      this.setSize(500, 500);
      this.setLayout(null);
      this.setVisible(true);
  
      try
      {
        BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/stu.jpg"));
        this.setContentPane(new backImage(bf));
      } 
      catch (IOException e)
      {
        System.out.println(e);
      }

      JSeparator z = new JSeparator(); 
      z.setVisible(true);
      z.setBounds(0, 40, 500, 3);
      z.setOrientation(SwingConstants.HORIZONTAL); 

      JLabel ed = new JLabel("Edit Details");
      ed.setFont(new Font("Marlett", Font.BOLD, 17));
      ed.setForeground(Color.BLACK);
      JLabel ln = new JLabel("Name :");
      ln.setFont(new Font("Marlett", Font.ITALIC, 14));
      ln.setForeground(Color.BLACK);
      JLabel inst = new JLabel("Institution :");
      inst.setFont(new Font("Marlett", Font.ITALIC, 14));
      inst.setForeground(Color.BLACK);
      JLabel inst1 = new JLabel("<html>Neil Gogte<br/>Institute of technology</html>");
      inst1.setFont(new Font("Marlett", Font.ITALIC, 14));
      inst1.setForeground(Color.BLACK);
      JLabel cl = new JLabel("Class :");
      cl.setFont(new Font("Marlett", Font.ITALIC, 14));
      cl.setForeground(Color.BLACK);
      JLabel cl1 = new JLabel("Btech III yr");
      cl1.setFont(new Font("Marlett", Font.ITALIC, 14));
      cl1.setForeground(Color.BLACK);
      JLabel lp = new JLabel("Password :");
      lp.setFont(new Font("Marlett", Font.ITALIC, 14));
      lp.setForeground(Color.BLACK);
      name = new JTextField(cr.sdb[i].name);
      pass = new JTextField(cr.sdb[i].pass);
      JButton submit = new JButton("Submit");
      JButton back = new JButton("Back");
  
      ed.setBounds(375, 5, 150, 30);
      ln.setBounds(70, 70, 100, 40);
      name.setBounds(220, 70, 140, 35);
      inst.setBounds(70, 140, 100, 40);
      inst1.setBounds(220, 120, 200, 80);
      cl.setBounds(70, 180, 100, 40);
      cl1.setBounds(220, 180, 200, 40);
      lp.setBounds(70, 230, 100, 40);
      pass.setBounds(220, 230, 140, 35);
      submit.setBounds(80, 330, 140, 35);
      back.setBounds(260, 330, 140, 35);
  
      submit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          s.edit(i, name.getText(), pass.getText());
          JOptionPane.showMessageDialog(null,"Details updated successfully."); 
        }
      });
      back.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          s.loggedIn(i);
          dispose();
        }
      });
      this.add(ed);
      this.add(z);
      this.add(ln);
      this.add(lp);
      this.add(inst);
      this.add(inst1);
      this.add(cl);
      this.add(cl1);
      this.add(name);
      this.add(pass);
      this.add(submit);
      this.add(back);
  
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
  }
  //---------------------------------------------------------------
  
  class SMaterial extends JFrame{
    int j = 0;
    int k = 0;
    //int count=0;
    Classroom cr;
    SMaterial(Classroom cr){
      this.cr = cr;
    }
  
    public void frame(){
      this.setSize(590, 650);

      try
      {
        BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/stumat.jpeg"));
        this.setContentPane(new backImage(bf));
      } 
      catch (IOException e)
      {
        System.out.println(e);
      }
      this.setResizable(false);
      this.setVisible(true);
      this.display();
      //count++;

      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
    }
    
    public void display(){

      JButton back = new JButton("Back");
      back.setBounds(220, 580, 140, 35);
      back.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          dispose();
        }
      });
      this.add(back);
 
    
      if(cr.material[0] == null){
        JOptionPane.showMessageDialog(null,"No files have been uploaded yet.","Attention!",JOptionPane.INFORMATION_MESSAGE);
        dispose();

      }
      else{
        for(int i=0; i<cr.ctr; i++){
          
          if((i)%3==0 && i!=0)
          {j=j+1;
          k=0;}

          JButton b = new JButton(cr.material[i],new ImageIcon(System.getProperty("user.dir") + "/Assets/books1.png"));
          final String fPath = System.getProperty("user.dir")+"/files/"+cr.material[i];
          b.setBounds(10 + 190*k,10+ 190*j , 180, 180);
  
          b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
              try{
                File file = new File(fPath);
                if(!Desktop.isDesktopSupported()){
                  System.out.println("File Not Supported");
                  return;
                }
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
              }
              catch(Exception e){
                System.out.println(e);
              }
            }
          });
          this.add(b);
          k++;

        }
      }
     
    }
  }
  //---------------------------------------------------------------
  
  class SQaA extends JFrame{
    Classroom cr;
    Student s;
    SQaA(Classroom cr, Student s){
      this.cr = cr;
      this.s = s;
    }
  
    public void frame(){
      this.setSize(900, 1000);
      this.setResizable(false);
      try
      {
        BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/images.jpeg"));
        this.setContentPane(new backImage(bf));
      } 
      catch (IOException e)
      {
        
      }
      this.setVisible(true);
  
      this.display();
      this.addQ();

      this.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent event){
          dispose();
        }
      });
    }
  
    public void display(){

      if(cr.qa[0] == null){
        JOptionPane.showMessageDialog(null,"No questions yet.","Attention!",JOptionPane.INFORMATION_MESSAGE);
      }
      else{
        int i=0;
        while(cr.qa[i] != null){
          JLabel tle = new JLabel("Doubt Sessions");
          tle.setFont(new Font("Marlett", Font.ITALIC, 23));
          tle.setForeground(Color.BLACK);
          tle.setBounds(350, 10, 200, 200);
          this.add(tle);

          JTextField q = new JTextField("Q"+(i+1)+") " + cr.qa[i].que);
          q.setForeground(Color.BLACK);
          q.setOpaque(false);
          q.setBorder(javax.swing.BorderFactory.createEmptyBorder());
          q.setBounds(100, 120*(i) + 140, 580, 80);
          q.setFont(new Font("Marlett", Font.ITALIC ,20));
          JTextField a = new JTextField("A) " +cr.qa[i].ans);
          a.setForeground(Color.BLACK);
          a.setOpaque(false);
          a.setBorder(javax.swing.BorderFactory.createEmptyBorder());
          a.setBounds(100, 120*(i) + 180, 580, 80);
          a.setFont(new Font("Marlett", Font.ITALIC, 20));
          q.setEditable(false);
          a.setEditable(false);
            
          this.add(q);
          this.add(a);

          i++;
        }
      }

    }
  
    public void addQ(){
      JTextField q = new JTextField("");
      JButton b = new JButton("Add");
      JLabel aq = new JLabel("Enter your question below: ");
      aq.setFont(new Font("Marlett", Font.ITALIC, 21));
      aq.setForeground(Color.BLACK);
      aq.setBounds(65, 650, 300, 200);
      this.add(aq);
      
  
      b.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          s.addQ(q.getText());
          SQaA z = new SQaA(cr, s);
          z.frame();
          dispose();
        }
      });
      JButton back = new JButton("Back");
      back.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          dispose();
        }
      });
      q.setBounds(55, 775, 780,75);
      b.setBounds(310, 860, 100 , 40);
      back.setBounds(440, 860, 100, 40);
      this.add(q);
      this.add(b);
      this.add(back);
      JLabel zx = new JLabel();
      this.add(zx);

    }
  }
  //---------------------------------------------------------------
  
  class SSignUp extends JFrame{
    Classroom cr;
    Student s;
    SSignUp(Classroom cr, Student s){
      this.cr = cr;
      this.s = s;
    }
  
    public void frame(){
      this.setSize(450, 500);
      this.setLayout(null);
      this.setVisible(true);
  
      try
      {
        BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/stu.jpg"));
        this.setContentPane(new backImage(bf));
      } 
      catch (IOException e)
      {
        
      }

      this.addButtons();
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
  
    public void addButtons(){
      JButton submit = new JButton("Register");
      JButton back = new JButton("Back");
  
      JLabel ln = new JLabel("Name :");
      ln.setFont(new Font("Marlett", Font.ITALIC, 14));
      ln.setForeground(Color.BLACK);
      JLabel inst = new JLabel("Institution :");
      inst.setFont(new Font("Marlett", Font.ITALIC, 14));
      inst.setForeground(Color.BLACK);
      JLabel inst1 = new JLabel("<html>Neil Gogte<br/>Institute of Technology</html>");
      inst1.setFont(new Font("Marlett", Font.ITALIC, 14));
      inst1.setForeground(Color.BLACK);
      JLabel cl = new JLabel("Class :");
      cl.setFont(new Font("Marlett", Font.ITALIC, 14));
      cl.setForeground(Color.BLACK);
      JLabel cl1 = new JLabel("Btech III yr");
      cl1.setFont(new Font("Marlett", Font.ITALIC, 14));
      cl1.setForeground(Color.BLACK);
      JLabel lp = new JLabel("Password :");
      lp.setFont(new Font("Marlett", Font.ITALIC, 14));
      lp.setForeground(Color.BLACK);
  
      JTextField name = new JTextField("");
      JTextField pass = new JTextField("");
      JLabel head = new JLabel("New Account");
      head.setFont(new Font("Marlett", Font.ITALIC, 19));
      head.setForeground(Color.BLACK);
      String classes[]={" ","BTech III yr"};
      JComboBox<String> cb=new JComboBox<>(classes);
  
      head.setBounds(150, 20, 150, 60);
      ln.setBounds(50, 100, 100, 40);
      name.setBounds(180, 100, 150, 35);
      inst.setBounds(50, 170, 100, 40);
      inst1.setBounds(180, 170, 150, 40);
      cl.setBounds(50, 240, 100, 40);
      cb.setBounds(180, 240, 150, 40);
      lp.setBounds(50, 310, 100, 40);
      pass.setBounds(180, 310, 150, 35);
  
      submit.setBounds(110, 390, 100, 40);
      back.setBounds(220, 390, 100, 40);
  
      submit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          if(!name.getText().equals("") && !pass.getText().equals("") && pass.getText().length()<32){
            JOptionPane.showMessageDialog(null, "Submitted Request to the Admnistrator");
            dispose();
            s.re(name.getText(), pass.getText());
          }
          else{
            JOptionPane.showMessageDialog(null, "Please fill Details Correctly");
          }
        }
      });
      back.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          Student s = new Student(cr);
          s.frame();
          dispose();
        }
      });
      this.add(head);
      this.add(ln);
      this.add(inst);
      this.add(inst1);
      this.add(cl);
      this.add(cb);
      this.add(lp);
      this.add(name);
      this.add(pass);
  
      this.add(submit);
      this.add(back);
    }
  
  }
