import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Objects;

public class RequestHandler implements Runnable {

    private final Socket clientSocket;
    private static Database database;

    public RequestHandler(Socket clientSocket) {
        Objects.requireNonNull(clientSocket, "the specified client is null");

        this.clientSocket = clientSocket;
    }

    /* this is what we send back to the client */
    public String getResponse(String request) {
        return request;
    }
    /* need to do this part still (checks if the client gives the correct format to stuff)
    * I need client to tell me if:
    * The person using the program is saving or removing an account (user) or saving or removing a profile
    */

    /* saves the user we got from the client */
    public boolean saveUser(User user) {
        boolean added;

        Objects.requireNonNull(user, "The given user is null");
        added = database.addObject(user);

        return added;
    }

    /* removes the user we got from the client */
    public boolean removeUser(String userIdentifier) {
        boolean removed;

        Objects.requireNonNull(userIdentifier, "The given user identifier is null");
        User user = database.removeObject(userIdentifier);

        if (user == null) {
            removed = false; //nothing's removed
        } else {
            removed = true; //user was removed
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
    public boolean saveProfile (Profile profile) {
        boolean added;

        Objects.requireNonNull(profile, "The given profile is null");
        added = database.addProfile(profile);

        return added;
    }

    /* removes the profile we got from the client*/
    public boolean removeProfile (String profileId) {
        boolean removed;

        Objects.requireNonNull(profileId, "The given profileId is null");
        removed = database.removeProfile(profileId);

        return removed;
    }

    /* Reads the request from the client, gets the response, and writes/sends it back to the client */
    @Override
    public void run() {
        String request;
        String response;

        try (var inputStream = this.clientSocket.getInputStream(); //reads in the input stream
             var reader = new BufferedReader(new InputStreamReader(inputStream)); //reads the input stream
             var outputStream = this.clientSocket.getOutputStream(); //creates an output stream
             var writer = new BufferedWriter(new OutputStreamWriter(outputStream))) { //writes the output stream

            /* reads in the database info */
            try (var inputStreamDatabase = new ObjectInputStream(new FileInputStream("objects.ser"))) {
                List<User> objects;

                objects = (List<User>) inputStreamDatabase.readObject();

                database = new Database(objects);
            } catch (IOException | ClassNotFoundException e) { //email lwz1030@gmail.com
                database = new Database();
            } //end try-catch   //

            request = reader.readLine();

            response = this.getResponse(request);

            writer.write(response);

            writer.newLine();

            /* writes to the database the updated info */
            try (var outputStreamDatabase = new ObjectOutputStream(new FileOutputStream("objects.ser"))) {
                List<User> objects;

                objects = database.getObjects();

                outputStreamDatabase.writeObject(objects);
            } catch (IOException e) {
                e.printStackTrace();
            } //end try-catch  //WRITE

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "RequestHandler{" +
                "clientSocket=" + clientSocket +
                '}';
    }
}
