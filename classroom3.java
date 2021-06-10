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

class backImage extends JComponent {
  Image i;
  public backImage(Image i) {
  this.i = i;
  }

  @Override
  public void paintComponent(Graphics g) {
  g.drawImage(i, 0, 0, null);
  }
}
//---------------------------------------------------------------

class User{
  String name, pass;
  User(String name, String pass){
    this.name = name;
    this.pass = pass;
  }
}
//---------------------------------------------------------------

class SUser{
  String name, pass;
  SUser(String name, String pass){
    this.name = name;
    this.pass = pass;
  }
}
//---------------------------------------------------------------

class QA{
  String que, ans;
  QA(String q, String a){
    this.que = q;
    this.ans = a;
  }
}
//---------------------------------------------------------------

class Request{
  char ident;
  String name, pass;
  Request(char ident, String name, String pass){
    this.ident = ident;
    this.name = name;
    this.pass = pass;
  }
}
//---------------------------------------------------------------

class Classroom extends JFrame{

  User[] db;        //Teacher's Database
  SUser[] sdb;      //Student's Database
  Request[] req;
  String[] material = new String[25];
  int ctr = 0;
  QA[] qa = new QA[10];     //Q/A

  String a_user = "admin";      //Admin username
  String a_pass = "admin";      //Admin Password

  public void addButtons(){
    JButton stu = new JButton("Student Login");
    JButton teacher = new JButton("Teacher Login");
    JButton admin = new JButton("Admin Login");

    stu.setBounds(475, 180, 180, 45);
    teacher.setBounds(475, 240, 180, 45);
    admin.setBounds(475, 300, 180, 45);

    Classroom cr = this;
    teacher.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        Teacher t = new Teacher(cr);
        t.frame();
      }
    });
    stu.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        Student s = new Student(cr);
        s.frame();
      }
    });
    admin.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        Admin a = new Admin(cr);
        a.frame();
      }
    });

    this.add(stu);
    this.add(teacher);
    this.add(admin);
  }

  public void database(){
    db = new User[5];
    db[0] = new User("Teacher2", "pass");
    db[1] = new User("Teacher1", "pass");

    sdb = new SUser[5];
    sdb[0] = new SUser("Student2", "pass");
    sdb[1] = new SUser("Student1", "pass");
    sdb[2] = new SUser("Student", "pass");

    req = new Request[5];
  }

  public void imgt()
  {

    try
    {
      BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "Assets/login.jpeg"));
      this.setContentPane(new backImage(bf));
    }
    catch (IOException e)
    {
      System.out.println(e);
    }

    JLabel img = new JLabel("");
    img.setIcon(new ImageIcon(System.getProperty("user.dir")+"/Assets/login.jpeg"));
    img.setBounds(0, 60,500,400);
    img.setVisible(true);
    this.add(img);

    JLabel label = new JLabel ("Welcome to Virtubox");
    label.setFont(new Font("Marlett", Font.ITALIC, 28));
    label.setBounds(155, 5, 500, 100);
    this.add(label);

    JLabel label1 = new JLabel("Virtubox - Connecting Minds.");
    label1.setFont(new Font("Marlett", Font.ITALIC, 24));
    label1.setBounds(125, 450, 600, 100);
    this.add(label1);
  }

  public void mat(){
    String[] temp = new String[25];
    int count=0;
    File path = new File(System.getProperty("user.dir")+"/files");

    if(!path.exists()){
      path.mkdir();
    }
    temp = path.list();

    for(int i=0; i<temp.length; i++){
      material[i] = temp[i];
      ctr++;
    }
  }

  public void qaa(){
    qa[0] = new QA("What is capital of India?", "Still not Answered");
    qa[1] = new QA("What is your reaction after you completed this Project?", "Still not Answered");
  }

  public static void main(String args[]){
    Classroom cr = new Classroom();
    JPanel contentPane = new JPanel();
    cr.setContentPane(contentPane);
    cr.setResizable(false);
    cr.setSize(700, 600);
    cr.setLayout(null);
    cr.setVisible(true);

    cr.imgt();
    cr.addButtons();
    cr.database();
    cr.mat();
    cr.qaa();

    cr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
//---------------------------------------------------------------

class Teacher extends JFrame{
    Classroom cr;
    Teacher(Classroom cr){
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
    Login l = new Login(cr, this);
    l.check();
  }

  public void loggedIn(int i){
    this.setVisible(true);
    JLabel title = new JLabel("<html>  Welcome "+cr.db[i].name+" !<br/>Happy Teaching...</html>");
    title.setFont(new Font("Marlett", Font.ITALIC, 26));
    title.setForeground(Color.BLACK);
    JButton edit = new JButton(" Edit ID/Pswd",new ImageIcon(System.getProperty("user.dir") + "/Assets/edit.png"));
    JButton mat = new JButton("  Material",new ImageIcon(System.getProperty("user.dir") + "/Assets/books1.png"));
    JButton qa = new JButton("  Que/Ans",new ImageIcon(System.getProperty("user.dir")+"/Assets/qa.png"));
    JButton lgt = new JButton("  Logout",new ImageIcon(System.getProperty("user.dir") + "/Assets/logout.jpg"));
    

    title.setBounds(210, 190, 300, 80);
    edit.setBounds(230, 300, 180, 50);
    mat.setBounds(230, 370, 180, 50);
    qa.setBounds(230, 440, 180, 50);
    lgt.setBounds(230, 510, 180, 50);
    

    try
    {
      BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "Assets/start.jpeg"));
      this.setContentPane(new backImage(bf));
    }
    catch (IOException e)
    {
      System.out.println(e);
    }

    Teacher teacher = this;
    edit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        Edit e = new Edit(cr, teacher);
        e.frame(i);
        dispose();
      }
    });
    mat.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        Material m = new Material(cr, teacher);
        m.frame();
      }
    });
    qa.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        QaA q = new QaA(cr, teacher);
        q.frame();
      }
    });
    lgt.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        Student s = new Student(cr);
        dispose();
        teacher.frame();
      }
    });
    
    this.add(title);
    this.add(edit);
    this.add(mat);
    this.add(lgt);
   
    this.add(qa);
  }

  public void edit(int i, String name, String pass){
    cr.db[i] = new User(name, pass);
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
    }}
    
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

  public void editQ(String b, String a){
    int i = Integer.parseInt(String.valueOf(b.charAt(b.length()-1))) - 1;
    cr.qa[i].ans = a;
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

  public void re(String n, String p){
    int i=0;
    while(cr.req[i] != null){
      i++;
    }
    cr.req[i] = new Request('T', n, p);
  }

}
//---------------------------------------------------------------

class Login extends JFrame{
  Classroom cr;
  Teacher t;

  Login(Classroom cr, Teacher t){
    this.cr = cr;
    this.t = t;
  }

  public void check(){
    this.setSize(500, 500);
      this.setLayout(null);
      this.setVisible(true);
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      JLabel ln = new JLabel("");
      JLabel lp = new JLabel("");
      JLabel li = new JLabel("Teacher Login");
      li.setFont(new Font("Marlett", Font.BOLD, 17));
      JTextField name = new JTextField("");
      JPasswordField pass = new JPasswordField("");
      JCheckBox sp = new JCheckBox(" Show Password");
      JButton submit = new JButton("Login");
      JButton signup = new JButton("Sign Up");
      JButton back = new JButton("Back");

      JSeparator z = new JSeparator();
      z.setVisible(true);
      z.setBounds(0, 40, 500, 3);
      z.setOrientation(SwingConstants.HORIZONTAL);

      ln.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Assets/teacheri.png"));
      ln.setVisible(true);

      lp.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Assets/pw.jpeg"));
      lp.setVisible(true);


      try
      {
        BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/background1.jpg"));
        this.setContentPane(new backImage(bf));
      }
      catch (IOException e)
      {
        System.out.println(e);
      }

      li.setBounds(345, 5, 150, 30);
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
        while(cr.db[i] != null){
          if( cr.db[i].name.equals(name.getText()) && cr.db[i].pass.equals(pass.getText()) )
          {
            k=1;
            dispose();
            t.loggedIn(i);
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
    signup.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        dispose();
        SignUp s = new SignUp(cr, t);
        s.frame();
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
    this.add(signup);
  }
}
//---------------------------------------------------------------

class Edit extends JFrame{
  Classroom cr;
  Teacher t;
  JTextField name, pass;

  Edit(Classroom cr, Teacher t){
    this.cr = cr;
    this.t = t;
  }

  public void frame(int i){
    this.setUndecorated(true);
    this.setSize(500, 500);
    this.setLayout(null);
    this.setVisible(true);

    try
    {
      BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/edit1.jpeg"));
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
    ln.setFont(new Font("Marlett", Font.BOLD, 14));
    ln.setForeground(Color.BLACK);
    JLabel inst = new JLabel("Institution :");
    inst.setFont(new Font("Marlett", Font.BOLD, 14));
    inst.setForeground(Color.BLACK);
    JLabel inst1 = new JLabel("<html>Neil Gogte<br/>Institute of technology</html>");
    inst1.setFont(new Font("Marlett", Font.BOLD, 14));
    inst1.setForeground(Color.BLACK);

    JLabel lp = new JLabel("Password :");
    lp.setFont(new Font("Marlett", Font.BOLD, 14));
    lp.setForeground(Color.BLACK);
    name = new JTextField(cr.db[i].name);
    pass = new JTextField(cr.db[i].pass);
    JButton submit = new JButton("Submit");
    JButton back = new JButton("Back");

    ed.setBounds(375, 5, 150, 30);
    ln.setBounds(70, 70, 100, 40);
    name.setBounds(220, 70, 140, 35);
    inst.setBounds(70, 140, 100, 40);
    inst1.setBounds(220, 120, 200, 80);
    lp.setBounds(70, 210, 100, 40);
    pass.setBounds(220, 210, 140, 35);
    submit.setBounds(80, 300, 140, 35);
    back.setBounds(260, 300, 140, 35);

    submit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        t.edit(i, name.getText(), pass.getText());
        JOptionPane.showMessageDialog(null,"Details updated successfully.","Attention!",JOptionPane.INFORMATION_MESSAGE);
            }
    });
    back.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        dispose();
        t.loggedIn(i);

      }
    });
    this.add(ed);
    this.add(z);
    this.add(ln);
    this.add(lp);
    this.add(inst);
    this.add(inst1);
    this.add(name);
    this.add(pass);
    this.add(submit);
    this.add(back);

    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }
}
//---------------------------------------------------------------

class Material extends JFrame{
  int j = 0;
  int k = 0;
  Classroom cr;
  Teacher t;
  Material(Classroom cr, Teacher t){
    this.cr = cr;
    this.t = t;
  }

  public void frame(){
      try
      {
        BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/stumat.jpeg"));
        this.setContentPane(new backImage(bf));
      }
      catch (IOException e)
      {
        System.out.println(e);
      }
      this.setSize(570, 650);
      this.setResizable(false);
      this.setVisible(true);
      this.display();
      this.upload();
      this.delete();

      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void display(){

        JButton back = new JButton("Back");
        back.setBounds(200, 575, 140, 40);
        back.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
            dispose();
        }
        });
        this.add(back);


        if(cr.material == null){
        JLabel l = new JLabel("Its empty");
        l.setBounds(50, 100, 100, 40);
        this.add(l);
        }
        else{
        for(int i=0; i<cr.ctr; i++){

            if((i)%3==0 && i!=0)
            {j=j+1;
            k=0;}

            JButton b = new JButton(cr.material[i],new ImageIcon(System.getProperty("user.dir") + "/Assets/books1.png"));
            final String fPath = System.getProperty("user.dir")+"/files/"+cr.material[i];
            b.setBounds(30 + 170*k,10+ 170*j , 160, 160);

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

  public void upload(){
    JButton b = new JButton("Upload");
    b.setBounds(125, 520, 140, 45);


    b.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = jfc.getSelectedFile();
          t.upload(selectedFile.getAbsolutePath());
          Material m = new Material(cr, t);
          m.frame();
          dispose();
        }
      }
    });
    this.add(b);
  }

  public void delete(){
    JButton b = new JButton("Delete");
    b.setBounds(285, 520, 140, 45);

    b.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        Delete d = new Delete(cr, t);
        d.frame();
        dispose();
        JOptionPane.showMessageDialog(null,"Selected file will be deleted.","Attention!",JOptionPane.INFORMATION_MESSAGE);
      }
    });

    this.add(b);
  }
}
//---------------------------------------------------------------

class Delete extends JFrame{
  int j = 0;
  int k = 0;
  Classroom cr;
  Teacher t;
  Delete(Classroom cr, Teacher t){
    this.cr = cr;
    this.t = t;
  }

  public void frame(){
      try
      {
        BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/stumat.jpeg"));
        this.setContentPane(new backImage(bf));
      }
      catch (IOException e)
      {
        System.out.println(e);
      }
      this.setSize(570, 650);
      this.setResizable(false);
      this.setVisible(true);

      this.del();
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  public void del(){

    JButton back = new JButton("Back");
    back.setBounds(200, 575, 140, 40);
    back.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        Material m = new Material(cr, t);
        m.frame();
        dispose();
      }
    });
    this.add(back);

    for(int i=0; i<cr.ctr; i++){
      if((i)%3==0 && i!=0)
      {j=j+1;
      k=0;}

      JButton b = new JButton(cr.material[i],new ImageIcon(System.getProperty("user.dir") + "/Assets/books1.png"));
      final String fPath = System.getProperty("user.dir")+"/files/"+cr.material[i];
      b.setBounds(30 + 170*k,10+ 170*j , 160, 160);

      b.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
          t.del(event.getActionCommand());
          Delete d = new Delete(cr, t);
          d.frame();
          dispose();
        }
      });
      this.add(b);
      k++;
    }
  }
}
//---------------------------------------------------------------

class QaA extends JFrame{
  Classroom cr;
  Teacher t;
  QaA(Classroom cr, Teacher t){
    this.cr = cr;
    this.t = t;
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
    this.setSize(900, 1000);
    this.setResizable(false);
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

        JButton edit = new JButton("Edit A"+(i+1));
        edit.setBounds(100, 140*i + 290 , 120 , 30);
        JButton del = new JButton("Del Q"+(i+1));
        del.setBounds(400, 140*i + 290 , 120 , 30);
        del.setVisible(true);
        JButton submit = new JButton("Submit A"+(i+1));
        submit.setBounds(250, 140*i + 290 , 120 , 30);
        submit.setVisible(false);

        edit.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent event){
            a.setEditable(true);
            a.setForeground(Color.RED);
            submit.setVisible(true);
          }
        });
        submit.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent event){
            t.editQ(event.getActionCommand(), a.getText());
            a.setEditable(false);
            a.setForeground(Color.BLACK);
            submit.setVisible(false);
          }
        });
        del.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent event){
            t.delQ(event.getActionCommand());
            QaA q = new QaA(cr, t);
            q.frame();
            dispose();
          }
        });

        this.add(edit);
        this.add(del);
        this.add(submit);

        i++;

      }
    }

  }
}//-------------------------------------------------------
/*JButton btnNewButton = new JButton("Activity!");
btnNewButton.setBounds(134, 142, 274, 77);
btnNewButton.addActionListener(new ActionListener(){

public void actionPerformed(ActionEvent e) {                
    clicked++;
    String x=Integer.toString(clicked);
    textArea.setText(x);                                            
    }       
});*/
//---------------------------------------------------------------

class SignUp extends JFrame{
  Classroom cr;
  Teacher t;
  SignUp(Classroom cr, Teacher t){
    this.cr = cr;
    this.t = t;
  }

  public void frame(){
    this.setSize(450, 500);
      this.setLayout(null);
      this.setVisible(true);

      try
      {
        BufferedImage bf = ImageIO.read(new File(System.getProperty("user.dir") + "/Assets/background1.jpg"));
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
      ln.setFont(new Font("Marlett", Font.BOLD, 14));
      ln.setForeground(Color.BLACK);
      JLabel inst = new JLabel("Institution :");
      inst.setFont(new Font("Marlett", Font.BOLD, 14));
      inst.setForeground(Color.BLACK);
      JLabel inst1 = new JLabel("<html>Neil gogte<br/>Institute of technology</html>");
      inst1.setFont(new Font("Marlett", Font.BOLD, 14));
      inst1.setForeground(Color.BLACK);
      JLabel lp = new JLabel("Password :");
      lp.setFont(new Font("Marlett", Font.BOLD, 14));
      lp.setForeground(Color.BLACK);

      JTextField name = new JTextField("");
      JTextField pass = new JTextField("");
      JLabel head = new JLabel("New Account");
      head.setFont(new Font("Marlett", Font.BOLD, 19));
      head.setForeground(Color.BLACK);
      String classes[]={" ","BTech II yr"};
      JComboBox<String> cb=new JComboBox<>(classes);

      head.setBounds(150, 20, 150, 60);
      ln.setBounds(50, 100, 100, 40);
      name.setBounds(180, 100, 150, 35);
      inst.setBounds(50, 170, 100, 40);
      inst1.setBounds(180, 170, 150, 40);
      lp.setBounds(50, 240, 100, 40);
      pass.setBounds(180, 240, 150, 35);

      submit.setBounds(110, 350, 100, 40);
      back.setBounds(220, 350, 100, 40);
    submit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        if(!name.getText().equals("") && !pass.getText().equals("") && pass.getText().length() < 32){
          JOptionPane.showMessageDialog(null, "Submitted request to the Admnistrator");
          t.re(name.getText(), pass.getText());
          dispose();
        }
        else{
          JOptionPane.showMessageDialog(null, "Please fill all the Details Correctly");
        }
      }
    });
    back.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){

        dispose();
      }
    });

    this.add(submit);
    this.add(back);
    this.add(head);
    this.add(ln);
    this.add(inst);
    this.add(inst1);
    this.add(lp);
    this.add(name);
    this.add(pass);
  }
}
//------------------------------------------------------
