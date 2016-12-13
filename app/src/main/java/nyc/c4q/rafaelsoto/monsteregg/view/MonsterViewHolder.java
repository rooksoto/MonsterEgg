package nyc.c4q.rafaelsoto.monsteregg.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;

public class MonsterViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    ImageView caughtMonsterImage;
    TextView caughtMonsterName;

    MonsterFragment monsterFragment;

    public MonsterViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cv_monster_item);
        caughtMonsterImage = (ImageView) itemView.findViewById(R.id.caught_image);
        caughtMonsterName = (TextView) itemView.findViewById(R.id.caught_name);

    }

    public void bind(final Monster aMonster) {

        caughtMonsterName.setText(aMonster.getName());
        Picasso.with(itemView.getContext()).load("file:///android_asset/" + aMonster.getImageAsset()).into(caughtMonsterImage);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), aMonster.getName() + " was clicked.", Toast.LENGTH_SHORT).show();

//                monsterFragment = new MonsterFragment();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("monster", aMonster);
//                monsterFragment.setArguments(bundle);
//                FragmentManager fragmentManager = monsterFragment.getActivity().getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.ll_fragment_holder, monsterFragment).commit();
            }
        });
    }
}
