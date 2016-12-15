package nyc.c4q.rafaelsoto.monsteregg.view;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.parceler.Parcels;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;
import nyc.c4q.rafaelsoto.monsteregg.model.MonsterDataProvider;

/**
 * Created by shannonalexander-navarro on 12/10/16.
 */

public class MonsterFragment extends Fragment {
    View root;
    ImageView ivMonsterPic;
    TextView tvMonsterName;
    TextView tvMonsterType;
    TextView tvMonsterRarity;
    TextView tvMonsterLikes;
    TextView tvMonsterWeakness;
    Monster monster;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.monster_fragment, container, false);

        monster = Parcels.unwrap(getArguments().getParcelable(MonsterViewHolder.MONSTER));

        initViews();
        loadMonster();

        return root;

    }

    private void loadMonster() {

        //Load a Random Monster
        Random random = new Random();

        //Generate from List, call monster by array index
        Monster thisMonster = MonsterDataProvider.monsterList.get(random.nextInt(37));

        //Load Image from Assets
        try {
            InputStream is = getActivity().getAssets().open(thisMonster.getImageAsset());
            Drawable d = Drawable.createFromStream(is, null);
            ivMonsterPic.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvMonsterName.setText(monster.getName());
        tvMonsterType.setText("Type: " + monster.getType());

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
        tvMonsterRarity.setText("Rarity: " + monster.getRarity());
        tvMonsterLikes.setText("Likes: " + monster.getLikes());
        tvMonsterWeakness.setText("Weakness: " + monster.getWeakness());
    }

    private void initViews() {
        ivMonsterPic = (ImageView) root.findViewById(R.id.iv_monster_pic);
        tvMonsterName = (TextView) root.findViewById(R.id.tv_monster_name);
        tvMonsterType = (TextView) root.findViewById(R.id.tv_monster_type);
        tvMonsterRarity = (TextView) root.findViewById(R.id.tv_monster_rarity);
        tvMonsterLikes = (TextView) root.findViewById(R.id.tv_monster_likes);
        tvMonsterWeakness = (TextView) root.findViewById(R.id.tv_monster_weakness);
    }
}
