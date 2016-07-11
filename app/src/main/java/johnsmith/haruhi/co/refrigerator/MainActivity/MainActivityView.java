package johnsmith.haruhi.co.refrigerator.MainActivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import johnsmith.haruhi.co.refrigerator.Model.Unit.Item;
import johnsmith.haruhi.co.refrigerator.R;
import johnsmith.haruhi.co.refrigerator.Unit.CardViewAdapter;
import johnsmith.haruhi.co.refrigerator.databinding.ActivityMainBinding;

public class MainActivityView extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CardViewAdapter cardViewAdapter;
    private ItemTouchHelper itemTouchHelper;
    private ItemTouchHelper.SimpleCallback simpleCallback;
    private List<Item> itemList = new ArrayList<>();

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new MainActivityViewModel(this);
        binding.setViewModel(viewModel);

        setRecyclerView();
        setItemTouchHelper();

        viewModel.onViewCreate();

        binding.mainFAB.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                viewModel.onFABLongClick();
                return true;
            }
        });
    }

    public void dataSetChanged(List<Item> itemList) {
        this.itemList.clear();
        this.itemList.addAll(itemList);
        cardViewAdapter.notifyDataSetChanged();
    }

    public void dataDeleteAll() {
        itemList.clear();
        cardViewAdapter.notifyDataSetChanged();
    }

    public void dataAdd(Item item) {
        itemList.add(item);
        cardViewAdapter.notifyDataSetChanged();
    }

    private void setRecyclerView() {
        recyclerView = binding.mainRV;
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        cardViewAdapter = new CardViewAdapter(itemList);
        recyclerView.setAdapter(cardViewAdapter);
    }

    private void setItemTouchHelper() {
        simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                //((CardViewAdapter) recyclerView.getAdapter()).remove(position);
            }
        };

        itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}