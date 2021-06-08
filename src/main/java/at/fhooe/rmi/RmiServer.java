package at.fhooe.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer {

    public interface GameServer extends Remote {

        String getScore() throws RemoteException;
        void addGoal(int team) throws RemoteException;
        String getTeam1() throws RemoteException;
        String getTeam2() throws RemoteException;

    }

    public static class GameServerImpl implements GameServer
    {
        private String team1;
        private String team2;
        private int goalsTeam1;
        private int goalsTeam2;


        @Override
        public String getScore() {
            return goalsTeam1 + ":" + goalsTeam2;
        }

        @Override
        public synchronized void addGoal(int team) {
            if(team == 1) {
                goalsTeam1++;
            } else {
                goalsTeam2++;
            }
        }

        @Override
        public String getTeam1() {
            return team1;
        }

        @Override
        public String getTeam2() {
            return team2;
        }
    }

    public static void main(String[] args) {
        try {
            GameServer gameServer = (GameServer) UnicastRemoteObject.exportObject(new GameServerImpl(), 0);

            var registry = LocateRegistry.createRegistry(1099);
            registry.rebind("GameServer", gameServer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
