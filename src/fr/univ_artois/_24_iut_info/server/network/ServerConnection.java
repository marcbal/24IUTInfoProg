package fr.univ_artois._24_iut_info.server.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <h1>Protocole de communication entre le serveur et un client</h1>
 * <p>
 * <b>ABC:Data</b><br/>
 * <br/>
 * A = 1 si serveur, 2 si client<br/>
 * B = 1 si normal, 2 si warning, 3 si erreur<br/>
 * C de 0 à 9
 * </p>
 * <ul>
 * 	<li>Le client se connecte</li>
 * 	<li><code>210:Les Mouny Python</code></li>
 * 	<li><code>120:Message</code> Erreur de protocole</li>
 * 	<li><code>130:</code> Impossible de rejoindre le serveur</li>
 * 	<li><code>110:1</code> (1 ou 2, le numéro du joueur)</li>
 *  <li><code>111:1:2:3|4:5:6|7:8:9</code></li>
 *  <li><code>112:</code> (c'est au joueur de jouer)</li>
 *  <li><code>212:1:1</code> (coordonnée du pion à poser, partant de 0 0)</li>
 *  <li><code>113:</code> Le pion a été posé correctement</li>
 *  <li><code>123:</code> Le pion a mal été posé</li>
 *  <li><code>114:1:1</code> L'ennemie a posé le pion à ces coordonnées</li>
 *  <li><code>124:</code> L'ennemie a mal joué</li>
 *  <li><code>115:</code> Vous n'avez plus de pion</li>
 *  <li><code>116:183:230</code> La partie est terminée (score joueur 1 et score joueur 2)</li>
 * </ul>
 * @author Marc Baloup
 *
 */
public class ServerConnection {
	
	private DatagramSocket socket;
	private InetSocketAddress addr;
	private Thread receiverThread;
	private Charset charset = Charset.forName("UTF-8");
	private ReceiveListener listener;
	
	
	
	
	public ServerConnection(int port, ReceiveListener l) throws IOException {
		if (port <= 0 || port > 65535 || l == null)
			throw new IllegalArgumentException("les arguments ne peuvent pas être null");
		socket = new DatagramSocket(port);
		
		listener = l;
		
		receiverThread = new Thread(() -> {
			DatagramPacket packet = new DatagramPacket(new byte[4096], 4096, addr);
			
				try {
					while(true) {
						socket.receive(packet);
						
						String dataStr = new String(packet.getData(), charset).substring(0, packet.getLength());

						System.out.println("[Client "+packet.getSocketAddress()+"] "+dataStr);
						
						String[] data = dataStr.split(":", 2);
						
						
						if (data.length != 2) {
							System.err.println("message du client mal formé");
							sendProtocolError(packet.getSocketAddress(), "Erreur protocole : le packet doit contenir au moins un symbole \":\"");
							continue;
						}
						
						int code = 0;
						try {
							code = Integer.parseInt(data[0]);
						} catch (NumberFormatException e) {
							System.err.println("message du client mal formé");
							sendProtocolError(packet.getSocketAddress(), "Erreur protocole : la première partie doit être un entier");
							continue;
						}
						
						
						try {
							interpreteReceivedMessage(packet.getSocketAddress(), code, data[1]);
						} catch (InvalidClientMessage e) {
							System.err.println("message du client mal formé");
							sendProtocolError(packet.getSocketAddress(), "Erreur protocole : "+e.getMessage());
						} catch (Exception e) {
							System.err.println("erreur lors de la prise en charge du message du client");
							e.printStackTrace();
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		});
		
		receiverThread.start();
		
	}
	
	
	private void send(SocketAddress addr, int code, String data) throws IOException {
		String out = code+":"+data;
		System.out.println("[Serveur à "+addr+"] "+out);
		byte[] bytes = out.getBytes(charset);
		socket.send(new DatagramPacket(bytes, bytes.length, addr));
	}
	
	
	private void interpreteReceivedMessage(SocketAddress addr, int code, String data) {
		
		if (code == 210) {
			listener.onPlayerSendName(addr, data);
		}
		
		
		else if (code == 212) {
			
			String[] spl = data.split(":", 2);
			if (spl.length != 2)
				throw new InvalidClientMessage("voud devez envoyer 2 nombres séparés du symbole \":\"");
			
			int ligne = 0, colonne = 0;
			try {
				ligne = Integer.parseInt(spl[0]);
				colonne = Integer.parseInt(spl[1]);
			} catch(NumberFormatException e) {
				throw new InvalidClientMessage("les deux nombres doivent être entier");
			}
			
			listener.onPlayerSendPlayCoordinate(addr, ligne, colonne);
		}
		
		
		
		
		else {
			throw new InvalidClientMessage("le code du message est inconnu");
		}
	}
	
	
	
	
	

	public void sendProtocolError(SocketAddress addr, String message) throws IOException {
		send(addr, 120, message);
	}

	public void sendCantJoin(SocketAddress addr) throws IOException {
		send(addr, 130, "");
	}
	
	public void sendPlayerNumber(SocketAddress addr, int nb) throws IOException {
		send(addr, 110, Integer.toString(nb));
	}
	
	public void sendMap(SocketAddress addr, String map) throws IOException {
		send(addr, 111, map);
	}
	
	public void sendPlay(SocketAddress addr) throws IOException {
		send(addr, 112, "");
	}
	
	public void sendGoodPlay(SocketAddress addr) throws IOException {
		send(addr, 113, "");
	}
	
	public void sendBadPlay(SocketAddress addr) throws IOException {
		send(addr, 123, "");
	}
	
	public void sendEnnemyGoodPlay(SocketAddress addr, int ligne, int colonne) throws IOException {
		send(addr, 114, ligne+":"+colonne);
	}
	
	public void sendEnnemyBadPlay(SocketAddress addr) throws IOException {
		send(addr, 124, "");
	}
	
	public void sendNoMorePawn(SocketAddress addr) throws IOException {
		send(addr, 115, "");
	}
	
	public void sendFinish(SocketAddress addr, int[] scorePlayer) throws IOException {
		String out = ""; boolean premier = true;
		for (int s : scorePlayer) {
			if (!premier) out += ":";
			premier = false;
			out += s;
		}
		
		send(addr, 116, out);
	}
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("serial")
	private class InvalidClientMessage extends RuntimeException {
		public InvalidClientMessage(String message) {
			super(message);
		}
	}
	
}
