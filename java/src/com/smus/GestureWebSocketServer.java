package com.smus;

import java.net.InetSocketAddress;
import java.util.Set;

import org.java_websocket.*;
import org.java_websocket.handshake.ClientHandshake;

public class GestureWebSocketServer extends WebSocketServer {

	public GestureWebSocketServer(InetSocketAddress address) {
		super(address);
	}

	@Override
	public void onClose(WebSocket arg0, int arg1, String arg2, boolean arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(WebSocket arg0, Exception arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(WebSocket arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOpen(WebSocket arg0, ClientHandshake arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void sendToAll( String text ) throws InterruptedException {
		Set<WebSocket> con = connections();
		synchronized ( con ) {
			for( WebSocket c : con ) {
				c.send( text );
			}
		}
	}

}
