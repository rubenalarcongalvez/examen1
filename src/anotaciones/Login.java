package anotaciones;

import java.util.ArrayList;
import java.util.Scanner;

@Credencial(usuario = "usuario1", hashPasswd = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918")
@Credencial(usuario = "usuario2", hashPasswd = "ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270") 
public class Login {
	private ArrayList<String> usuario;
	private ArrayList<String> pass;
	/**
	 * @param usuario
	 * @param pass
	 */
	public Login() {
		usuario = new ArrayList<>();
		pass = new ArrayList<>();
		
		Credenciales cred = Login.class.getAnnotation(Credenciales.class);
		
		this.usuario.add(cred.value()[0].usuario());
		this.usuario.add(cred.value()[1].usuario());
		
		this.pass.add(cred.value()[0].hashPasswd());
		this.pass.add(cred.value()[1].hashPasswd());
	}
	
	public static void login(String usuario, String contrasena) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Dime tu nombre de usuario: ");
		String user = sc.next();
		
		System.out.print("Dime tu contraseña: ");
		String contra = sc.next();
		
		if (usuario.equals(this.usuario) && contra.equals(this.pass)) {
			System.out.println("ACCESO CONCEDIDO");
		} else {
			System.out.println("ACCESO DENEGADO");
		}
	}
	
}