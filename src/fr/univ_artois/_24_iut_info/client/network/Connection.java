package fr.univ_artois._24_iut_info.client.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

public class Connection {
	
	private DatagramSocket socket;
	private SocketAddress addr;
	private Thread receiverThread;
	private Charset charset = Charset.forName("UTF-8");
	private ReceiveListener listener;
	
	
	
	
	public Connection(SocketAddress a, String nomEquipe, ReceiveListener l) throws IOException {
		if (a == null || nomEquipe == null || l == null)
			throw new IllegalArgumentException("les arguments ne peuvent pas être null");
		socket = new DatagramSocket();
		addr = a;
		listener = l;
		
		// socket.setSoTimeout(30000); // timeout de 30 secondes
		System.out.println("Connexion au serveur à l'adresse "+addr.toString());
		socket.connect(addr);
		
		receiverThread = new Thread(() -> {
			DatagramPacket packet = new DatagramPacket(new byte[4096], 4096, addr);
			
				try {
					while(true) {
						socket.receive(packet);
						
						String dataStr = new String(packet.getData(), charset).substring(0, packet.getLength());

						System.out.println("[Serveur] "+dataStr);
						
						String[] data = dataStr.split(":", 2);
						
						
						if (data.length != 2) {
							System.err.println("message du serveur mal formé");
							continue;
						}
						
						
						try {
							interpreteReceivedMessage(Integer.parseInt(data[0]), data[1]);
						} catch (Exception e) {
							System.err.println("erreur lors de la prise en charge du message du serveur");
							e.printStackTrace();
						}
						
					}
				} catch (SocketTimeoutException e) {
					System.err.println("Le serveur a prit trop de temps à répondre");
				} catch (Exception e) {
					e.printStackTrace();
				}
		});
		
		receiverThread.start();
		
		
		send(210, nomEquipe);
		
	}
	

	private void send(int code, String data) throws IOException {
		String out = code+":"+data;
		System.out.println("[Client] "+out);
		byte[] bytes = out.getBytes(charset);
		socket.send(new DatagramPacket(bytes, bytes.length, addr));
	}
	
	
	private void interpreteReceivedMessage(int code, String data) {
		
		if (code == 120)
			System.err.println("Erreur de protocole : "+data);
		
		else if (code == 130) {
			System.err.println("Impossible de rejoindre le serveur");
			System.exit(0);
		}
		
		else if (code == 110)
			listener.onPlayerSet(Integer.parseInt(data));
		
		
		
		else if (code == 111) {
			listener.onGameStart(data);
		}
		

		else if (code == 112) {
			listener.onRoundStart();
		}
		
		else if (code == 113) {
			listener.onGoodPlay();
		}
		
		else if (code == 123) {
			listener.onIllegalPlay();
		}
		
		
		else if (code == 114) {
			String[] d = data.split(":");

			if (d.length != 2)
				throw new InvalidServerMessage("Partie data invalide");
			
			
			listener.onOpponentPlay(Integer.parseInt(d[0]), Integer.parseInt(d[1]));
		}



		else if (code == 124) {
			listener.onOpponentPlayIllegal();
		}

		else if (code == 115) {
			listener.onPlayerCantPlay();
		}

		else if (code == 116) {
			String[] d = data.split(":");

			if (d.length != 2)
				throw new InvalidServerMessage("Partie data invalide");
			
			listener.onGameFinish(Integer.parseInt(d[0]), Integer.parseInt(d[1]));
			System.exit(0);
		}
		
		
		else {
			throw new InvalidServerMessage("le code du message est inconnu");
		}
	}
	
	
	
	
	
	
	public synchronized void sendTwistLock(int ligne, int colonne) throws IOException {
		send(212, ligne+":"+colonne);
	}
	
	
	
	
	
	@SuppressWarnings("serial")
	private class InvalidServerMessage extends RuntimeException {
		public InvalidServerMessage(String message) {
			super(message);
		}
	}
	
}
