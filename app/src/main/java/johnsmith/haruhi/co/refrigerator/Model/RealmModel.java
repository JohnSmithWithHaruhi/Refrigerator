package johnsmith.haruhi.co.refrigerator.Model;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import johnsmith.haruhi.co.refrigerator.Model.Unit.Item;

/**
 * Created by wj on 16/6/21.
 */
public class RealmModel {

    private Realm realm;
    private RealmResults<Item> results;

    public RealmModel() {
        realm = Realm.getDefaultInstance();
    }

    public RealmResults<Item> getAllData() {
        RealmQuery query = realm.where(Item.class);
        results = query.findAll();
        return results;
    }

    public Item getDataByID(String id) {
        return realm.where(Item.class).equalTo("id", id).findFirst();
    }

    public void setData(String id, String name, String time) {
        realm.beginTransaction();
        Item item = realm.createObject(Item.class, id);
        item.setName(name);
        item.setTime(time);
        realm.commitTransaction();
    }

    public void deleteDataByID(String id) {
        realm.beginTransaction();
        realm.where(Item.class).equalTo("id", id).findFirst().deleteFromRealm();
        realm.commitTransaction();
    }

    public void editNameByID(String id, String value) {
        realm.beginTransaction();
        realm.where(Item.class).equalTo("id", id).findFirst().setName(value);
        realm.commitTransaction();
    }

    public void deleteAllData() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

}
