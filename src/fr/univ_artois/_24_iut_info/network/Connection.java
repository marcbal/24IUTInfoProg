package fr.univ_artois._24_iut_info.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Connection {
	
	private DatagramSocket socket;
	private InetSocketAddress addr;
	private Thread receiverThread;
	private Charset charset = Charset.forName("UTF-8");
	private ReceiveListener listener;
	
	
	
	
	public Connection(InetSocketAddress a, String nomEquipe, ReceiveListener l) throws IOException {
		if (a == null || nomEquipe == null)
			throw new IllegalArgumentException("les arguments ne peuvent pas être null");
		socket = new DatagramSocket();
		addr = a;
		
		receiverThread = new Thread(() -> {
			DatagramPacket packet = new DatagramPacket(new byte[4096], 4096);
			
				try {
					while(true) {
						socket.receive(packet);
						
						String[] data = new String(packet.getData(), charset).split("[:-]", 2);

						
						if (data.length != 2) {
							System.err.println("message du serveur mal formé : ");
							System.err.println(Arrays.toString(data));
							continue;
						}
						
						
						try {
							interpreteReceivedMessage(data[0], data[1]);
							
							System.out.println("Serveur : "+Arrays.toString(data));
						} catch (Exception e) {
							System.err.println("erreur d'interprétation du message du serveur : ");
							System.err.println(Arrays.toString(data));
							e.printStackTrace();
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		});
		
		receiverThread.start();
		
		
		send(nomEquipe);
		
		
	}
	
	
	private void send(String s) throws IOException {
		byte[] bytes = s.getBytes(charset);
		socket.send(new DatagramPacket(bytes, bytes.length, addr));
	}
	
	
	private void interpreteReceivedMessage(String code, String data) {
		
		if (code.equals("1") || code.equals("2"))
			listener.onPlayerSet(Integer.parseInt(code));
		
		
		
		else if (code.equals("01")) {
			String[] d = data.split("\n");
			if (d.length != 2)
				throw new InvalidServerMessage("Partie data invalide");
			
			if (!d[1].toLowerCase().startsWith("map="))
				throw new InvalidServerMessage("Partie data invalide : la deuxième ligne doit commencer par 'map='");
			
			String map = d[1].substring(4);
			
			listener.onGameStart(map);
		}
		

		else if (code.equals("10")) {
			listener.onRoundStart();
		}
		
		
		else if (code.equals("20")) {
			String[] d = data.split(":");

			if (d.length != 2)
				throw new InvalidServerMessage("Partie data invalide");
			
			Matcher m = Pattern.compile("^([0-9]+)([A-Za-z]+)([0-9]+)$").matcher(d[1]);
			int ligne = -1, coin = -1;
			char colonne = ' ';
			if (m.find()) {
			    ligne = Integer.parseInt(m.group(1));
			    colonne = m.group(2).charAt(0);
			    coin = Integer.parseInt(m.group(3));
			}
			else
				throw new InvalidServerMessage("Le code du coup adversaire est invalide : doit être ChiffreLettreChiffre (1A1 par exemple)");
			
			listener.onOpponentPlay(ligne, colonne, coin);
		}


		else if (code.equals("21")) {
			listener.onIllegalPlay();
		}

		else if (code.equals("22")) {
			listener.onOpponentPlayIllegal();
		}

		else if (code.equals("50")) {
			listener.onPlayerCantPlay();
		}

		else if (code.equals("88")) {
			listener.onGameFinish(data);
		}
		
		
		else {
			throw new InvalidServerMessage("le code du message est inconnu");
		}
	}
	
	
	
	
	
	
	
	
	@SuppressWarnings("serial")
	private class InvalidServerMessage extends RuntimeException {
		public InvalidServerMessage(String message) {
			super(message);
		}
	}
	
}
