package nyc.c4q.rafaelsoto.monsteregg.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;


public class MonsterFragment extends Fragment {
    View root;
    ImageView ivMonsterPic;
    TextView tvMonsterName;
    TextView tvMonsterType;
    TextView tvMonsterRarity;
    TextView tvMonsterLikes;
    TextView tvMonsterWeakness;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.activity_monster, container, false);
        initViews();
        loadMonster((Monster) getArguments().getSerializable("monster"));
        return root;
    }

    public void loadMonster(Monster monster) {

        Picasso.with(getContext()).load("file:///android_asset/" + monster.getImageAsset()).into(ivMonsterPic);

        tvMonsterName.setText(monster.getName());
        tvMonsterType.setText("Type: " + monster.getType());

        switch (monster.getRarity()) {
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
