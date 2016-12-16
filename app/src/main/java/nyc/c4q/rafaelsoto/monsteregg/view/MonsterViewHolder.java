package nyc.c4q.rafaelsoto.monsteregg.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;
import static nyc.c4q.rafaelsoto.monsteregg.view.MainActivity.database;

public class MonsterViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    LinearLayout linearLayout;
    ImageView caughtMonsterImage;
    TextView caughtMonsterName;

    public MonsterViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cv_monster_item);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_monster_item);
        caughtMonsterImage = (ImageView) itemView.findViewById(R.id.caught_image);
        caughtMonsterName = (TextView) itemView.findViewById(R.id.caught_name);
    }

    private void removeMonster(Monster monster) {
        cupboard().withDatabase(database).delete(monster);
    }

    private void refreshMonsterList() {
        MainActivity.adapter.notifyDataSetChanged();
    }

    public void bind(final Monster aMonster) {

        caughtMonsterName.setText(aMonster.getName());
        Picasso.with(cardView.getContext()).load("file:///android_asset/" + aMonster.getImageAsset()).into(caughtMonsterImage);

        caughtMonsterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("ser_monster", aMonster);

                Fragment monsterFragment = MonsterFragment.newInstance(aMonster);
                monsterFragment.setArguments(bundle);

                FragmentManager fragmentManager = ((FragmentActivity) view
                        .getContext())
                        .getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();
                fragmentTransaction.replace(R.id.fl_fragment_holder, monsterFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        caughtMonsterImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                cupboard().withDatabase(database).get(aMonster); //getting cat from db
                removeMonster(aMonster);
                refreshMonsterList();
                return true;
            }
        });
    }
}
