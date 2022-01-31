import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class GUI {
JPanel leftPanel;
JPanel rightPanel;

JLabel labelFile;
JLabel labelSetting;
DrawPlayBar dre;
DrawFrame dr;
final String fileName="C:\\Users\\Gmi Bro\\OneDrive\\Desktop\\songs.txt";
String line="";
ArrayList<ArrayList<String>> songList=new ArrayList<ArrayList<String>>();
    public static void main(String[] args) {
        new GUI();




    }

    public GUI(){
        dr=new DrawFrame();
        //panel=new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
       // menuPanel=new DrawMenu();
        //createMenu();
        dre=new DrawPlayBar();

        leftPanel=new DrawLeftPanel();
        rightPanel=new DrawRightPanel();
        dr.add(dre);
        //dr.add(menuPanel);
        dr.add(leftPanel);
        dr.add(rightPanel);
        //createPlay();
        populateSongList();


    }

    public void createPlay(){
        JLabel labelPlay=new JLabel("play button");
        labelPlay.setBounds(50,30,30,30);
//        ImageIcon icon=new ImageIcon("play.png");
//        labelPlay.setIcon(icon.getImageIcon());
//        BufferedImage img=null;
//        try{
//            img= ImageIO.read(new File("play.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Image dimg=img.getScaledInstance(labelPlay.getWidth(),labelPlay.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon imageIcon = null;
        ImageIcon img=new ImageIcon("redplay.png");
        imageIcon=new ImageIcon(img.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
        labelPlay.setIcon(imageIcon);
        labelPlay.setText("");
        dre.add(labelPlay);

    }
    public void createMenu(){
        JMenuBar jMenuBar=new JMenuBar();
        UIManager.put("MenuBar.background",Color.BLACK);
        JMenu fileMenu=new JMenu("File");
        fileMenu.setForeground(Color.white);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        jMenuBar.add(fileMenu);
        JMenuItem menuItem1=new JMenuItem("Open",KeyEvent.VK_O);
        fileMenu.add(menuItem1);
        dr.setJMenuBar(jMenuBar);
        //drawMenuTools();
    }

    public void populateSongList(){
        try{
            BufferedReader reader=new BufferedReader(new FileReader(fileName));
            while ((line= reader.readLine())!=null){
                String[] songs=line.split(",");
                songList.add(new ArrayList<String >(Arrays.asList(songs[0],songs[1])));
                //System.out.println(songList);
//create label and set song names TBC

            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



//        songList.add(new ArrayList<String>(Arrays.asList("Adam Levine","Sugar")));
//        songList.add(new ArrayList<String>(Arrays.asList("Naruto","Opening1")));
//        songList.add(new ArrayList<String>(Arrays.asList("GNR","Sweet Child O mine")));
        createSongLabels(songList);

    }

    public void drawMenuTools(){
        labelFile=new JLabel();
        labelFile.setText("File");
        labelFile.setBounds(0,0,40,18);
        labelFile.addMouseListener(new ClickMouse(3));
        //labelFile.setForeground(Color.YELLOW);
//        menuPanel.add(labelFile);
//
//        labelSetting=new JLabel();
//        labelSetting.setText("Setting");
//        labelSetting.setBounds(25,0,60,20);
//        //labelSetting.setForeground(Color.blue);
//        labelSetting.setBackground(Color.GREEN);
//        menuPanel.add(labelSetting);
    }

    public void createSongLabels(ArrayList<ArrayList<String>> arr){
//        JButton jButton=new JButton();
//        jButton.setText("Cick");
//        leftPanel.add(jButton);
//        JLabel label;
//       /// int i=0;
//        int boundMulti=1;

//        for(ArrayList<String> list:arr){
//
//            System.out.println(list);
//            label=new JLabel(list.get(1));
//            label.setBounds(0,boundMulti*20,200,20);
//
//            boundMulti++;
//            leftPanel.add(label);
//            //leftPanel.add(label);
//
//
//        }
        DefaultListModel def=new DefaultListModel();
        for(int i=0;i< arr.size();i++){
            def.add(i,arr.get(i).get(1));

        }
        JList list=new JList(def);
        list.setVisibleRowCount(arr.size());
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        list.setBounds(10,40,180,arr.size()*19);
        list.setBackground(Color.BLACK);
        list.setForeground(Color.white);
        leftPanel.add(list);

    }


}
class DrawPlayBar extends JPanel{
    public DrawPlayBar(){
        setBounds(0,0,800,60);
        setBackground(new Color(64, 61, 54));
        //setLayout(null);

    }
}

class DrawLeftPanel extends JPanel{
    public DrawLeftPanel(){
        setBounds(0,21,200,500);
        setBackground(new Color(87,99,99));
        setLayout(null);
    }


}

class DrawRightPanel extends JPanel{
    public DrawRightPanel(){
        setBackground(new Color(56,148,148));
        setBounds(60,21,600,500);
    }
}
class DrawFrame extends JFrame{

    public DrawFrame(){
        setVisible(true);
        setTitle("Gmi's Music Player");
        setSize(800,500);
        //setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



    }


}
