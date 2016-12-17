package nyc.c4q.rafaelsoto.monsteregg.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;



public class MonsterFragment extends Fragment  {
    View view;
    FloatingActionButton fabEditName;
    ImageView ivMonsterPic;
    EditText etMonsterName;
    TextView tvMonsterType;
    TextView tvMonsterRarity;
    TextView tvMonsterLikes;
    TextView tvMonsterWeakness;
    LinearLayout linearLayout;

    static Monster thisMonster;

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

        thisMonster = loadMonster((Monster) getArguments()
                .getSerializable("ser_monster")
        );

        fabEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMonsterName();
            }
        });
    }

    private void editMonsterName() {
        etMonsterName.setEnabled(true);
        etMonsterName.requestFocus();
        etMonsterName.setSelection(etMonsterName
                .getText()
                .length()
        );
        etMonsterName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                etMonsterName.setEnabled(false);
                etMonsterName.setText(etMonsterName.getText());
                cupboard().withDatabase(MainActivity.database).get(thisMonster._id);
                thisMonster.setName(etMonsterName.getText().toString());
                cupboard().withDatabase(MainActivity.database).put(thisMonster);
                return false;
            }
        });

    }

    public Monster loadMonster(Monster monster) {

        Picasso.with(getContext()).load("file:///android_asset/" + monster.getImageAsset()).into(ivMonsterPic);

        etMonsterName.setText(monster.getName());
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

        return thisMonster;
    }

    private void initViews() {
        fabEditName = (FloatingActionButton) view.findViewById(R.id.fab_edit_name);
        linearLayout = (LinearLayout) view.findViewById(R.id.ll_monster_frag);
        ivMonsterPic = (ImageView) view.findViewById(R.id.iv_monster_pic);
        etMonsterName = (EditText) view.findViewById(R.id.et_monster_name);
        tvMonsterType = (TextView) view.findViewById(R.id.tv_monster_type);
        tvMonsterRarity = (TextView) view.findViewById(R.id.tv_monster_rarity);
        tvMonsterLikes = (TextView) view.findViewById(R.id.tv_monster_likes);
        tvMonsterWeakness = (TextView) view.findViewById(R.id.tv_monster_weakness);
    }
}
