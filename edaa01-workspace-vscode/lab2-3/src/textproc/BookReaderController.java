package textproc;

import java.awt.BorderLayout;
import java.awt.Container;

import java.util.Map;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BookReaderController {
   public BookReaderController(GeneralWordCounter counter) {
      SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
   }

   private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
      JFrame frame = new JFrame(title);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container pane = frame.getContentPane();
      // pane är en behållarkomponent till vilken de övriga komponenterna 
      // (listvy, knappar etc.) ska läggas till.
      frame.setSize(width, height);
      frame.setVisible(true);
      frame.pack();
      frame.setVisible(true);

      List<Map.Entry<String, Integer>> wordList = counter.getWordList();
      wordList.removeIf((j) -> Character.isDigit(j.getKey().charAt(0)));
      //JList<Map.Entry<String, Integer>> swingList = new JList<Map.Entry<String, Integer>>(wordList.toArray(Map.Entry[]::new));

      SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<Map.Entry<String, Integer>>(wordList);

    JList<Map.Entry<String, Integer>> swingList =
        new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(swingList);

   JPanel panel = new JPanel();
   JRadioButton alfabet = new JRadioButton("Alphabet");
   JRadioButton frekvens = new JRadioButton("Frequency");

   ButtonGroup group = new ButtonGroup();

   group.add(alfabet);
   group.add(frekvens);

   JTextField textField = new JTextField(20);
   JButton search = new JButton("Find");

   panel.add(alfabet);
   panel.add(frekvens);
   panel.add(textField);
   panel.add(search);

   alfabet.addActionListener((e) -> {
      listModel.sort((j1, j2) -> j1.getKey().compareTo(j2.getKey()));
   });
   frekvens.addActionListener((e) -> {
      listModel.sort((j1, j2) -> j2.getValue() - j1.getValue());
   });
   search.addActionListener((e) -> {
      String text = textField.getText().trim().toLowerCase();
      System.out.println(text);
      int i = 0;
      for(int j = 0; j < wordList.size(); j++) {
         System.out.println(wordList.get(j).getKey());
         if(wordList.get(j).getKey().equals(text)) {
            i = j;
         }
      }
      if(i == 0) {
         JOptionPane.showMessageDialog(null, "Ord finns inte i listan", "alert", 0);

      }
      swingList.setSelectedIndex(i);
      swingList.ensureIndexIsVisible(i);
   });


    // Add the scroll pane to the window
    pane.setLayout(new BorderLayout());

    pane.add(scrollPane, BorderLayout.CENTER);
    pane.add(panel, BorderLayout.SOUTH);

   }
}