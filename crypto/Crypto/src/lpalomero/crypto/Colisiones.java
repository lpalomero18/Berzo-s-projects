package lpalomero.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Colisiones {

	 private static final int LIMITE = 40554432;
	//private static final int LIMITE = 655360;
	private static long[] mensajes;
	private static String[] resultado;
	private static HashMap<Long, Integer> resultado3;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		resultado = new String[LIMITE];
		resultado3 = new HashMap<Long, Integer>();
		creaArray();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Crea los tags
		for (int i = 0; i < LIMITE - 1; i++) {
			md.update(int2byteArray(mensajes[i]));
			byte[] trunca50bits = trunca50bits(md.digest());
			long indice = byte2long(trunca50bits);
			resultado[i] = convierte(trunca50bits);
			Integer put = resultado3.put(Long.valueOf(byte2long(trunca50bits)),
					new Integer(i));
			if (put != null && (mensajes[i] != mensajes[put.intValue()])) {
				System.out.println("colisión ");
				// }
				// if ( mensajes[i]!=mensajes[put.intValue()]){
				// / resultado[put.intValue()] =
				// convierte(trunca50bits(md.digest()));
				// resultado[i] = convierte(trunca50bits(md.digest()));
				System.out.println(resultado[i] + " " + mensajes[i] + " "
						+ convierte(int2byteArray(mensajes[i])));
				System.out.println(resultado[put.intValue()] + " "
						+ mensajes[put.intValue()] + " "
						+ convierte(int2byteArray(mensajes[put.intValue()])));
				System.out.println();
				System.out.println();
				System.exit(0);
			}
			if (i % 100000 == 0)

				System.out.println(i + " " + resultado[i]);
		}
		/*
		 * // Busca colisiones for (int i = 0; i < 8; i++) { final int j = i;
		 * Runnable r = new Runnable() {
		 * 
		 * @Override public void run() { buscaColisiones(LIMITE / 8 * j, LIMITE
		 * / 8 * (j + 1)); System.out.println("Lanzada Thread " + j); }
		 * 
		 * }; Thread t = new Thread(r); t.start(); }
		 */

		/*
		 * byte byteData[] = md.digest();
		 * 
		 * String output = convierte(byteData); System.out.println(output);
		 * 
		 * byte[] byteData2 = trunca50bits(byteData); output =
		 * convierte(byteData2); System.out.println(output);
		 */
	}

	private static void buscaColisiones(int inicio, int fin) {
		System.out.println("Entramos a buscar colisiones de otra manera");
		for (int i = inicio; i < fin - 1; i++) {
			for (int j = i + 1; j < LIMITE - 1; j++) {

				if (j % 100000 == 0 && i % 10000 == 0)

					System.out.println(i + " Buscando colisiones " + j);
				if (resultado[i].compareTo(resultado[j]) == 0) {
					if (mensajes[i] - mensajes[j] != 0) {
						// String resultadoj =
						// convierte(trunca50bits(md.digest()));
						System.out.println("Colisión encontrada!");
						System.out.println(mensajes[i] + " " + resultado[i]);
						;
						System.out.println(mensajes[j] + " " + resultado[j]);
						System.exit(0);
					}
				}
			}
		}
	}

	private static String convierte(byte[] byteData) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		String output = sb.toString();
		return output;
	}

	public static byte[] trunca50bits(byte[] aTruncar) {
		byte[] mascara = {  0xf, 0xf, 0xf, 0xf,
			0xf, 0xf, 0xf };
		// byte[] mascara = { 0xf, 0xf, 0xf, 0xf, 0xf};
		for (int i = aTruncar.length - 1; aTruncar.length - i-1 < mascara.length; i--) {
			mascara[mascara.length - (aTruncar.length - i)] = (byte) (aTruncar[i] & 0xFF);
		}
		mascara[0] = (byte) (mascara[0] & 0x03);
	//	System.out.println(convierte(mascara));
		return mascara;
	}

	private static void creaArray() {
		System.out.println("Llenando array con " + LIMITE);
		mensajes = new long[LIMITE];
		Random rand = new Random();
		for (int i = 0; i < LIMITE - 1; i++)
			mensajes[i] = rand.nextInt();
		System.out.println("array lleno");
	}

	public static final byte[] int2byteArray(long value) {
		return new byte[] { (byte) (value >>> 56), (byte) (value >>> 48),
				(byte) (value >>> 40), (byte) (value >>> 32),
				(byte) (value >>> 24), (byte) (value >>> 16),
				(byte) (value >>> 8), (byte) (value) };
	}

	public static long byte2long(byte[] entrada) {
		long l = 0;
		for (int i = 0; i < entrada.length; i++) {
			l <<= 8;
			l ^= (long) entrada[i] & 0xFF;
		}
		return l;
	}
}