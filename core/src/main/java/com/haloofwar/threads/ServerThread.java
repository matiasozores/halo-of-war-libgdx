package com.haloofwar.threads;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.haloofwar.common.enumerators.BulletType;
import com.haloofwar.common.enumerators.Direction;
import com.haloofwar.common.enumerators.FireArmType;
import com.haloofwar.common.enumerators.MeleeWeaponType;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.interfaces.Weapon;
import com.haloofwar.network.ServerController;
import com.haloofwar.network.components.Client;

public class ServerThread extends Thread {
	private boolean end = false;
	private DatagramSocket socket;
	private int port = 5000;
	private ServerController controller;

	public ServerThread(ServerController controller) {
        this.controller = controller;

		try {
			this.socket = new DatagramSocket(this.port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void run() {
        do{
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            try {
                this.socket.receive(packet);
                this.processMessage(packet);
            } catch (SocketException e) {
                if (!this.end) {
                	e.printStackTrace();
                }
                
                this.end = true;
            } catch (IOException e) {
                if (!this.end) {
                    e.printStackTrace();
                }
            }
        } while (!this.end);
    }

	private void processMessage(DatagramPacket packet) {
		String message = new String(packet.getData()).trim();
		String[] parts = message.split(":");
		
		switch (parts[0]) {
		    case "Connect":
                this.controller.addClient(new Client(packet.getAddress(), packet.getPort()));
                break;
            case "Disconnect":
                this.controller.removeClient(new Client(packet.getAddress(), packet.getPort()));
                break;
            case "Move":
            	this.move(parts);
                break;
            case "Interact":
            	this.interact(parts);
				break;
            case "BuyWeapon":
            	this.buyWeapon(parts);
            	break;
            case "ChangeWeapon":
            	this.changeWeapon(parts);
            	break;
            case "NextCutscene":
    			this.controller.advanceCutscene();
    			break;
            case "ShootBullet":
            	this.shootBullet(parts);
            	break;
            case "MeleeAttack":
            	this.meleeAttack(parts);
            	break;
		default:
			System.out.println("Condicion inesperada...");
			break;
		}
	}

	public void sendMessage(String message, InetAddress clientIp, int clientPort) {
		byte[] data = message.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, clientIp, clientPort);
        try {
            this.socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	private boolean parseBoolean(String str) {
	    if("true".equalsIgnoreCase(str)) {
	    	return true;
	    }
	    
	    if("false".equalsIgnoreCase(str)) {
	    	return false;
	    }
	    
	    throw new IllegalArgumentException("Valor booleano inválido: " + str);
	}

	private Weapon parseWeapon(String name) {
	    Weapon weapon = FireArmType.getByName(name);
	    
	    if(weapon == null) {
	    	weapon = MeleeWeaponType.getByName(name);
	    }
	    
	    if(weapon == null) {
	    	throw new IllegalArgumentException("Arma inválida: " + name);
	    }
	    return weapon;
	}

	private boolean validateParts(String[] parts, int expectedLength, String messageType) {
	    if (parts.length != expectedLength) {
	        System.out.println("Formato inválido de mensaje " + messageType + ": esperado " + expectedLength + " partes, recibido " + parts.length);
	        return false;
	    }
	    return true;
	}
	
	private void move(String parts[]) {
    	if(!this.validateParts(parts, 4, "Move")) {
    		return;
    	}
    	
    	final int IDENTIFIER = Integer.parseInt(parts[1]);
    	final Direction DIRECTION = Direction.getByName(parts[2]);
    	boolean PRESSED = this.parseBoolean(parts[3]);
    	
    	this.controller.moveEntity(IDENTIFIER, DIRECTION, PRESSED);
	}
	
	private void interact(String parts[]) {
		if(!this.validateParts(parts, 3, "Interact")) {
			return;
		}
		
		PlayerType type = PlayerType.getPlayerByName(parts[1]);
		boolean isPressed = this.parseBoolean(parts[2]);
		this.controller.interact(type, isPressed);
	}
	
	private void buyWeapon(String parts[]) {
		if(!this.validateParts(parts, 3, "BuyWeapon")) {
    		return;
    	}
    	
    	PlayerType type = PlayerType.getPlayerByName(parts[1]);
        Weapon weaponType = this.parseWeapon(parts[2]);
        
        this.controller.buyWeapon(type, weaponType);
        
	}
	
	private void changeWeapon(String parts[]) {
		if(!this.validateParts(parts, 3, "ChangeWeapon")) {
    		return;
    	}
    	
    	PlayerType playerOnChangeWeapon = PlayerType.getPlayerByName(parts[1]);
    	Weapon weaponOnChangeWeapon = this.parseWeapon(parts[2]);
    	this.controller.changeWeapon(playerOnChangeWeapon, weaponOnChangeWeapon);
	}
	
	private void shootBullet(String parts[]) {
		if(!this.validateParts(parts, 8, "ShootBullet")) {
			return;
		}
		
		float x = Float.parseFloat(parts[1]);
		float y = Float.parseFloat(parts[2]);
		float dirX = Float.parseFloat(parts[3]);
		float dirY = Float.parseFloat(parts[4]);
		int damage = Integer.parseInt(parts[5]);
		float speed = Float.parseFloat(parts[6]);
		BulletType type = BulletType.getByName(parts[7]);
		
		this.controller.shootBullet(x, y, dirX, dirY, damage, speed, type);
	}
	
	private void meleeAttack(String parts[]) {
		if(!this.validateParts(parts, 8, "MeleeAttack")) {
			return;
		}
		
		final int identifier = Integer.parseInt(parts[1]);
		float x = Float.parseFloat(parts[2]);
		float y = Float.parseFloat(parts[3]);
		float width = Float.parseFloat(parts[4]);
		float height = Float.parseFloat(parts[5]);
		int damage = Integer.parseInt(parts[6]);
		float range = Float.parseFloat(parts[7]);
		
		this.controller.meleeAttack(identifier, x, y, width, height, damage, range);
	}
	
	public void terminate() {
        this.end = true;
        if (this.socket != null && !this.socket.isClosed()) {
            this.socket.close();
        }
        this.interrupt();
    }
}
