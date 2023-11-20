package com.example.mancity.ui.player;

import java.util.Objects;

public class PlayerModel {
    String man_city_player;
    String man_city_player_number;
    String man_city_player_position;
    String man_city_player_nation;
    String man_city_player_dob;
    String man_city_player_joined;
    String man_city_player_value;

    private final long playerId; // Unique ID for each player



    int flag;
    String man_city_player_description;
    int image;

    public PlayerModel(String man_city_player, String man_city_player_number, String man_city_player_position, String man_city_player_nation, int flag, String man_city_player_dob, String man_city_player_joined, String man_city_player_value, String man_city_player_description, int image) {
        this.man_city_player = man_city_player;
        this.man_city_player_number = man_city_player_number;
        this.man_city_player_position = man_city_player_position;
        this.man_city_player_nation = man_city_player_nation;
        this.flag = flag;
        this.man_city_player_dob = man_city_player_dob;
        this.man_city_player_joined = man_city_player_joined;
        this.man_city_player_value = man_city_player_value;
        this.man_city_player_description = man_city_player_description;
        this.image = image;
        this.playerId = generateUniqueId(man_city_player, man_city_player_number);
    }

    public String getMan_city_player() {
        return man_city_player;
    }

    public String getMan_city_player_number() {
        return man_city_player_number;
    }

    public String getMan_city_player_position() {
        return man_city_player_position;
    }

    public String getMan_city_player_nation() {
        return man_city_player_nation;
    }

    public int getFlag() {
        return flag;
    }

    public String getMan_city_player_dob() { return man_city_player_dob; }

    public String getMan_city_player_joined() {
        return man_city_player_joined;
    }

    public String getMan_city_player_value() {
        return man_city_player_value;
    }

    public String getMan_city_player_description() { return man_city_player_description; }

    public int getImage() {
        return image;
    }

    public long getPlayerId() {
        return playerId;
    }

    // Generate a unique ID based on player's name and number
    private long generateUniqueId(String name, String number) {
        return Objects.hash(name, number);
    }
}
