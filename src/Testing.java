import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Testing {


    public static void main(String[] args) {
    /**  JLabel profileIdLabel;
         JTextField profileIdText;
       JLabel contactInfoLabel;
       JTextField contactInfoText;
         JLabel aboutMeLabel;
         JTextField aboutMeText;
         JLabel interestLabel;
         JTextField interestText;

      JLabel editEmailLabel;
      JTextField editEmailText;
         JLabel friendListLabel;
       JTextField friendListText;

        JButton createProfileButton;
        JButton friendsListButton;


        JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        JPanel panel = new JPanel();
        frame.setSize(350, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
        content.add(panel, BorderLayout.CENTER);


        editEmailLabel = new JLabel("Email");
        editEmailLabel.setBounds(10, 20, 120, 25);
        panel.add(editEmailLabel);

        editEmailText = new JTextField(10);
        editEmailText.setBounds(100, 20, 120, 25);
        panel.add(editEmailText);

        interestLabel = new JLabel("Interest");
        interestLabel.setBounds(10, 50, 120, 25);
        panel.add(interestLabel);

        interestText = new JTextField(10);
        interestText.setBounds(100, 50, 120, 25);
        panel.add(interestText);

        profileIdLabel = new JLabel("Profile ID");
        profileIdLabel.setBounds(10, 80, 120, 25);
        panel.add(profileIdLabel);

        profileIdText = new JTextField(20); // length of text field
        profileIdText.setBounds(100, 80, 120, 25);
        panel.add(profileIdText);

        contactInfoLabel = new JLabel("Contact Info: ");
        contactInfoLabel.setBounds(10, 110, 120, 25);
        panel.add(contactInfoLabel);

        contactInfoText = new JTextField(10);
        contactInfoText.setBounds(100, 110, 120, 25);
        panel.add(contactInfoText);

        aboutMeLabel = new JLabel("About me: ");
        aboutMeLabel.setBounds(10, 140, 120, 25);
        panel.add(aboutMeLabel);

        aboutMeText = new JTextField(10);
        aboutMeText.setBounds(100, 140, 120, 25);
        panel.add(aboutMeText);

        interestLabel = new JLabel("Interests: ");
        interestLabel.setBounds(10, 170, 120, 25);
        panel.add(interestLabel);

        interestText = new JTextField(10);
        interestText.setBounds(100, 170, 120, 25);
        panel.add(interestText);

        friendListLabel = new JLabel("Friend List: ");
        friendListLabel.setBounds(10, 200, 120, 25);
        panel.add(friendListLabel);

        friendListText = new JTextField(10);
        friendListText.setBounds(100, 200, 120, 25);
        panel.add(friendListText);


        createProfileButton = new JButton("Create Profile");
        createProfileButton.setBounds(10, 230, 130, 25);
        panel.add(createProfileButton);
        //createProfileButton.addActionListener(actionListener);

        friendsListButton = new JButton("View Friends");
        friendsListButton.setBounds(150, 230, 130, 25);
        panel.add(friendsListButton); **/
        //friendsListButton.addActionListener(actionListener); **/

       /** ArrayList<String> friendList = new ArrayList<String>();
        friendList.add("Ali");
        friendList.add("lol");
        friendList.add("pop");
        friendList.add("gg");
        friendList.add("bibi");
     System.out.println(friendList);

      JFrame frame2 = new JFrame();
      Container content2 = frame2.getContentPane();
      JPanel panel2 = new JPanel();
      frame2.setSize(350, 300);
      frame2.setLocationRelativeTo(null);
      frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame2.setVisible(true);
      frame2.add(panel2);
      panel2.setLayout(null);

      JLabel allFriends = new JLabel("Friends List: " + friendList);
      allFriends.setBounds(10, 20, 350,25);
      panel2.add(allFriends); **/

       String[] a = {"a","b","c"};
        ArrayList<String> passwordList = new ArrayList<String>();

        Collections.addAll(passwordList,a);
        System.out.println(passwordList);









    }
}
