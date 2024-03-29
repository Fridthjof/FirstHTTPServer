// TEST TEST TESTSET SETSFASLÆDL
package firsthttpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;

/**
 * @author Lars Mortensen
 */
public class FirstHtttpServer {
  static int port = 8080;
  static String ip = "127.0.0.1";
  public static void main(String[] args) throws Exception {
    if(args.length == 2){
      port = Integer.parseInt(args[0]);
      ip = args[1];
    }
    HttpServer server = HttpServer.create(new InetSocketAddress(ip,port), 0);
    server.createContext("/welcome", new RequestHandler());
    server.setExecutor(null); // Use the default executor
    server.start();
    System.out.println("Server started, listening on port: "+port);
  }

  static class RequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException 
    {
       StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<title>My Fancy Web Site</title>\n");
        sb.append("<meta charset='UTF-8'>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("<h1>Welcome to my very first home Web Server :-) </h1>\n");
        sb.append("</body>\n");
        sb.append("</html>");
        String response = sb.toString();
      //String response = "Welcome to my very first almost home made Web Server :-)";
      
      he.sendResponseHeaders(200, response.length());
      Headers h = he.getResponseHeaders();
      h.add("Content-type", "text/html");
      try (PrintWriter pw = new PrintWriter(he.getResponseBody())) {
        pw.print(response); //What happens if we use a println instead of print --> Explain
      }
    }
  }
}