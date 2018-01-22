package project.jinjingm.cluecheater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class StartGameActivity extends AppCompatActivity {

    private static final ArrayList<String> PlayerOptions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
    }

    public void SetPlayerNumber(View view){
        EditText editText = findViewById(R.id.editText_playerNum);
        int num = Integer.parseInt(editText.getText().toString());

        if (num >= 4 && num <=6) {
            ((MyGame) this.getApplication()).SetPlayerNumber(num);

            Intent intent = new Intent(this, SetPlayerCharacterNameActivity.class);
            intent.putExtra(SetPlayerCharacterNameActivity.CURRENT_PLAYER, 0);
            startActivity(intent);
        }
    }
}