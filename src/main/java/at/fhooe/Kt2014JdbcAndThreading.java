package at.fhooe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Kt2014JdbcAndThreading {
    public static class WorldCupStatistics {
        public Player getTopScorerOfTeam(String team) throws SQLException {
            try (var connection = DbHelper.getPlayerDbConnection()) {
                var stmt = connection.prepareStatement("SELECT name, goals, team FROM Person WHERE team = ? ORDER BY goals DESC LIMIT 1");
                stmt.setString(1, team);
                try (ResultSet rs = stmt.executeQuery()) {
                    rs.next();
                    return new Player(
                            rs.getString(1),
                            rs.getInt(2),
                            rs.getString(3));
                }

            } catch (SQLException exception) {
                exception.printStackTrace();
            }

            return null;
        }

        public void getTopScorerOfTeamAsync(String team, Listener listener) {
            new Thread(() -> {
                try {
                    Player player = getTopScorerOfTeam(team);
                    listener.playerStatisticsComputed(player);
                } catch (SQLException e) {
                    listener.databaseAccessFailed();
                }
            }).start();
        }
    }


    interface Listener {
        void databaseAccessFailed();

        void playerStatisticsComputed(Player player);
    }

    public static class PrintListener implements Listener {
        @Override
        public void databaseAccessFailed() {
            System.out.println("Exception!");
        }

        @Override
        public void playerStatisticsComputed(Player player) {
            System.out.println(player);
        }
    }

    public static class Player {
        private String name;
        private int goals;
        private String team;

        public Player(String name, int goals, String team) {
            this.name = name;
            this.goals = goals;
            this.team = team;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", goals=" + goals +
                    ", team='" + team + '\'' +
                    '}';
        }
    }

    public static class DbHelper {
        public static Connection getPlayerDbConnection() throws SQLException {
            return DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Fifa", "root", "toor");
        }
    }

    public static void main(String[] args) {
        new WorldCupStatistics()
                .getTopScorerOfTeamAsync("jklö", new PrintListener());

    }
}
