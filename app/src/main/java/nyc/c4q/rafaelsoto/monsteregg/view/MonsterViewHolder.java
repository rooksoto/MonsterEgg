package nyc.c4q.rafaelsoto.monsteregg.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;

/**
 * Created by shannonalexander-navarro on 12/10/16.
 */

public class MonsterViewHolder extends RecyclerView.ViewHolder {

    LinearLayout layout;
    ImageView caughtMonsterImage;
    TextView caughtMonsterName;

    public MonsterViewHolder(View itemView) {
        super(itemView);

        layout = (LinearLayout) itemView.findViewById(R.id.layout);
        caughtMonsterImage = (ImageView) itemView.findViewById(R.id.caught_image);
        caughtMonsterName = (TextView) itemView.findViewById(R.id.caught_name);
    }

    public void bind(Monster aMonster) {
        // caughtMonsterName.set
        caughtMonsterName.setText(aMonster.getName());
    }
}
