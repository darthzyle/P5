import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * A client that connects to a {@code PowerServer} instance.
 */
public final class Client implements Runnable {
    private ArrayList<String> emailList = new ArrayList<String>();
    private ArrayList<String> passwordList = new ArrayList<String>();
    private JLabel welcome;

    public static Database database;

    private static JLabel hostNameLabel;             //FOR SERVER & CLIENT
    private static JTextField hostNameText;          //FOR SERVER & CLIENT

    private static JLabel portLabel;             //FOR SERVER & CLIENT
    private static JTextField portText;          //FOR SERVER & CLIENT
    JButton enterButton;                          //FOR SERVER & CLIENT


    private static JLabel nameLabel;                 //FOR REGISTER
    private static JTextField nameText;              //FOR REGISTER
    private static JLabel emailLabel;                //FOR REGISTER
    private static JTextField emailText;             //FOR REGISTER
    private static JLabel genderLabel;               //FOR REGISTER
    private static JTextField genderText;            //FOR REGISTER
    private static JLabel birthdayLabel;             //FOR REGISTER
    private static JTextField birthdayText;          //FOR REGISTER
    private static JLabel newPasswordLabel;          //FOR REGISTER
    private static JPasswordField newPasswordText;   //FOR REGISTER
    private static JLabel confirmPasswordLabel;      //FOR REGISTER
    private static JTextField confirmPasswordText;   //FOR REGISTER


    private static JLabel logInEmail;                // FOR LOGIN
    private static JTextField logInEmailText;        // FOR LOGIN
    private static JLabel logInPasswordLabel;             // FOR LOGIN
    private static JPasswordField logInPasswordText;      // FOR LOGIN


    JButton signUpButton;
    JButton loginButton;
    JButton registerButton;

    private String email;
    private String gender;
    private String birthday;
    private String name;

    int count = 0;
    String hostName;
    String portString;    //port number in String
    int port;

    private JLabel accountSetting;
    JButton deleteAccountButton;
    JButton editAccountButton;

    private JLabel profileSetting; // do things related to profile



    private String profileId;
    private String contactInfo;
    private ArrayList<String> interestsList;
    private ArrayList<String> friendList;
    private String aboutMe;
    private String editName;
    //Profile profile;
    //User profileOwner;

    private static JLabel profileIdLabel;
    private static JTextField profileIdText;
    private static JLabel contactInfoLabel;
    private static JTextField contactInfoText;
    private static JLabel aboutMeLabel;
    private static JTextField aboutMeText;
    private static JLabel interestLabel;
    private static JTextField interestText;
    private static JLabel editEmailLabel;
    private static JTextField editEmailText;
    private static JLabel friendListLabel;
    private static JTextField friendListText;

    JButton createProfileButton;
    JButton friendsListButton;

    private Socket socket;



    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private OutputStream outputStream;









    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Client());

    } //main

    @Override
    public void run()  {
        BufferedReader inputReader;
        //String hostName;

        //int port;               //port number in int
        String request;
        String response;

        JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        JPanel panel = new JPanel();
        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
        content.add(panel, BorderLayout.CENTER);

        hostNameLabel = new JLabel("Server's name");
        hostNameLabel.setBounds(10, 20, 110, 25);
        panel.add(hostNameLabel);

        hostNameText = new JTextField(20); //length of text field
        hostNameText.setBounds(100, 20, 165, 25);
        panel.add(hostNameText);

        portLabel = new JLabel("Server's port");
        portLabel.setBounds(10, 50, 110, 25);
        panel.add(portLabel);

        portText = new JTextField(20); //length of text field
        portText.setBounds(100, 50, 165, 25);
        panel.add(portText);

        enterButton = new JButton("Enter");
        enterButton.setBounds(120, 80, 80, 25);
        panel.add(enterButton);
        enterButton.addActionListener(actionListener);

    }

    ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == enterButton ) {
                // inputReader = new BufferedReader(new InputStreamReader(System.in));
                //System.out.print("Enter the server's host name: ");

                hostName = hostNameText.getText();

                if (hostName == null) {
                    return;
                } //end if

                //System.out.print("Enter the server's port: ");


                portString = portText.getText();

                if (portString == null) {
                    return;
                } //end if

                try {
                    port = Integer.parseInt(portString);   //convert portString to int
                } catch (NumberFormatException g) {
                    System.out.println();
                    System.out.println("Error: Invalid port!");
                    return;
                } //end try catch **/


                try {
                    socket = new Socket(hostName, port);   //Connected to the server

                } catch (IOException h) {
                    JOptionPane.showMessageDialog(null, "Connection cannot be established",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JFrame frame = new JFrame();
                Container content = frame.getContentPane();
                JPanel panel = new JPanel();
                frame.setSize(350, 200);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.add(panel);
                panel.setLayout(null);

                welcome = new JLabel("Welcome to PurdueBook");
                welcome.setBounds(10, 0, 150, 25);
                content.add(panel, BorderLayout.CENTER);
                panel.add(welcome);

                logInEmail = new JLabel("Email Address");
                logInEmail.setBounds(10, 30, 110, 25);
                content.add(panel, BorderLayout.CENTER);
                panel.add(logInEmail);

                logInEmailText = new JTextField(20); // length of text field
                logInEmailText.setBounds(100, 30, 165, 25);
                panel.add(logInEmailText);

                loginButton = new JButton("Login");
                loginButton.setBounds(10, 70, 80, 25);
                panel.add(loginButton);
                loginButton.addActionListener(actionListener);

                signUpButton = new JButton("Sign up");
                signUpButton.setBounds(120, 70, 80, 25);
                panel.add(signUpButton);
                signUpButton.addActionListener(actionListener);
            }

            if (e.getSource() == signUpButton) {
                JFrame frame = new JFrame("New User");
                Container content = frame.getContentPane();
                JPanel panel = new JPanel();
                frame.setSize(400, 300);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.add(panel);
                panel.setLayout(null);

                // attributes
                nameLabel = new JLabel("Name");
                nameLabel.setBounds(10, 20, 175, 25);
                content.add(panel, BorderLayout.CENTER);
                panel.add(nameLabel);

                nameText = new JTextField(20);
                name = nameText.getText();
                nameText.setBounds(200, 20, 165, 25);
                panel.add(nameText);

                emailLabel = new JLabel("Email (eg. pete@purdue.com)");
                emailLabel.setBounds(10, 50, 175, 25);
                panel.add(emailLabel);

                emailText = new JTextField(20); /** Condition to be set to confirm the validity of the user's email **/
                emailText.setBounds(200, 50, 165, 25);
                email = emailText.getText();
                panel.add(emailText);
                // System.out.println(email); WHy email null?

                genderLabel = new JLabel("Gender (M/F)");
                genderLabel.setBounds(10, 80, 175, 25);
                panel.add(genderLabel);

                genderText = new JTextField(20);
                gender = genderText.getText();
                genderText.setBounds(200, 80, 165, 25);
                panel.add(genderText);

                birthdayLabel = new JLabel("Birthday (dd/mm/yyyy)");
                birthdayLabel.setBounds(10, 110, 175, 25);
                panel.add(birthdayLabel);

                birthdayText = new JTextField(20);
                birthday = birthdayText.getText();
                birthdayText.setBounds(200, 110, 165, 25);
                panel.add(birthdayText);

                registerButton = new JButton("Register");
                registerButton.setBounds(10, 200, 100, 25);
                panel.add(registerButton);

                registerButton.addActionListener(actionListener);


            }

            if (e.getSource() == registerButton) {

                if (nameText.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Name cannot be empty!", "PurdueBook",
                            JOptionPane.ERROR_MESSAGE);

                } else if (emailText.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "email cannot be empty!", "PurdueBook",
                            JOptionPane.ERROR_MESSAGE);

                } else if (genderText.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Gender cannot be empty!", "PurdueBook",
                            JOptionPane.ERROR_MESSAGE);

                } else if (birthdayText.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Birthday cannot be empty!", "PurdueBook",
                            JOptionPane.ERROR_MESSAGE);

                }

                else {
                    //SERVER
                    User accountRegistered = new User(nameText.getText(), emailText.getText(), genderText.getText(),
                            birthdayText.getText());


                    try {
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(accountRegistered);
                        objectOutputStream.flush();
                        JOptionPane.showMessageDialog(null,"Sent to the server: "
                                + accountRegistered.toString());

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }



                    email = emailText.getText(); //MAYBE DON'T NEED IT

                    //  database.addObject(accountRegister);
                   // JOptionPane.showMessageDialog(null, "Registered Successfully");

                    JFrame frame = new JFrame();
                    Container content = frame.getContentPane();
                    JPanel panel = new JPanel();
                    frame.setSize(350, 300);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    frame.add(panel);
                    panel.setLayout(null);
                    content.add(panel, BorderLayout.CENTER);

                    confirmPasswordLabel = new JLabel("Registered Successfully, click the button to log in");
                    confirmPasswordLabel.setBounds(40, 80, 300, 25);
                    panel.add(confirmPasswordLabel);


                    enterButton = new JButton("Login");
                    enterButton.setBounds(10, 200, 100, 25);
                    panel.add(enterButton);
                    enterButton.addActionListener(actionListener);



                }
            }

            if (e.getSource() == loginButton) { //after sign up


                if (database.findObject(email) != null) {
                    count++;
                }

                //need server

                //   private static JTextField logInEmailText;        // FOR LOGIN

                //WE HAVE TO ACCESS THE DATABASE TO FIND THE logInEmailText, IF DATA IS FOUND COUNT++


                /**  for (int i = 0; i < emailList.size(); i++) {
                    if (logInEmailText.getText().equals(emailList.get(i))) {
                        count++;
                    }
                }

                for (int j = 0; j < passwordList.size(); j++) {
                    if (logInPasswordText.getText().equals(passwordList.get(j))) {
                        count++;
                    }
                }

                //JOptionPane.showMessageDialog(null, count);

                if (count == 2 ) {
                    JOptionPane.showMessageDialog(null, "Login Successfully");
                    //To be continued...

                } **/

                if (count == 1) {
                    JOptionPane.showMessageDialog(null, "Login Successfully");

                    JFrame frame = new JFrame();
                    Container content = frame.getContentPane();
                    JPanel panel = new JPanel();
                    frame.setSize(350, 300);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    frame.add(panel);
                    panel.setLayout(null);

                    accountSetting = new JLabel("Account Settings");
                    accountSetting.setBounds(10, 10, 120, 25);
                    content.add(panel, BorderLayout.CENTER);
                    panel.add(accountSetting);

                    deleteAccountButton = new JButton("Delete Account");
                    deleteAccountButton.setBounds(10, 40, 120, 25);
                    panel.add(deleteAccountButton);
                    deleteAccountButton.addActionListener(actionListener);

                    editAccountButton = new JButton("Edit Account");
                    editAccountButton.setBounds(10, 70, 120, 25);
                    panel.add(editAccountButton);
                    editAccountButton.addActionListener(actionListener);

                    profileSetting = new JLabel("Profile Settings");
                    profileSetting.setBounds(10, 100, 120, 25);
                    content.add(panel, BorderLayout.CENTER);
                    panel.add(profileSetting);

                    // profile
                } else if (count == 0) {
                    JOptionPane.showMessageDialog(null, "Specified account does not exist");

                }

            }

            if (e.getSource() == editAccountButton) {
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

                nameLabel = new JLabel("Name");
                nameLabel.setBounds(10, 50, 120, 25);
                panel.add(nameLabel);

                nameText = new JTextField(10);
                nameText.setBounds(100, 50, 120, 25);
                panel.add(nameText);

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
                createProfileButton.addActionListener(actionListener);

                friendsListButton = new JButton("View Friends");
                friendsListButton.setBounds(150, 230, 130, 25);
                panel.add(friendsListButton);
                friendsListButton.addActionListener(actionListener);

               profileId = profileIdText.getText();
               contactInfo = contactInfoText.getText();
               aboutMe = aboutMeText.getText();
               editName = nameText.getText();

                String[] interests = interestText.getText().split(", ");

                Collections.addAll(interestsList, interests);

                String[] friends = friendListText.getText().split(", ");

                Collections.addAll(friendList, friends);

            }

            if (e.getSource() == createProfileButton) {

                    //SERVER
                Profile profile;
                profile = new Profile(email, contactInfo, interestsList,friendList, aboutMe);

                //sending profile to the server
                try {

                    outputStream = socket.getOutputStream();
                    objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(profile);
                    objectOutputStream.flush();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Profile Created!");

                //DISPLAY PROFILE

                JFrame frame = new JFrame();
                Container content = frame.getContentPane();
                JPanel panel = new JPanel();
                frame.setSize(350, 300);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.add(panel);
                panel.setLayout(null);

                JLabel displayName = new JLabel("Name: " + editName);
                displayName.setBounds(10, 20, 350,25);
                panel.add(displayName);

                JLabel displayContactInfo = new JLabel("Contact info: " + contactInfo);
                displayName.setBounds(10, 50, 350,25);
                panel.add(displayName);

                JLabel displayInterest = new JLabel("Interests: " + interestsList);
                displayName.setBounds(10, 80, 350,25);
                panel.add(displayName);

                JLabel displayFriendsList  = new JLabel("Friends: " + friendList);
                displayName.setBounds(10, 110, 350,25);
                panel.add(displayName);

                JLabel displayAboutMe  = new JLabel("About me: " + aboutMe);
                displayName.setBounds(10, 140, 350,25);
                panel.add(displayName);

            }

            if (e.getSource() == friendsListButton) {
                for (int i = 0; i < friendList.size(); i++) {
                    JFrame frame = new JFrame();
                    Container content = frame.getContentPane();
                    JPanel panel = new JPanel();
                    frame.setSize(350, 300);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    frame.add(panel);
                    panel.setLayout(null);

                    JLabel allFriends = new JLabel("Friends List: " + friendList);
                    allFriends.setBounds(10, 20, 350,25);
                    panel.add(allFriends);

                    //TO BE CONTINUED





                    /**Users can create a friends list of other users of the application.
                    To be friends, both users must have an account, and both must confirm to be friends with one another.
                            Users can see a list of their sent and received friend requests. They can rescind friend requests or confirm them.
                    If a friend request is rescinded, it will no longer appear for the recipient or the sender.
                            If a friend request is confirmed, it will no longer appear for the recipient or the sender.
                     Both users will be added to each other's friend list.
                    Users can view a list of all the application's users and send any given individual a friend request or view their profile. **/



                }


            }




        }
    };
}

