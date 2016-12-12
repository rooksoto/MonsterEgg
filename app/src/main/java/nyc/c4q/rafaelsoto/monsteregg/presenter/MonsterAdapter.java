package nyc.c4q.rafaelsoto.monsteregg.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;
import nyc.c4q.rafaelsoto.monsteregg.view.MonsterViewHolder;

/**
 * Created by shannonalexander-navarro on 12/10/16.
 */

public class MonsterAdapter extends RecyclerView.Adapter {

    List<Monster> monsterList = new ArrayList<>();


    public MonsterAdapter(List<Monster> caughtMonsters) {
//        caughtMonsters.add(MonsterDataProvider.monsterMap.get(0));
//        caughtMonsters.add(MonsterDataProvider.monsterMap.get(1));
//        caughtMonsters.add(MonsterDataProvider.monsterMap.get(2));
//        caughtMonsters.add(MonsterDataProvider.monsterList.get(3));
//        caughtMonsters.add(MonsterDataProvider.monsterList.get(4));
//        caughtMonsters.add(MonsterDataProvider.monsterList.get(5));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.monster_item, parent, false);
        return new MonsterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MonsterViewHolder viewHolder = (MonsterViewHolder) holder;

        Monster aMonster = monsterList.get(position);
        viewHolder.bind(aMonster);
    }

    @Override
    public int getItemCount() {
        return monsterList.size();
    }
}
