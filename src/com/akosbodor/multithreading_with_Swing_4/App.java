package com.akosbodor.multithreading_with_Swing_4;

import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				new MainFrame("SwingWorker Demo");
			}
		});
		
	}

}
