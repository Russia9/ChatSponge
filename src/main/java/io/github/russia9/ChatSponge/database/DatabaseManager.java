package io.github.russia9.ChatSponge.database;

import io.github.russia9.ChatSponge.ChatSponge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private ChatSponge plugin;

    public DatabaseManager(ChatSponge plugin) {
        this.plugin = plugin;
    }


    public void query(String uri, String query) {
        try (Connection conn = plugin.getSqlService().getDataSource("").getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet results = stmt.executeQuery()) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
