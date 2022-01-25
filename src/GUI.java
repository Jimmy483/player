import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class GUI  {
JPanel leftPanel;
JPanel rightPanel;
DrawFrame dr;
final String fileName="C://users//Desktop//Songs.txt";
String line="";
ArrayList<ArrayList<String>> songList=new ArrayList<ArrayList<String>>();
    public static void main(String[] args) {
        new GUI();




    }

    public GUI(){
        dr=new DrawFrame();
        //panel=new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        leftPanel=new DrawLeftPanel();
        rightPanel=new DrawRightPanel();
        dr.add(leftPanel);
        dr.add(rightPanel);
        populateSongList();

    }

    public void populateSongList(){
        try{
            BufferedReader reader=new BufferedReader(new FileReader(fileName));
            while ((line= reader.readLine())!=null){
                String[] songs=line.split(",");
                songList.add(new ArrayList<String >(Arrays.asList(songs[0],songs[1])));
                System.out.println(songList);


            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
class DrawLeftPanel extends JPanel{
    public DrawLeftPanel(){
        setBounds(0,0,200,500);
        setBackground(new Color(87,99,99));
    }
}

class DrawRightPanel extends JPanel{
    public DrawRightPanel(){
        setBackground(new Color(56,148,148));
        setBounds(60,0,600,500);
    }
}
class DrawFrame extends JFrame{

    public DrawFrame(){
        setVisible(true);
        setTitle("Gmi's Music Player");
        setSize(800,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



    }


}
