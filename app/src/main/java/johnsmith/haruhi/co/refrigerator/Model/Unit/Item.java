package johnsmith.haruhi.co.refrigerator.Model.Unit;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by wj on 16/6/21.
 */

public class Item extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
