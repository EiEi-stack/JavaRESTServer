package JavaProject.JavaRESTServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException{
    	int serverPort =8000;
    	HttpServer server = HttpServer.create(new InetSocketAddress(serverPort),0);
    	server.createContext("/api/hello",(exchange->{
    		
    		if("GET".equals(exchange.getRequestMethod())) {
    			String respText = "Hello!";
        		exchange.sendResponseHeaders(200, respText.getBytes().length);
        		OutputStream output= exchange.getResponseBody();
        		output.write(respText.getBytes());
        		output.flush();
    		} else {
    			exchange.sendResponseHeaders(405, -1);//405 メソッドはきょうかされていない
    		}
    		
    		exchange.close();
    		
    	}));
    	
    	server.setExecutor(null); //デフォルト設定
    	server.start();
    	
    }
}
