package nyc.c4q.rafaelsoto.monsteregg.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rook on 12/5/16.
 */




//This class stores all of our Monster objects in a List and in a Map
public class MonsterDataProvider {

    public static List<Monster> monsterList;
    public static Map<String, Monster> monsterMap;

    static {
        monsterList = new ArrayList<>();
        monsterMap = new HashMap<>();

        //Super Rare Monsters
        addMonster(new Monster("Cthulhu", "Old One", "Super Rare", "Destroying Worlds", "NOTHING!", "sr_cthulhu"));
        addMonster(new Monster("Bruce", "Ghost", "Super Rare", "His Parents", "Muggers", "sr_bruce"));
        addMonster(new Monster("Slaanesh", "Old One", "Super Rare", "Enslaving Species", "Cthulhu", "sr_slaanesh"));
        addMonster(new Monster("Thoth", "Old One", "Super Rare", "Genocide", "Hope", "sr_thoth"));

        //Rare Monsters
        addMonster(new Monster("Bambi", "Ghost", "Rare", "Mommy", "Hunters", "r_bambi"));
        addMonster(new Monster("Gossamer", "Boogeyman", "Rare", "Haunted Houses", "Wascally Wabbits", "r_gossamer"));
        addMonster(new Monster("Vlad", "Boogeyman", "Rare", "Necks", "Garlic", "r_vlad"));
        addMonster(new Monster("Ringo", "Boogeyman", "Rare", "Yellow Submarines", "Solo Career", "r_ringo"));
        addMonster(new Monster("Cthylla", "Old One", "Rare", "Eating Children", "Daddy", "r_cthylla"));
        addMonster(new Monster("Lucky", "Ghost", "Rare", "Hopping", "Hugs", "r_lucky"));
        addMonster(new Monster("Glaaki", "Old One", "Rare", "Torturing Humans", "Salt", "r_glaaki"));
        addMonster(new Monster("Bert & Ernie", "Boogeyman", "Rare", "Rubber Ducks", "Cancer", "r_bert_ernie"));
        addMonster(new Monster("Jack", "Boogeyman", "Rare", "Halloween", "Christmas", "r_jack"));
        addMonster(new Monster("Mikey", "Boogeyman", "Rare", "University", "Randall Boggs", "r_mikey"));

        //Normal Monsters
        addMonster(new Monster("Needle", "Ghost", "Common", "Thread", "Thimbles", "c_needle"));
        addMonster(new Monster("Drumstick", "Boogeyman", "Common", "Farms", "Colonels", "c_drumstick"));
        addMonster(new Monster("Cootie", "Disease", "Common", "Children", "Vaccinations", "c_cootie"));
        addMonster(new Monster("Stitches", "Disease", "Common", "Playgrounds", "Rubber Mats", "c_stitches"));
        addMonster(new Monster("Weezer", "Disease", "Common", "Cigarettes", "Clean Air", "c_weezer"));
        addMonster(new Monster("Matthew", "Boogeyman", "Common", "Bad Advice", "Women", "c_matthew"));
        addMonster(new Monster("Rash", "Disease", "Common", "Great Skin", "Aveeno", "r_rash"));
        addMonster(new Monster("Jinx", "Boogeyman", "Common", "Harry Potter", "Lord Voldemort", "c_jinx"));
        addMonster(new Monster("Legionella", "Disease", "Common", "Steam", "Water", "c_legionella"));
        addMonster(new Monster("Thorn Thing", "Boogeyman", "Common", "Brambles", "Gloves", "c_thorn_thing"));
        addMonster(new Monster("Bindi", "Ghost", "Common", "Vishnu", "Kali", "c_bindi"));
        addMonster(new Monster("Fleek", "Ghost", "Common", "Kim Kardashian", "Tweezers", "c_fleek"));
        addMonster(new Monster("Toxie", "Disease", "Common", "Cleopatra", "Antivenin", "c_toxie"));
        addMonster(new Monster("Poppy", "Ghost", "Common", "Eating Children", "Kittens", "c_poppy"));
        addMonster(new Monster("Gangreen", "Disease", "Common", "Eating Limbs", "Kittens", "c_poppy"));
        addMonster(new Monster("Gingivitis", "Disease", "Common", "Gums", "Dentists", "c_gingivitis"));
        addMonster(new Monster("Pimple", "Disease", "Common", "Pretty Faces", "Soap", "c_pimple"));
        addMonster(new Monster("Itis", "Disease", "Common", "Big Meals", "Naps", "c_itis"));
        addMonster(new Monster("Happy", "Ghost", "Common", "Parties", "Grumpy People", "c_happy"));
        addMonster(new Monster("Migraine", "Disease", "Common", "Loud Noises", "Aspirin", "c_migraine"));
        addMonster(new Monster("Kraken", "Boogeyman", "Common", "Killing Sailors", "Fishermen", "c_kraken"));
        addMonster(new Monster("Minotaur", "Boogeyman", "Common", "Eating Children", "Theseus", "c_minotaur"));
        addMonster(new Monster("Knock-Knock", "Ghost", "Common", "Dropping Plates", "Nightlights", "c_knock_knock"));

    }

    private static void addMonster(Monster monster) {
        monsterList.add(monster);
        monsterMap.put(monster.getName(), monster);
    }
}
