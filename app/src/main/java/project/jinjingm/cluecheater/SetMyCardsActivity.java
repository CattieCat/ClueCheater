package project.jinjingm.cluecheater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;

public class SetMyCardsActivity extends AppCompatActivity {

    private ListView suspectListView;
    private ListView weaponListView;
    private ListView roomListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_my_cards);

        CharacterName[] suspects = CharacterName.values();
        String[] suspectStrings = new String[suspects.length];
        for (int i=0; i<suspects.length; i++){
            suspectStrings[i] = suspects[i].name();
        }

        suspectListView = findViewById(R.id.listView_setMyCards_suspect);
        suspectListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, suspectStrings));
        suspectListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Weapon[] weapons = Weapon.values();
        String[] weaponStrings = new String[weapons.length];
        for (int i=0; i<weapons.length; i++){
            weaponStrings[i] = weapons[i].name();
        }

        weaponListView = findViewById(R.id.listView_setMyCards_weapon);
        weaponListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, weaponStrings));
        weaponListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Room[] rooms = Room.values();
        String[] roomStrings = new String[rooms.length];
        for (int i=0; i<rooms.length; i++){
            roomStrings[i] = rooms[i].name();
        }

        roomListView = findViewById(R.id.listView_setMyCards_room);
        roomListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, roomStrings));
        roomListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    public void SubmitSuggestion(View view) {
        String[] suspects = new String[suspectListView.getCheckedItemCount()];
        SparseBooleanArray selectedSuspects = suspectListView.getCheckedItemPositions();
        int k = 0;
        for (int i = 0; i < suspectListView.getAdapter().getCount(); i++) {
            if (selectedSuspects.get(i)) {
                String suspect = (String)suspectListView.getAdapter().getItem(i);
                suspects[k] = suspect;
                k++;
            }
        }

        String[] weapons = new String[weaponListView.getCheckedItemCount()];
        SparseBooleanArray selectedWeapons = weaponListView.getCheckedItemPositions();
        k = 0;
        for (int i = 0; i < weaponListView.getAdapter().getCount(); i++) {
            if (selectedWeapons.get(i)) {
                String weapon = (String)weaponListView.getAdapter().getItem(i);
                weapons[k] = weapon;
                k++;
            }
        }

        String[] rooms = new String[roomListView.getCheckedItemCount()];
        SparseBooleanArray selectedRooms = roomListView.getCheckedItemPositions();
        k = 0;
        for (int i = 0; i < roomListView.getAdapter().getCount(); i++) {
            if (selectedRooms.get(i)) {
                String room = (String)roomListView.getAdapter().getItem(i);
                rooms[k] = room;
                k++;
            }
        }

        ((MyGame)this.getApplication()).SetMyCards(suspects, weapons, rooms);

        Intent intent = new Intent(this, MakeSuggestionActivity.class);
        intent.putExtra(MakeSuggestionActivity.CURRENT_PLAYER, 0);
        startActivity(intent);
    }
}
