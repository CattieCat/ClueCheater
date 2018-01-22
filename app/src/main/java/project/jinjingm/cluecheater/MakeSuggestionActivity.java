package project.jinjingm.cluecheater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MakeSuggestionActivity extends AppCompatActivity {

    private int currentPlayer;
    private RadioGroup suspectRadioGroup;
    private RadioGroup weaponRadioGroup;
    private RadioGroup roomRadioGroup;
    private MyGame currentGame;

    public static final String CURRENT_PLAYER = "ClueCheater.MakeSuggestion.CURRENT_PLAYER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_suggestion);

        Intent intent = getIntent();
        currentPlayer = intent.getIntExtra(MakeSuggestionActivity.CURRENT_PLAYER, 0);
        currentGame = (MyGame) this.getApplication();
        CharacterName currentPlayerName = currentGame.PlayerList[currentPlayer].Name;

        TextView title = findViewById(R.id.textView_makeSuggestionPlayer);
        title.setText("Player: " + currentPlayerName.name());

        CharacterName[] suspects = CharacterName.values();
        RadioButton[] suspectRbs = new RadioButton[suspects.length];
        suspectRadioGroup = findViewById(R.id.radio_suggestSuspect);
        for (int i=0;i<suspects.length;i++) {
            suspectRbs[i]=new RadioButton(this);
            suspectRbs[i].setText(suspects[i].name());
            suspectRbs[i].setId(("radioBtn_suggestSuspect" + i).hashCode());
            suspectRadioGroup.addView(suspectRbs[i]);
        }

        Weapon[] weapons = Weapon.values();
        RadioButton[] weaponRbs = new RadioButton[weapons.length];
        weaponRadioGroup = findViewById(R.id.radio_suggestWeapon);
        for (int i=0;i<weapons.length;i++) {
            weaponRbs[i]=new RadioButton(this);
            weaponRbs[i].setText(weapons[i].name());
            weaponRbs[i].setId(("radioBtn_suggestWeapon" + i).hashCode());
            weaponRadioGroup.addView(weaponRbs[i]);
        }

        Room[] rooms = Room.values();
        RadioButton[] roomRbs = new RadioButton[rooms.length];
        roomRadioGroup = findViewById(R.id.radio_suggestRoom);
        for (int i=0;i<rooms.length;i++) {
            roomRbs[i]=new RadioButton(this);
            roomRbs[i].setText(rooms[i].name());
            roomRbs[i].setId(("radioBtn_suggestRoom" + i).hashCode());
            roomRadioGroup.addView(roomRbs[i]);
        }
    }

    public void SubmitSuggestion(View view) {
        int suspectSelectedId = suspectRadioGroup.getCheckedRadioButtonId();
        int weaponSelectedId = weaponRadioGroup.getCheckedRadioButtonId();
        int roomSelectedId = roomRadioGroup.getCheckedRadioButtonId();

        if(suspectSelectedId > 0 && weaponSelectedId > 0 && roomSelectedId > 0) {
            RadioButton selectedSuspect = findViewById(suspectSelectedId);
            CharacterName suspect = CharacterName.valueOf(selectedSuspect.getText().toString());

            RadioButton selectedWeapon = findViewById(weaponSelectedId);
            Weapon weapon = Weapon.valueOf(selectedWeapon.getText().toString());

            RadioButton selectedRoom = findViewById(roomSelectedId);
            Room room = Room.valueOf(selectedRoom.getText().toString());

            currentGame.MakeSuggestion(currentPlayer, suspect, weapon, room);

            // TODO add redirect
        }
    }
}
