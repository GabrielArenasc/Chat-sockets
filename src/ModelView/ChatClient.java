package ModelView;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    private PrintWriter out;
    private BufferedReader in;

    public void ChatClients() {
        try {
            Socket socket = new Socket("192.168.154.28", 4); // Reemplaza SERVIDOR_IP con la dirección IP del servidor
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        // Establecer un hilo para recibir mensajes del servidor
        new Thread(new Client()).start();
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String receiveMessage() {
        try {
            String inputLine = in.readLine();
            return inputLine;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private class Client implements Runnable {
        @Override
        public void run() {
            String inputLine;
            while ((inputLine = receiveMessage()) != null) {
                // Manejar el mensaje recibido, por ejemplo, mostrarlo en la interfaz de usuario
            }

            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}