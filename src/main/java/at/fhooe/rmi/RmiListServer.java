package at.fhooe.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RmiListServer {

    public interface Server extends Remote {
        void put(String value) throws RemoteException;

        String get(int i) throws RemoteException;
    }

    public static class ListServer implements Server {
        private final List<String> list = new ArrayList<>();

        @Override
        public synchronized void put(String value) throws RemoteException {
            list.add(value);
        }

        @Override
        public synchronized String get(int i) throws RemoteException {
            return list.get(i);
        }
    }

    public static void main(String[] args) {
        Server server = new ListServer();
        try {
            Server stub = (Server) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("asdf", stub);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
