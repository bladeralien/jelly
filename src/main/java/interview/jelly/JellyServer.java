package interview.jelly;

import interview.jelly.model.Board;
import interview.jelly.model.JellySession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

/**
 * Created by bladeralien on 2017/2/8.
 */
public class JellyServer {

    private Socket incoming = null;
    private DataOutputStream out = null;

    private static SessionFactory factory;
    static {
        try {
            factory = new Configuration()
                    .configure(new File("conf/hibernate.cfg.xml"))
                    .addAnnotatedClass(JellySession.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create SessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public JellyServer(Socket incomingPara) {
        incoming = incomingPara;
    }

    public void handle() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
            out = new DataOutputStream(incoming.getOutputStream());
            String request = in.readLine();
            StringTokenizer tokenizer = new StringTokenizer(request);
            String httpMethod = tokenizer.nextToken();
            String URL = tokenizer.nextToken();

            String[] splitURL = URL.split("\\?");
            String path = "/";
            Map<String, String> paramsMap = new HashMap<String, String>();
            if (splitURL.length == 1) {
                path = splitURL[0];
            }
            if (splitURL.length == 2) {
                path = splitURL[0];
                for (String pair : splitURL[1].split("&")) {
                    paramsMap.put(pair.split("=")[0], pair.split("=")[1]);
                }
            }

            if (httpMethod.equals("GET") && path.equals("/start-level")) {
                Board board = new Board();
                board.initialize();
                System.out.println(board.toString());
                UUID id = UUID.randomUUID();
                Session session = factory.openSession();
                Transaction transaction = null;
                try{
                    transaction = session.beginTransaction();
                    JellySession jellySession = new JellySession();
                    jellySession.setId(id.toString());
                    jellySession.setBoard(board.toString());
                    session.save(jellySession);
                    transaction.commit();
                } catch (HibernateException e) {
                    if (transaction != null) transaction.rollback();
                    e.printStackTrace();
                } finally {
                    session.close();
                }
                sendResponse("200 OK", id.toString() + "\n" + board.toString());
            } else if (httpMethod.equals("GET") && path.equals("/move")) {
                Board board = null;
                Session session = factory.openSession();
                Transaction transaction = null;
                try{
                    transaction = session.beginTransaction();
                    JellySession jellySession = (JellySession) session.get(JellySession.class, paramsMap.get("sessionId"));
                    board = new Board(jellySession.getBoard());
                    System.out.println(board.toString());
                    board.erase(
                            Integer.parseInt(paramsMap.get("row0")),
                            Integer.parseInt(paramsMap.get("col0")),
                            Integer.parseInt(paramsMap.get("row1")),
                            Integer.parseInt(paramsMap.get("col1")));
                    board.fall();
                    board.fill();
                    jellySession.setBoard(board.toString());
                    session.update(jellySession);
                    transaction.commit();
                } catch (HibernateException e) {
                    if (transaction != null) transaction.rollback();
                    e.printStackTrace();
                } finally {
                    session.close();
                }
                System.out.println(board.toString());
                sendResponse("200 OK", board.toString());
            } else {
                sendResponse("404 Not Found", "INVALID PARAMS");
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse("404 Not Found", "INVALID PARAMS");
        }
    }

    private void sendResponse(String status, String response) {
        try {
            out.writeBytes("HTTP/1.1 " + status + "\r\n");
            out.writeBytes("Server: JellyServer\r\n");
            out.writeBytes("Content-Type: text/html" + "\r\n");
            out.writeBytes("Content-Length: " + response.length() + "\r\n");
            out.writeBytes("\r\n");
            out.writeBytes(response);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(8008);
        while (true) {
            Socket incoming = s.accept();
            (new JellyServer(incoming)).handle();
        }
    }
}
