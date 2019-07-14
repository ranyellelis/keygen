import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

public class Criptografia {
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Criptografia s = new Criptografia();
		
		String data = JOptionPane.showInputDialog("Digite a data no formato: dd/MM/yyyy");
		String dias = JOptionPane.showInputDialog("Digite a quantidade de dias:");
		
		JOptionPane.showInputDialog("Campo 1: ",s.encripta(data));
		JOptionPane.showInputDialog("Campo 2: ",s.encripta(dias));
	}
	
	public String encripta(String msg) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		byte[] mensagem = msg.getBytes("UTF-8");
		byte[] chave = "autorizado10+rpl".getBytes("UTF-8");
		
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave, "AES"));
		byte[] encrypted = cipher.doFinal(mensagem);
		
		String retorno = DatatypeConverter.printBase64Binary(encrypted);
		System.out.println(retorno);
		return retorno;
		
	}

	public String decripta(String msg) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		byte[] mensagem = DatatypeConverter.parseBase64Binary(msg);
		byte[] chave = "autorizado10+rpl".getBytes("UTF-8");
		
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chave, "AES"));
		byte[] decrypted = cipher.doFinal(mensagem);
		
		String retorno = new String(decrypted);
		System.out.println(retorno);
		return retorno;
	}
}