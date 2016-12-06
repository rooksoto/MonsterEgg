package nyc.c4q.rafaelsoto.monsteregg.view;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;
import nyc.c4q.rafaelsoto.monsteregg.model.MonsterDataProvider;

public class MainActivity extends AppCompatActivity {

    ImageView ivMonsterPic;
    TextView tvMonsterName;
    TextView tvMonsterType;
    TextView tvMonsterRarity;
    TextView tvMonsterLikes;
    TextView tvMonsterWeakness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        loadMonster();
    }

    private void loadMonster(){


        //Load a Random Monster
        Random random = new Random();

        //Generate from Map, call by monster "name" value
//        Monster thisMonster = MonsterDataProvider.monsterMap.get("Jinx");

        //Generate from List, call monster by array index
        Monster thisMonster = MonsterDataProvider.monsterList.get(0);

        //Load Image from Assets
        try {
            InputStream is = getAssets().open(thisMonster.getImageAsset());
            Drawable d = Drawable.createFromStream(is, null);
            ivMonsterPic.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvMonsterName.setText(thisMonster.getName());
        tvMonsterType.setText("Type: " + thisMonster.getType());

        switch (thisMonster.getRarity()) {
            case "Common":
                tvMonsterRarity.setTextColor(Color.DKGRAY);
                break;
            case "Rare":
                tvMonsterRarity.setTextColor(Color.BLUE);
                break;
            case "Super Rare":
                tvMonsterRarity.setTextColor(Color.MAGENTA);
                break;
            default:
                break;
        }
        tvMonsterRarity.setText("Rarity: " + thisMonster.getRarity());
        tvMonsterLikes.setText("Likes: " + thisMonster.getLikes());
        tvMonsterWeakness.setText("Weakness: " + thisMonster.getWeakness());
    }

    private void initViews() {
        ivMonsterPic = (ImageView) findViewById(R.id.iv_monster_pic);
        tvMonsterName = (TextView) findViewById(R.id.tv_monster_name);
        tvMonsterType = (TextView) findViewById(R.id.tv_monster_type);
        tvMonsterRarity = (TextView) findViewById(R.id.tv_monster_rarity);
        tvMonsterLikes = (TextView) findViewById(R.id.tv_monster_likes);
        tvMonsterWeakness = (TextView) findViewById(R.id.tv_monster_weakness);
    }
}
