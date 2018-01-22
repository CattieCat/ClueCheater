package project.jinjingm.cluecheater;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SetPlayerCharacterNameActivity extends AppCompatActivity {

    private int currentPlayer;
    private RadioGroup radioGroup;
    private MyGame currentGame;

    public static final String CURRENT_PLAYER = "ClueCheater.SetPlayer.CURRENT_PLAYER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_player_character_name);

        Intent intent = getIntent();
        currentPlayer = intent.getIntExtra(SetPlayerCharacterNameActivity.CURRENT_PLAYER, 0);
        currentGame = (MyGame) this.getApplication();

        TextView textView = findViewById(R.id.textView_currentPlayer);
        textView.setText("Player " + currentPlayer);

        CharacterName[] characters = CharacterName.values();

        radioGroup = (RadioGroup) findViewById(R.id.radio_selectCharacter);
        RadioButton[] radioButtons = new RadioButton[characters.length];
        for(int i=0; i<characters.length; i++)
        {
            radioButtons[i]=new RadioButton(this);
            radioButtons[i].setText(characters[i].name());
            radioButtons[i].setId(("radioBtn_setCharacter" + i).hashCode());

            if (!currentGame.FindPlayerByNameMap.containsKey(characters[i])) {
                radioButtons[i].setEnabled(true);
            }
            else {
                radioButtons[i].setEnabled(false);
            }

            radioGroup.addView(radioButtons[i]);
        }

        CheckBox checkbox = findViewById(R.id.checkBox_itsMe);
        if(currentGame.MyPlayer == null){
            checkbox.setEnabled(true);
        }
        else {
            checkbox.setEnabled(false);
        }
    }

    public void addPlayer(View view) {
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton selected = (RadioButton) findViewById(selectedId);
        CheckBox checkbox = findViewById(R.id.checkBox_itsMe);
        currentGame.AddPlayer(currentPlayer, CharacterName.valueOf(selected.getText().toString()), checkbox.isChecked());

        if (currentPlayer < currentGame.PlayerCount-1) {
            Intent intent = new Intent(this, SetPlayerCharacterNameActivity.class);
            intent.putExtra(SetPlayerCharacterNameActivity.CURRENT_PLAYER, currentPlayer + 1);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, SetMyCardsActivity.class);
            startActivity(intent);
        }
    }
}
