package nyc.c4q.rafaelsoto.monsteregg.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.parceler.Parcels;

import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;

/**
 * Created by shannonalexander-navarro on 12/10/16.
 */

public class MonsterViewHolder extends RecyclerView.ViewHolder {

    LinearLayout layout;
    ImageView caughtMonsterImage;
    TextView caughtMonsterName;
    public static final String MONSTER = "monster";

    public MonsterViewHolder(View itemView) {
        super(itemView);

        layout = (LinearLayout) itemView.findViewById(R.id.layout);
        caughtMonsterImage = (ImageView) itemView.findViewById(R.id.caught_image);
        caughtMonsterName = (TextView) itemView.findViewById(R.id.caught_name);
    }

    public void bind(final Monster aMonster) {

        caughtMonsterName.setText(aMonster.getName());
        caughtMonsterName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MonsterFragment m = new MonsterFragment();

                Bundle bundle = new Bundle();
                bundle.putParcelable(MONSTER, Parcels.wrap(aMonster));
                m.setArguments(bundle);

                FragmentManager fm = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.main, m).commit();


            }
        });
    }
}
