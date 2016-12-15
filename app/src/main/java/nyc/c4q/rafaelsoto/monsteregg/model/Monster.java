package nyc.c4q.rafaelsoto.monsteregg.model;

import org.parceler.Parcel;

/**
 * Created by rook on 12/5/16.
 */
@Parcel
public class Monster  {

    private long _id;
    private String name;
    private String type;
    private String rarity;
    private String likes;
    private String weakness;
    private String imageAsset;

    public Monster() {
    }

    public Monster(String name, String type, String rarity, String likes, String weakness, String imageAsset) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.likes = likes;
        this.weakness = weakness;
        this.imageAsset = imageAsset;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
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

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
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

    public String getImageAsset() {
        return imageAsset;
    }

    public void setImageAsset(String imageAsset) {
        this.imageAsset = imageAsset;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", rarity='" + rarity + '\'' +
                ", likes='" + likes + '\'' +
                ", weakness='" + weakness + '\'' +
                ", imageAsset='" + imageAsset + '\'' +
                '}';
    }
}
