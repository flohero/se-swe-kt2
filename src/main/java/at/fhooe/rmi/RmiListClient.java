package at.fhooe.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiListClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            RmiListServer.Server server = (RmiListServer.Server) registry.lookup("asdf");
            server.put("Hello");
            server.put("World");
            System.out.println(server.get(1));
            System.out.println(server.get(0));
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
