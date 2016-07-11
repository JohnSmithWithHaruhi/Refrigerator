package johnsmith.haruhi.co.refrigerator.MainActivity;

import android.view.View;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

import johnsmith.haruhi.co.refrigerator.Model.RealmModel;
import johnsmith.haruhi.co.refrigerator.Model.Unit.Item;
import johnsmith.haruhi.co.refrigerator.Unit.CardViewAdapter;
import johnsmith.haruhi.co.refrigerator.Unit.InputFragment;

/**
 * Created by wj on 16/6/21.
 */

public class MainActivityViewModel implements InputFragment.InputFragmentListener, CardViewAdapter.DeleteListener {

    private MainActivityView view;
    private RealmModel realmModel;
    private InputFragment inputFragment;

    public MainActivityViewModel(MainActivityView view) {
        this.view = view;
        realmModel = new RealmModel();
        inputFragment = new InputFragment();
        inputFragment.setListener(this);
    }

    public void onViewCreate() {
        view.dataSetChanged(realmModel.getAllData());
    }


    public void onFABClick(View view) {
        inputFragment.show(this.view.getFragmentManager(), "inputFragment");
    }

    public void onFABLongClick() {
        realmModel.deleteAllData();
        view.dataDeleteAll();
    }

    @Override
    public void onTextSet(String text) {
        Date date = new Date();
        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setName(text);
        item.setTime(DateFormat.getDateTimeInstance().format(date));
        this.view.dataAdd(item);
        realmModel.setData(item.getId(), item.getName(), item.getTime());
        inputFragment.dismiss();
    }

    @Override
    public void onDeleteClick(String id) {
        realmModel.deleteDataByID(id);
    }
}
