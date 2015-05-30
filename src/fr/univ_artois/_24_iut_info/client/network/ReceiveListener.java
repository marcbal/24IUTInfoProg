package fr.univ_artois._24_iut_info.client.network;

public interface ReceiveListener {
	
	/**
	 * Méthode appelée lorque le serveur a défini les numéro des joueurs
	 * @param playerId l'identifiant du joueur dans la partie : 1 (Rouge) ou 2 (Vert)
	 */
	public void onPlayerSet(int playerId);
	
	/**
	 * Méthode appelée lorque le serveur envoir la map pour la partie
	 * @param mapStr la map sous forme de chaine de caractère : "|" sépare les lignes, et ":" sépare les cases
	 */
	public void onGameStart(String mapStr);
	
	/**
	 * Méthode appelée lorque le serveur nous envoi les informations du coup de l'adversaire
	 * <br/><br/>
	 * <b>Important : </b> Il se peut que l'adversaire ait envoyé un coup invalide. Dans ce cas, la méthode
	 * <code>onOpponentPlayIllegal()</code> est appelée
	 * @param ligne la ligne du twist-lock posé par l'adversaire
	 * @param colonne la colonne du twist-lock posé par l'adversaire
	 * @param coin le coin du twist-lock posé par l'adversaire
	 */
	public void onOpponentPlay(int ligne, char colonne, int coin);

	/**
	 * Méthode appelée lorsque le serveur nous attend pour jouer
	 */
	public void onRoundStart();
	
	/**
	 * Méthode appelée lorsque le serveur indique que le coup qu'on a joué est illégal
	 */
	public void onIllegalPlay();
	
	/**
	 * Méthode appelée lorsque le serveur indique que le coup de l'adversaire est illégal
	 */
	public void onOpponentPlayIllegal();
	
	/**
	 * Méthode appelée lorsque le serveur indique que nous ne pouvont plus jouer
	 */
	public void onPlayerCantPlay();
	
	/**
	 * Méthode appelée lorsque le serveur indique que la partie est terminée
	 * @param serverMessage le message du serveur, contenant le score de la partie
	 */
	public void onGameFinish(String serverMessage);
	
}
