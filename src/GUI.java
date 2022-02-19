import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.net.MalformedURLException;
import java.net.URL;
//import javazoom.jlgui.basicplayer.BasicPlayer;
//import javazoom.jlgui.basicplayer.BasicPlayerException;


public class GUI implements MouseListener {
JPanel leftPanel;
JPanel rightPanel;

JLabel labelFile;
JLabel labelSetting;
DrawPlayBar dre;
DrawFrame dr;
DrawMusicBar drM;
//JList<String> list;
List<String> myList;
DefaultListModel def;
JList<String> list;
ArrayList<ArrayList<String>> jpt;
JLabel labelPlay;
ImageIcon img;
Thread playThread;
Thread pauseThread;
Thread resumeThread;
Thread progressThread;
Player player;
FileInputStream fileInputStream;
long fileLength;
long pause;
long totalLength;
File myFile;
String musicFilename;
String musicFilePath;
    String clickedd;
JFileChooser jfc;
    JProgressBar progressBar;
BufferedInputStream bufferedInputStream;
final String fileName="C:\\Users\\Gmi Bro\\OneDrive\\Desktop\\songs.txt";
String line="";
JLabel labelMusic;
ArrayList<ArrayList<String>> songList=new ArrayList<ArrayList<String>>();
    public static void main(String[] args) {
        new GUI();




    }

    public GUI(){
        dr=new DrawFrame();
        //panel=new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
       // menuPanel=new DrawMenu();

        dre=new DrawPlayBar();
        drM=new DrawMusicBar();
        leftPanel=new DrawLeftPanel();
        rightPanel=new DrawRightPanel();
        dr.add(dre);
        dr.add(drM);
        //dr.add(menuPanel);
        dr.add(leftPanel);
        dr.add(rightPanel);
        createPlay();
        populateSongList();
        createMenu();
        createMusicBar();
        progressThread=new Thread(progressRunnable);
        playThread=new Thread(playRunnable);
        //checkThreadStatus(playThread);
        pauseThread=new Thread(pauseRunnable);
        resumeThread=new Thread(resumeRunnable);
        progressThread=new Thread(progressRunnable);
    }

    public void checkThreadStatus(Thread playThread){

        img=new ImageIcon("realplay.png");
        ImageIcon imageIcon=new ImageIcon(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
        labelPlay.setIcon(imageIcon);
        player.close();
        playThread.interrupt();
//        labelMusic.setText("File");

    }

    public void createMusicBar(){
        progressBar=new JProgressBar();
        progressBar.setBounds(300,20,200,10);
        progressBar.setForeground(Color.blue);
        progressBar.setVisible(true);
        progressBar.setStringPainted(true);
//        progressBar.setValue(12);

        drM.add(progressBar);
        labelMusic=new JLabel("File");
        labelMusic.setForeground(Color.white);
        //labelMusic.scrollRectToVisible(labelMusic.getBounds());
        labelMusic.setBounds(370,2,400,20);
        drM.add(labelMusic);
    }

    public void createPlay(){
        ImageIcon imageIcon = null;
        JLabel labelPrevious=new JLabel("Previous Button");
        //labelPrevious.setBounds(200,20,30,30);
        ImageIcon imgPrevious=new ImageIcon("realprevious.png");
        imageIcon=new ImageIcon(imgPrevious.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
        labelPrevious.setIcon(imageIcon);
        labelPrevious.setText("");
        dre.add(labelPrevious);

        labelPlay=new JLabel("play button");
        //labelPlay.setBounds(200,20,30,30);
//        ImageIcon icon=new ImageIcon("play.png");
//        labelPlay.setIcon(icon.getImageIcon());
//        BufferedImage img=null;
//        try{
//            img= ImageIO.read(new File("play.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Image dimg=img.getScaledInstance(labelPlay.getWidth(),labelPlay.getHeight(),Image.SCALE_SMOOTH);

        img=new ImageIcon("realplay.png");
        imageIcon=new ImageIcon(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
        labelPlay.setIcon(imageIcon);
        labelPlay.setText("");
        labelPlay.addMouseListener(this);
        dre.add(labelPlay);

        JLabel labelNext=new JLabel("Next Button");
        //labelNext.setBounds(200,20,30,30);
        ImageIcon imgNext=new ImageIcon("realnext.png");
        imageIcon=new ImageIcon(imgNext.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
        labelNext.setIcon(imageIcon);
        labelNext.setText("");
        dre.add(labelNext);



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
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfc=new JFileChooser();
                JFrame jf=new JFrame();
                int agree=jfc.showOpenDialog(jf.add(jfc));
                int position=myList.size();
                jfc.setFileFilter(new FileNameExtensionFilter("Mp3 files","mp3"));
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if(agree==JFileChooser.APPROVE_OPTION){
                    pause=0;
                    File file= jfc.getSelectedFile();
                    String text=file.getPath().toString();
                    myFile=jfc.getSelectedFile();
                    musicFilename=jfc.getSelectedFile().getName();
                    musicFilePath=jfc.getSelectedFile().getPath();
                    try {
                        BufferedWriter bfW=new BufferedWriter(new FileWriter("C:\\Users\\Gmi Bro\\OneDrive\\Desktop\\songs.txt"));
//                        BufferedReader bfR=new BufferedReader(new FileReader("C:\\Users\\Gmi Bro\\OneDrive\\Desktop\\songs.txt"));
//                        String line;
//                        while ((line=bfR.readLine())!=null){
//                            String[] songs=line.split(",");
//
//                        }
                        for(int i=0;i<songList.size();i++){
                            bfW.write(songList.get(i).get(0)+"," + songList.get(i).get(1) + "\n");

                        }
                        bfW.write(musicFilename+", " +musicFilePath);
                        bfW.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    String[] txt;
                    txt=new String[]{text};
                    myList.add(text);
                    def.add(position,text);
//                    DefaultListModel df=new DefaultListModel();
//                    df.add(0,text);
//                    list.add(());
//                    list=new JList(df);
//                    list.setVisibleRowCount(jpt.size());
//                    list.addListSelectionListener(new ListSelectionListener() {
//                        @Override
//                        public void valueChanged(ListSelectionEvent e) {
//
//                        }
//                    });
//                    list.setBounds(10,20,180,(jpt.size()+1)*19);
//                    list.setBackground(Color.BLACK);
//                    list.setForeground(Color.white);
                    //leftPanel.add(list);

                }
            }
        });
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
        def=new DefaultListModel();
        for(int i=0;i< arr.size();i++){
            def.add(i,arr.get(i).get(1));

        }
        myList = new ArrayList<String>(10);
        for(int index = 0; index < arr.size();index++) {
            myList.add(arr.get(index).get(1));
        }

        list = new JList(def);
        JScrollPane scrollPane = new JScrollPane();
        //scrollPane.setPreferredSize(new Dimension(50,100));

        //scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(0,0,0));
        //scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
        scrollPane.getViewport().setForeground(new Color(255,255,255));

//        scrollPane.setBackground(new Color(0,0,0));
//        scrollPane.setForeground(new Color(255,255,255));
        scrollPane.setViewportView(list);
        scrollPane.setBounds(0,0,200,320);
        list.setLayoutOrientation(JList.VERTICAL);
        leftPanel.add(scrollPane);
        //rightPanel.setBounds(20,40,180,500);
        //leftPanel.add(list);

        jpt=arr;

    }




    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==labelPlay){
            if(img.getDescription()=="realplay.png"){
                if(pause>0){
                    resumeThread.start();
                }
                else{
                    playThread.start();
                }

            }
            else if(img.getDescription()=="realpause.png"){
//             if(pauseThread.isAlive())
//             {
//                 //new Thread(pauseRunnable).start();
//                 pauseThread=new Thread(pauseRunnable);
//                 pauseThread.start();
             //}
             pauseThread.start();
            }
        }
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

    Runnable progressRunnable=new Runnable() {
        @Override
        public void run() {

            fileLength=clickedd.length();
            double prog=0.52;
            int n=1;
            while (!player.isComplete()){
                prog=prog+0.52;
                if(n%2==0){
                    progressBar.setValue((int)prog);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                n++;
            }

        }
    };

    Runnable playRunnable=new Runnable() {
        @Override
        public void run() {
            clickedd=list.getSelectedValue().toString();
            //fileLength=clickedd.length();
            //progressBar.setValue();
            System.out.println(clickedd);

            if(img.getDescription().toString()=="realplay.png"){
                img=new ImageIcon("realpause.png");
                ImageIcon imageIcon=new ImageIcon(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
                labelPlay.setIcon(imageIcon);
            }
            else if(img.getDescription().toString()=="realpause.png"){
                img=new ImageIcon("realplay.png");
                ImageIcon imageIcon=new ImageIcon(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
                labelPlay.setIcon(imageIcon);
                return;
            }

            ////here play code
            try {
                labelMusic.setText(clickedd);
                fileInputStream=new FileInputStream(clickedd);
                bufferedInputStream=new BufferedInputStream(fileInputStream);
                player=new Player(bufferedInputStream);
                totalLength=fileInputStream.available();
                player.play();//starting music
                if(player.isComplete()){
                    checkThreadStatus(Thread.currentThread());
                }



            }catch (IOException | JavaLayerException ad){
                ad.printStackTrace();
            }
        }
    };

    Runnable pauseRunnable=new Runnable() {
        @Override
        public void run() {

            if(img.getDescription().toString()=="realplay.png"){
                img=new ImageIcon("realpause.png");
                ImageIcon imageIcon=new ImageIcon(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
                labelPlay.setIcon(imageIcon);
            }
            else if(img.getDescription().toString()=="realpause.png"){
                img=new ImageIcon("realplay.png");
                ImageIcon imageIcon=new ImageIcon(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
                labelPlay.setIcon(imageIcon);
            }
            try {
                pause=fileInputStream.available();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.close();
        }
    };

    Runnable resumeRunnable=new Runnable() {
        @Override
        public void run() {
            if(img.getDescription().toString()=="realplay.png"){
                img=new ImageIcon("realpause.png");
                ImageIcon imageIcon=new ImageIcon(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
                labelPlay.setIcon(imageIcon);
            }
            else if(img.getDescription().toString()=="realpause.png"){
                img=new ImageIcon("realplay.png");
                ImageIcon imageIcon=new ImageIcon(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
                labelPlay.setIcon(imageIcon);
            }

            try {
                fileInputStream=new FileInputStream(myFile);
                bufferedInputStream=new BufferedInputStream(fileInputStream);
                player=new Player(bufferedInputStream);
                fileInputStream.skip(totalLength-pause);
                player.play();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
class DrawMusicBar extends JPanel{
    public DrawMusicBar(){
        setBounds(0,0,800,40);
        setBackground(new Color(64, 61, 54));
        setLayout(null);
    }
}
class DrawPlayBar extends JPanel{
    public DrawPlayBar(){
        setBounds(0,40,800,60);
        setBackground(new Color(64, 61, 54));

       // setLayout(null);

    }
}

class DrawLeftPanel extends JPanel{
    public DrawLeftPanel(){
        //new BorderLayout();
        setBounds(0,100,200,400);
        setBackground(new Color(87,99,99));
        setLayout(null);
    }


}

class DrawRightPanel extends JPanel{
    public DrawRightPanel(){
        setLayout(null);
        setBackground(new Color(150, 134, 150));
        setBounds(200,100,600,400);
    }
}
class DrawFrame extends JFrame{

    public DrawFrame(){
        setVisible(true);
        setTitle("Gmi's Music Player");
        setSize(800,500);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



    }


}
