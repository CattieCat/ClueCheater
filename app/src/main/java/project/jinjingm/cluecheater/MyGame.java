package project.jinjingm.cluecheater;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by majin on 1/21/2018.
 */

public class MyGame extends Application {
    public int PlayerCount;
    public int SuspectCount = 6;
    public int WeaponCount = 6;
    public int RoomCount = 9;
    public Player MyPlayer;
    public Player[] PlayerList; // indexed by player number
    public HashMap<CharacterName, Player> FindPlayerByNameMap;

    public State[][] SuspectMap;
    public State[][] WeaponMap;
    public State[][] RoomMap;

    public void SetPlayerNumber(int playerCount){
        PlayerCount = playerCount;
        PlayerList = new Player[playerCount];
        FindPlayerByNameMap = new HashMap<CharacterName, Player>(playerCount);
        SuspectMap = new State[playerCount][SuspectCount];
        WeaponMap = new State[playerCount][WeaponCount];
        RoomMap = new State[playerCount][RoomCount];
    }

    public void AddPlayer(int playerNumber, CharacterName character, Boolean me){
        Player player = new Player(character, playerNumber);
        PlayerList[playerNumber] = player;
        FindPlayerByNameMap.put(character, player);

        for (int i=0; i<SuspectCount; i++){
            SuspectMap[playerNumber][i] = State.Unknown;
        }

        for (int i=0; i<WeaponCount; i++){
            WeaponMap[playerNumber][i] = State.Unknown;
        }

        for (int i=0; i<RoomCount; i++){
            RoomMap[playerNumber][i] = State.Unknown;
        }

        if (me && MyPlayer == null) {
            MyPlayer = player;
        }
    }

    public void SetMyCards(String[] suspects, String[] weapons, String[] rooms){
        if(suspects != null){
            for (int i=0; i<suspects.length; i++){
                CharacterName suspect = CharacterName.valueOf(suspects[i]);
                SuspectMap[MyPlayer.Number][suspect.ordinal()] = State.Have;
            }
        }

        if(weapons != null){
            for (int i=0; i<weapons.length; i++){
                Weapon weapon = Weapon.valueOf(weapons[i]);
                WeaponMap[MyPlayer.Number][weapon.ordinal()] = State.Have;
            }
        }

        if(rooms != null){
            for (int i=0; i<rooms.length; i++){
                Room room = Room.valueOf(rooms[i]);
                RoomMap[MyPlayer.Number][room.ordinal()] = State.Have;
            }
        }
    }

    public void MakeSuggestion(int playerNumber, CharacterName suspect, Weapon weapon, Room room) {
        if(playerNumber != MyPlayer.Number) {
            SuspectMap[playerNumber][suspect.ordinal()] = State.MightNotHave;
            WeaponMap[playerNumber][weapon.ordinal()] = State.MightNotHave;
        }
    }
}
