import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import java.nio.file.Files;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

class Admin extends JFrame{
    Classroom cr;
    Admin(Classroom cr){
      this.cr = cr;
    }
  
    public void frame(){
    this.setSize(485, 485);
            
    try
    {
      BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/adm1.jpeg"));
      this.setContentPane(new backImage(bf));
    } 
    catch (IOException e)
    {
      System.out.println(e);
    }
    this.setLayout(null);

    JSeparator z = new JSeparator(); 
    z.setVisible(true);
    this.setResizable(false);
    z.setBounds(0, 10, 200, 3);
    z.setOrientation(SwingConstants.HORIZONTAL); 
    this.add(z);
    this.login();
    }
  
    public void login(){
      ALogin alog = new ALogin(cr, this);
      alog.check();
    }
  
    public void loggedIn(){

      this.setVisible(true);
      JLabel title = new JLabel("Admin");
      title.setFont(new Font("Marlett", Font.BOLD, 17));
      title.setBounds(425, 5, 150, 30);
      title.setForeground(Color.BLACK);
      JButton edit = new JButton("Edit Profile");
      JButton mat = new JButton("Material");
      JButton qa = new JButton("Q/A");
      JButton vf = new JButton("View Faculty Details");
      JButton vs = new JButton("View Student Details");
      JButton r = new JButton("Requests");
      //JButton ac = new JButton("Activity");
  
      edit.setBounds(150, 100, 200, 40);
      vf.setBounds(150, 150, 200, 40);
      vs.setBounds(150, 200, 200, 40);
      mat.setBounds(150, 250, 200, 40);
      qa.setBounds(150, 300, 200, 40);
      r.setBounds(150, 350, 200, 40);
      //ac.setBounds(150, 400, 200, 40);

      Admin a = this;
      Teacher t = new Teacher(cr);
      edit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          AEdit e = new AEdit(cr, a);
          e.frame();
        }
      });
      mat.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          Material m = new Material(cr, t);
          m.frame();
        }
      });
      qa.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          AQaA q = new AQaA(cr, t);
          q.frame();
        }
      });
      vf.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          ViewFaculty v = new ViewFaculty(cr, a);
          v.frame();
        }
      });
      vs.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          ViewStudent v = new ViewStudent(cr, a);
          v.frame();
        }
      });
      r.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          ViewRequest re = new ViewRequest(cr, a);
          re.frame();
        }
      });
      /*ac.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event) {                
          Activity a=new Activity();
          a.frame();                                   
    }       
});*/
      this.add(title);
      this.add(edit);
      this.add(mat);
      this.add(qa);
      this.add(vf);
      this.add(vs);
      this.add(r);
      //this.add(ac);
    }
  
    public void edit(String n, String p){
      cr.a_user = n;
      cr.a_pass = p;
    }
  
    public void upload(String path){
      try{
        File from = new File(path);
        File to = new File(System.getProperty("user.dir")+"/files/"+from.getName());
        Files.copy(from.toPath(), to.toPath());
        cr.material[cr.ctr] = from.getName();
        cr.ctr++;
      }
      catch(Exception e){
        System.out.println(e);
      }
    }
  
    public void del(String fName){
      File f = new File(System.getProperty("user.dir")+"/files/"+fName);
      f.delete();
      for(int i=0; i<cr.ctr; i++){
        if(cr.material[i] == fName){
          for(int j=i; j<cr.ctr; j++){
            cr.material[j] = cr.material[j+1];
          }
          cr.ctr--;
          return;
        }
      }
    }
   
    public void delQ(String b){
      int i = Integer.parseInt(String.valueOf(b.charAt(b.length()-1))) - 1;
      while(cr.qa[i+1] != null){
        cr.qa[i].que = cr.qa[i+1].que;
        cr.qa[i].ans = cr.qa[i+1].ans;
        i++;
      }
      cr.qa[i] = null;
    }
  
    public void editF(String j, String n, String p){
      int i = Integer.parseInt(String.valueOf(j.charAt(j.length()-1))) - 1;
      cr.db[i] = new User(n, p);
    }
  
    public void delF(String j){
      int i = Integer.parseInt(String.valueOf(j.charAt(j.length()-1))) - 1;
      while(cr.db[i+1] != null){
        cr.db[i].name = cr.db[i+1].name;
        cr.db[i].pass = cr.db[i+1].pass;
        i++;
      }
      cr.db[i] = null;
    }
  
    public void editS(String j, String n, String p){
      int i = Integer.parseInt(String.valueOf(j.charAt(j.length()-1))) - 1;
      cr.sdb[i] = new SUser(n, p);
    }
  
    public void delS(String j){
      int i = Integer.parseInt(String.valueOf(j.charAt(j.length()-1))) - 1;
      while(cr.sdb[i+1] != null){
        cr.sdb[i].name = cr.sdb[i+1].name;
        cr.sdb[i].pass = cr.sdb[i+1].pass;
        i++;
      }
      cr.sdb[i] = null;
    }
  
    public void decline(String j){
      int i = Integer.parseInt(String.valueOf(j.charAt(j.length()-1))) - 1;
      while(cr.req[i+1] != null){
        cr.req[i] = cr.req[i+1];
        i++;
      }
      cr.req[i] = null;
    }
  
    public void accept(String j){
      int i = Integer.parseInt(String.valueOf(j.charAt(j.length()-1))) - 1;
      if(cr.req[i].ident == 'T'){
      int k=0;
      while(cr.db[k] != null)
        k++;
      cr.db[k] = new User(cr.req[i].name, cr.req[i].pass);
      while(cr.req[i+1] != null){
        cr.req[i] = cr.req[i+1];
        i++;
      }
      cr.req[i] = null;
      return;
    }
    if(cr.req[i].ident == 'S'){
      int k=0;
      while(cr.sdb[k] != null)
        k++;
      cr.sdb[k] = new SUser(cr.req[i].name, cr.req[i].pass);
      while(cr.req[i+1] != null){
        cr.req[i] = cr.req[i+1];
        i++;
      }
      cr.req[i] = null;
    }
   }  
  
  }
  //---------------------------------------------------------------
  
  class ALogin extends JFrame{
    Classroom cr;
    Admin a;
    ALogin(Classroom cr, Admin a){
      this.cr = cr;
      this.a = a;
    }
  
    public void check(){
      this.setSize(500, 500);
      this.setResizable(false);
      this.setLayout(null);
      this.setVisible(true);
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  
      JLabel ln = new JLabel("");
      JLabel lp = new JLabel("");
      JLabel li = new JLabel("Admin Login");
      li.setFont(new Font("Marlett", Font.BOLD, 17));
      JTextField name = new JTextField("");
      JPasswordField pass = new JPasswordField("");
      JCheckBox sp = new JCheckBox(" Show Password");
      JButton submit = new JButton("Login");
      JButton back = new JButton("Back");

      JSeparator z = new JSeparator(); 
      z.setVisible(true);
      z.setBounds(0, 40, 500, 3);
      z.setOrientation(SwingConstants.HORIZONTAL); 

      ln.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Assets/icon.jpeg"));
      ln.setVisible(true);
      
      lp.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Assets/pw.jpeg"));
      lp.setVisible(true);

      try
      {
        BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/background1.jpeg"));
        this.setContentPane(new backImage(bf));
      } 
      catch (IOException e)
      {
        System.out.println(e);
      }
 
      li.setBounds(360, 5, 150, 30);
      ln.setBounds(100, 125, 100, 50);
      lp.setBounds(100, 185, 100, 50);
      name.setBounds(210, 135, 180, 35);
      pass.setBounds(210, 195, 180, 35); 
      pass.setEchoChar('*');
      sp.setBounds(210, 240, 180, 25);
      sp.setOpaque(false);
      submit.setBounds(70, 290, 160, 45);
      back.setBounds(240, 290, 160, 45);
  
      submit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          if( name.getText().equals(cr.a_user) && pass.getText().equals(cr.a_pass) ){
            dispose();
            a.loggedIn();
          }
          else
          {
            JOptionPane.showMessageDialog(null,"Incorrect Username/Password.","Login error",JOptionPane.ERROR_MESSAGE);
          }
          name.setText("");
          pass.setText("");
        }
      });
      
      back.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          dispose();
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
        this.add(sp);
        this.add(back);
      this.add(ln);
      this.add(lp);
  
      this.add(name);
      this.add(pass);
      this.add(submit);
    }
  }
  //---------------------------------------------------------------
  
  class AEdit extends JFrame{
    Classroom cr;
    Admin a;
    JTextField name, pass;
  
    AEdit(Classroom cr, Admin a){
      this.cr = cr;
      this.a = a;
    }
  
    public void frame(){
    try
    {
      BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/images.jpeg"));
      this.setContentPane(new backImage(bf));
    } 
    catch (IOException e)
    {
      System.out.println(e);
    }
      this.setSize(500, 500);
      this.setLayout(null);
      this.setVisible(true);
  
      name = new JTextField(cr.a_user);
      pass = new JTextField(cr.a_pass);
      JButton submit = new JButton("Submit");
  
      name.setBounds(160, 150, 200, 40);
      pass.setBounds(160, 200, 200, 40);
      submit.setBounds(160, 250, 200, 40);
  
      submit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          a.edit(name.getText(), pass.getText());
          dispose();
        }
      });
  
      this.add(name);
      this.add(pass);
      this.add(submit);
  
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
  }
  //---------------------------------------------------------------
  
  class AQaA extends JFrame{
    Classroom cr;
    Teacher t;
    AQaA(Classroom cr, Teacher t){
      this.cr = cr;
      this.t = t;
    }
  
    public void frame(){
      this.setSize(600, 900);
      this.setResizable(false);
      try
      {
        BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/images.jpeg"));
        this.setContentPane(new backImage(bf));
      } 
      catch (IOException e)
      {
        System.out.println(e);
      }
      this.setVisible(true);
  
      this.display();
  
      this.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent event){
          dispose();
        }
      });
    }
  
    public void display(){
  
      if(cr.qa[0] == null){
        JOptionPane.showMessageDialog(null,"No doubts asked yet.","Attention!",JOptionPane.INFORMATION_MESSAGE);
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
          q.setBounds(100, 140*(i) + 180, 580, 80);
          q.setFont(new Font("Marlett", Font.ITALIC ,20));
          JTextField a = new JTextField(cr.qa[i].ans);
          a.setForeground(Color.BLACK);
          a.setOpaque(false);
          a.setBorder(javax.swing.BorderFactory.createEmptyBorder());
          a.setBounds(100, 140*(i) + 220, 580, 80);
          a.setFont(new Font("Marlett", Font.ITALIC, 20));
          q.setEditable(false);
          a.setEditable(false);
            
          this.add(q);
          this.add(a);
  
          JButton back = new JButton("Back");
          back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
              dispose();
            }
          });
          back.setBounds(400, 860, 100, 40);
          this.add(back);
  
          JButton del = new JButton("Del Q"+(i+1));
          del.setBounds(400, 140*i + 290 , 120 , 30);
          del.setVisible(true);
  

          del.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
              t.delQ(event.getActionCommand());
              QaA q = new QaA(cr, t);
              q.frame();
              dispose();
            }
          });
  
          this.add(del);  
          i++;
  
        }
      }
  
    }
  }
  //---------------------------------------------------------------
  
  class ViewFaculty extends Frame{
    Classroom cr;
    Admin a;
    ViewFaculty(Classroom cr, Admin a){
      this.cr = cr;
      this.a = a;
    }
  
    public void frame(){
      this.setSize(600, 600);
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      this.setVisible(true);
  
      this.display();
      this.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent event){
          dispose();
        }
      });
    }
  
    public void display(){
      int i=0;
      while(cr.db[i] != null){
        JLabel aname = new JLabel("Username:");
        JLabel aspace = new JLabel(" ");
        JLabel aspace1 = new JLabel(" ");
        JTextField name = new JTextField(cr.db[i].name);
        JLabel apass = new JLabel("Password:");
        JTextField pass = new JTextField(cr.db[i].pass);
        JButton edit = new JButton("           Edit          ");
        JButton submit = new JButton("Submit Changes of Faculty "+(i+1));
        JButton del = new JButton("Delete Faculty "+(i+1));
        name.setEditable(false);
        pass.setEditable(false);
        submit.setVisible(false);
  
        edit.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent event){
            name.setEditable(true);
            pass.setEditable(true);
            submit.setVisible(true);
          }
        });
        submit.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent event){
            a.editF(event.getActionCommand(), name.getText(), pass.getText());
            name.setEditable(false);
            pass.setEditable(false);
            submit.setVisible(false);
          }
        });
        del.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent event){
            dispose();
            a.delF(event.getActionCommand());
          }
        });
        this.add(aspace1);
        this.add(aname);
        this.add(name);
        this.add(aspace);
        this.add(apass);
        this.add(pass);
        this.add(edit);
        this.add(submit);
        this.add(del);
        i++;
      }
    }
  }
  //---------------------------------------------------------------
  
  class ViewStudent extends Frame{
    Classroom cr;
    Admin a;
    ViewStudent(Classroom cr, Admin a){
      this.cr = cr;
      this.a = a;
    }
  
    public void frame(){
      this.setSize(300, 300);
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      this.setVisible(true);
  
      this.display();
      this.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent event){
          dispose();
        }
      });
    }
  
    public void display(){
      int i=0;
      while(cr.sdb[i] != null){
        JLabel aname = new JLabel("Username:");
        JLabel aspace = new JLabel(" ");
        JLabel aspace1 = new JLabel(" ");
        JLabel apass = new JLabel("Password:");
        JTextField name = new JTextField(cr.sdb[i].name);
        JTextField pass = new JTextField(cr.sdb[i].pass);
        JButton edit = new JButton("           Edit          ");
        JButton submit = new JButton("Submit Changes of Student "+(i+1));
        JButton del = new JButton("Delete Student "+(i+1));
        name.setEditable(false);
        pass.setEditable(false);
        submit.setVisible(false);
  
        edit.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent event){
            name.setEditable(true);
            pass.setEditable(true);
            submit.setVisible(true);
          }
        });
        submit.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent event){
            a.editS(event.getActionCommand(), name.getText(), pass.getText());
            name.setEditable(false);
            pass.setEditable(false);
            submit.setVisible(false);
          }
        });
        del.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent event){
            dispose();
            a.delS(event.getActionCommand());
          }
        });
  
        this.add(aspace1);
        this.add(aname);
        this.add(name);
        this.add(aspace);
        this.add(apass);
        this.add(pass);
        this.add(edit);
        this.add(submit);
        this.add(del);
        i++;
      }
    }
  }
  //---------------------------------------------------------------

  class ViewRequest extends Frame{
    Classroom cr;
    Admin a;
    ViewRequest(Classroom cr, Admin a){
      this.cr = cr;
      this.a = a;
    }
  
    public void frame(){
      this.setSize(500, 500);
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      this.setVisible(true);
  
      this.display();
      this.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent event){
          dispose();
        }
      });
    }
  
    public void display(){
      if(cr.req[0] == null){
        JLabel l = new JLabel("No Requests");
        this.add(l);
      }
      else{
        int i=0;
        while(cr.req[i] != null){
          String z;
          if(cr.req[i].ident == 'S')
            z = "---Student---";
          else
            z = "---Teacher---";
          JLabel iden = new JLabel(z);
          JLabel name = new JLabel("Username - "+ cr.req[i].name);
          JLabel pass = new JLabel("Password - "+ cr.req[i].pass);
          JButton accept = new JButton("Accept Request "+(i+1));
          JButton dec = new JButton("Decline Request "+(i+1));
  
          accept.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
              dispose();
              a.accept(event.getActionCommand());
            }
          });
          dec.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
              dispose();
              a.decline(event.getActionCommand());
            }
          });
          this.add(iden);
          this.add(name);
          this.add(pass);
          this.add(accept);
          this.add(dec);
          i++;
        }
      }
    }
  }
  //------------------------------------------------------- 
 /*class Activity extends Frame{
    Classroom cr;
    Admin a;
    Activity(Classroom cr, Admin a){
      this.cr = cr;
      this.a = a;
    }
  
  public void frame(){
      this.setSize(500, 500);
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      this.setVisible(true);
  
      this.display();
      this.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent event){
          dispose();
        }
      });
    }
  }*/
   