package nyc.c4q.rafaelsoto.monsteregg.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;


public class MonsterFragment extends Fragment  {
    View view;
    ImageView ivMonsterPic;
    TextView tvMonsterName;
    TextView tvMonsterType;
    TextView tvMonsterRarity;
    TextView tvMonsterLikes;
    TextView tvMonsterWeakness;
    LinearLayout linearLayout;

    public static MonsterFragment newInstance(Serializable monster) {

        MonsterFragment fragment = new MonsterFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ser_monster", monster);

        return fragment;
    }

    public MonsterFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_monster, container, false);
        initViews();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadMonster((Monster) getArguments()
                .getSerializable("ser_monster")
        );
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
        linearLayout = (LinearLayout) view.findViewById(R.id.ll_monster_frag);
        ivMonsterPic = (ImageView) view.findViewById(R.id.iv_monster_pic);
        tvMonsterName = (TextView) view.findViewById(R.id.tv_monster_name);
        tvMonsterType = (TextView) view.findViewById(R.id.tv_monster_familiar);
        tvMonsterRarity = (TextView) view.findViewById(R.id.tv_monster_rarity);
        tvMonsterLikes = (TextView) view.findViewById(R.id.tv_monster_ability_1);
        tvMonsterWeakness = (TextView) view.findViewById(R.id.tv_monster_ability_2);
    }
}
