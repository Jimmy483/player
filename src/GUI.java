import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        createMenu();
        dre=new DrawPlayBar();
        leftPanel=new DrawLeftPanel();
        rightPanel=new DrawRightPanel();
        dr.add(dre);
        //dr.add(menuPanel);
        dr.add(leftPanel);
        dr.add(rightPanel);
        populateSongList();


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
        drawMenuTools();
    }

    public void populateSongList(){
        try{
            BufferedReader reader=new BufferedReader(new FileReader(fileName));
            while ((line= reader.readLine())!=null){
                String[] songs=line.split(",");
                songList.add(new ArrayList<String >(Arrays.asList(songs[0],songs[1])));
                System.out.println(songList);
//create label and set song names TBC

            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        createSongLabels(songList);

    }

    public void drawMenuTools(){
        labelFile=new JLabel();
        labelFile.setText("File");
        labelFile.setBounds(0,0,40,18);
        labelFile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
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
        JButton jButton=new JButton();
        jButton.setText("Cick");
        leftPanel.add(jButton);
        JLabel label;
       /// int i=0;
        int boundMulti=1;

        for(ArrayList<String> list:arr){

            System.out.println(list);
            label=new JLabel(list.get(1));
            label.setBounds(0,boundMulti*20,200,20);
            boundMulti++;
            leftPanel.add(label);
            //leftPanel.add(label);


        }
    }


}
class DrawPlayBar extends JPanel{
    public DrawPlayBar(){
        setBounds(0,0,800,20);
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
