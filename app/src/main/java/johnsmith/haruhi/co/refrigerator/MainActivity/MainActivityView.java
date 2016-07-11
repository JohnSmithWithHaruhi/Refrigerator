package johnsmith.haruhi.co.refrigerator.MainActivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private List<Item> itemList = new ArrayList<>();

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new MainActivityViewModel(this);
        binding.setViewModel(viewModel);

        setRecyclerView();
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
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        cardViewAdapter = new CardViewAdapter(itemList);
        cardViewAdapter.setListener(viewModel);
        recyclerView.setAdapter(cardViewAdapter);
    }

}