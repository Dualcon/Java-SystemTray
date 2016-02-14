package com.wikidreams.systemtray;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class SystemTrayManager {

	private static TrayIcon trayIcon;
	private static PopupMenu menu;
	private static ImageIcon icon;
	private static String iconTitle = "Demo";
	private static String baloonTitle = "Application";
	private static String ballonMessage = "Running on system tray.";

	public static void CreateSystemTrayManager() {
		if (! SystemTray.isSupported()) {
			System.out.println("The system does not supports system tray.");
			System.exit(0);
		}
		CreateSystemTrayMenu();
	}


	private static void CreateSystemTrayMenu() {
		menu = new PopupMenu();

		MenuItem exitItem = new MenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menu.add(exitItem);
		CreateSystemTrayIcon();
	}


	private static void CreateSystemTrayIcon() {
		icon = new ImageIcon("images/icone.gif");
		trayIcon = new TrayIcon(icon.getImage(), iconTitle, menu);
		trayIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					SystemTray.getSystemTray().remove(trayIcon);
				} 
			}            
		});
		AddToSystemTray();
	}


	private static void AddToSystemTray() {
		try {
			SystemTray.getSystemTray().add(trayIcon);
			trayIcon.displayMessage(baloonTitle, ballonMessage, TrayIcon.MessageType.INFO);
		} catch (AWTException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
