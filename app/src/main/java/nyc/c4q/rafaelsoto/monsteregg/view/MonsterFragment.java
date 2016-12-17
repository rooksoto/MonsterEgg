package nyc.c4q.rafaelsoto.monsteregg.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import static nyc.c4q.rafaelsoto.monsteregg.view.MainActivity.adapter;


public class MonsterFragment extends Fragment {
    View view;
    ImageView ivMonsterPic;
    TextView tvMonsterName;
    TextView tvMonsterType;
    TextView tvMonsterRarity;
    TextView tvMonsterLikes;
    TextView tvMonsterWeakness;
    LinearLayout linearLayout;
    EditText editText;
    TextView button;
    public static final String SHARED_PREF = "nyc.c4q.user.pref";
    public static final String TAG = "user input";


    public static MonsterFragment newInstance(Serializable monster) {

        MonsterFragment fragment = new MonsterFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ser_monster", monster);

        return fragment;
    }

    public MonsterFragment() {

    }

    public void refreshMonsterList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_monster, container, false);
        initViews();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String monsterName = editText.getText().toString();

                if (!monsterName.isEmpty()) {
                    tvMonsterName.setText(monsterName);
                    refreshMonsterList();
//                    SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREF, MODE_PRIVATE).edit();
//                    editor.putString(getIntent().getExtras().getString(TAG), monsterName);
                  //  editor.apply();
                }
            }
        });

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
        button = (TextView) view.findViewById(R.id.save_name_btn);
        editText = (EditText) view.findViewById(R.id.edit_name);
        linearLayout = (LinearLayout) view.findViewById(R.id.ll_monster_frag);
        ivMonsterPic = (ImageView) view.findViewById(R.id.iv_monster_pic);
        tvMonsterName = (TextView) view.findViewById(R.id.tv_monster_name);
        tvMonsterType = (TextView) view.findViewById(R.id.tv_monster_type);
        tvMonsterRarity = (TextView) view.findViewById(R.id.tv_monster_rarity);
        tvMonsterLikes = (TextView) view.findViewById(R.id.tv_monster_likes);
        tvMonsterWeakness = (TextView) view.findViewById(R.id.tv_monster_weakness);

    }

}
