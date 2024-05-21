package mvc;

import java.awt.EventQueue;

import com.formdev.flatlaf.FlatLightLaf;

import controlador.Controlador;

public class Main {

	public static void main(String[] args){
		FlatLightLaf.setup();
		// TODO Auto-generated method stub
		Controlador controlador = new Controlador();
		controlador.login();
		
		// controlador.panelPrincipal();
	}
}
