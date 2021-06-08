package at.fhooe.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            RmiServer.GameServer gameServer = (RmiServer.GameServer) registry.lookup("GameServer");
            gameServer.addGoal(1);
            System.out.println(gameServer.getScore());
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

    }
}
