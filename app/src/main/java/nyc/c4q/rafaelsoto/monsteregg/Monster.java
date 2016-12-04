package nyc.c4q.rafaelsoto.monsteregg;

/**
 * Created by shannonalexander-navarro on 12/4/16.
 */

public class Monster {

    private Long _id;
    private String name;
    private String type;
    private int rarity;
    private String likes;
    private String weakness;
    private Boolean isCollected;

    public Monster() {
    }

    public Monster(Long _id, String name, String type, int rarity, String likes, String weakness, Boolean isCollected) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.likes = likes;
        this.weakness = weakness;
        this.isCollected = isCollected;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public Boolean getCollected() {
        return isCollected;
    }

    public void setCollected(Boolean collected) {
        isCollected = collected;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", rarity=" + rarity +
                ", likes='" + likes + '\'' +
                ", weakness='" + weakness + '\'' +
                ", isCollected=" + isCollected +
                '}';
    }
}
