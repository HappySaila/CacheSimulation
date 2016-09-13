import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.Map;

/**
 * Created by HappySaila on 2016/09/13.
 */
public class GUI {
    private JFrame frame;
    private JComboBox<String> caches;
    private JComboBox<String> file;

    private int cacheSetup = 0;
    private String fileName = "";

    public GUI(){
        caches = new JComboBox<>();
        file = new JComboBox<>();
        create();
    }

    public void create(){
        frame = new JFrame("Cache Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.setLayout(new BorderLayout());


        JLabel header = new JLabel("Cache Simulator: Choose file to read and select cache setup and press start.", SwingConstants.CENTER);
        frame.add(header, BorderLayout.NORTH);
        JLabel label = new JLabel("Select cache layout:");
        setFont(label);
        frame.add(label, BorderLayout.WEST);

        caches.addItem("Empty");
        caches.addItem("Single: 16 blocks and 16 bytes / block");
        caches.addItem("Single: 16 blocks and 32 bytes / block");
        caches.addItem("Double: L1: 16 blocks and 16 bytes / block; L2: 64 blocks, 64 bytes / block");
        caches.addItem("Double: L1: 8 blocks and 32 bytes / block; L2: 64 blocks, 64 bytes / block");
        caches.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    if (e.getItem().equals("Empty")){
                        cacheSetup = 0;
                    }else if (e.getItem().equals("Single: 16 blocks and 16 bytes / block")){
                        cacheSetup = 1;
                    }else if (e.getItem().equals("Single: 16 blocks and 32 bytes / block")){
                        cacheSetup = 2;
                    }else if (e.getItem().equals("Double: L1: 16 blocks and 16 bytes / block; L2: 64 blocks, 64 bytes / block")){
                        cacheSetup = 3;
                    }else if (e.getItem().equals("Double: L1: 8 blocks and 32 bytes / block; L2: 64 blocks, 64 bytes / block")){
                        cacheSetup = 4;
                    }
                }
            }
        });
        frame.add(caches, BorderLayout.CENTER);

        file.addItem("small_set.addr");
        file.addItem("matmul5x5_loop.addr");
        file.addItem("assignment.addr");
        file.addItem("fourier.addr");
        file.addItem("heapsort.addr");
        fileName=file.getSelectedItem().toString();
        file.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    fileName=file.getSelectedItem().toString();
                }
            }
        });
        frame.add(file, BorderLayout.WEST);

        JButton start = new JButton();
        start.setText("Start!");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                start simulation
                String file = "src/Instructions/"+fileName;
                Cache cache = new Cache(16, 16);
                Cache l2cache = new Cache(64, 32);
                //will create the cpu and call all the instructions
                //all hits, misses and cost will be saved in the cpu object
                CPU cpu = new CPU(cache,l2cache, file);
                System.out.println("l1Hits:"+cpu.l1Hit+"\nl2Hits:"+cpu.l2Hit+"\nMisses:"+cpu.miss);
            }
        });
        frame.add(start, BorderLayout.EAST);

        frame.pack();
    }

    private void setFont(JLabel label){
        Font font = label.getFont();
        Font bold = new Font(font.getFontName(), Font.BOLD, font.getSize());
        label.setFont(bold);
    }
}
