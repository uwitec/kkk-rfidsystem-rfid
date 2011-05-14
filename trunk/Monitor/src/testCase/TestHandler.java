package testCase;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import utilities.Handler;
import utilities.Information;

public class TestHandler {
	public static void main(String args[]) throws Exception, IOException{
		Socket aSocket = new Socket( "183.5.218.209", 10000 );
		Information info = new Information( 1, null );
		Handler handler1 = new Handler( aSocket, info );
		System.out.println("sending handler is starting.");
		handler1.start();
		Handler handler2 = new Handler( aSocket );
		System.out.println("Recieving handler is starting.");
		handler2.start();
	}
}
