import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class EchoServer implements Runnable {
    Socket socket;
    Database database;

    public EchoServer(Socket socket) {
        this.socket = socket;
    } //to run the thread

    public static void main(String[] args) throws IOException {
        int count = 0;
        ObjectOutputStream oos;  //newly added

        ServerSocket serverSocket = new ServerSocket(4242); //random port number

        while(true) {
            JOptionPane.showMessageDialog(null, "Waiting for the client to connect...",
                    "Status", JOptionPane.INFORMATION_MESSAGE);

            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
                oos = new ObjectOutputStream(clientSocket.getOutputStream());  //newly added
                oos.flush();                                                    //newly added
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            count++;

            JOptionPane.showMessageDialog(null, "Client" + count + " connected!",
                    "Status", JOptionPane.INFORMATION_MESSAGE);   //show

            EchoServer server = new EchoServer(clientSocket);
            new Thread(server).start();
        }
    }

    public void run() {
        InputStream inputStream;
        ObjectInputStream objectInputStream;

        System.out.println("running...");    //for testing

        try {
            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);

            if (equalsProfile(objectInputStream.readObject()) == true) {
                //the object is a profile, add to the database
                System.out.println("it's a profile");    //for testing
                Profile profile = (Profile) objectInputStream.readObject();
                saveProfile(profile);



            } else if (equalsUser(objectInputStream.readObject())  == true) {
                //the object is a user, add to the database
                System.out.println("it's a user");    //for testing
                User user = (User) objectInputStream.readObject();
                saveUser(user);


            } else {
                //the object is a string
                String str = (String) objectInputStream.readObject();
                if (str.indexOf("@") == -1) {
                    //the string is a profile Id
                    removeProfile(str);

                } else if (str.indexOf("@") != -1) {
                    //the object if a email
                    int lastCharacter = str.length();

                   if (str.substring(lastCharacter - 1, lastCharacter).equals("R")) {
                       //the email for removeUser
                       removeUser(str);

                   } else if (str.substring(lastCharacter - 1, lastCharacter).equals("G")) {
                       //the email for getUser
                       getUser(str);
                   }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        

       /** try {
            PrintWriter pw = new PrintWriter(this.socket.getOutputStream());
            Scanner in = new Scanner(this.socket.getInputStream());

            while(in.hasNextLine()) {
                String line = in.nextLine();
                System.out.printf("%s says: %s\n", this.socket, line);
                pw.printf("echo: %s\n", line);
                pw.flush();
            }

            pw.close();
            in.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        } **/

    }

    /* saves the user we got from the client */
    public String saveUser(User user) {
        String add;
        boolean added;

        Objects.requireNonNull(user, "The given user is null");
        added = database.addObject(user);

        if (added) {
            add = "success";
        } else {
            add = "fail";
        }

        return add;
    }


    /* removes the user we got from the client */
    public String removeUser(String userIdentifier) {
        String removed;

        Objects.requireNonNull(userIdentifier, "The given user identifier is null");
        User user = database.removeObject(userIdentifier);

        if (user == null) {
            removed = "failed"; //nothing's removed
        } else {
            removed = "success"; //user was removed
        }

        return removed;
    }

    /* find the user in the database */
    public User getUser(String userIdentifier) {

        Objects.requireNonNull(userIdentifier, "The given user identifier is null");
        User user = database.findObject(userIdentifier);

        return user; //returns null if the user isn't found
    }

    /* saves the profile we got from the client */
    public String saveProfile (Profile profile) {
        String add;
        boolean added;

        Objects.requireNonNull(profile, "The given profile is null");
        added = database.addProfile(profile);

        if (added) {
            add = "success";
        } else {
            add = "fail";
        }

        return add;
    }

    /* removes the profile we got from the client*/
    public String removeProfile (String profileId) {
        String remove;
        boolean removed;

        Objects.requireNonNull(profileId, "The given profileId is null");
        removed = database.removeProfile(profileId);

        if (removed) {
            remove = "success";
        } else {
            remove = "fail";
        }

        return remove;
    }

    public boolean equalsProfile(java.lang.Object o) {
        boolean result = false;
        if (o instanceof Profile) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean equalsUser(java.lang.Object o) {
        boolean result = false;
        if (o instanceof User) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    //for reference
   /** public boolean equals​(java.lang.Object o) {
        boolean result = false;
        if (o instanceof Artist) {
            if (o instanceof Artist) {
                if (this.artistName.equals(((Artist) o).artistName)
                        && this.artistGenre.equals(((Artist) o).artistGenre)
                        && this.artistSongs.equals(((Artist) o).artistSongs)
                        && this.appearsOnSongs.equals(((Artist) o).appearsOnSongs)) {
                    result = true;
                }
            } else {
                result = false;
            }
        }
        return result;
    } **/




}

