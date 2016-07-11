package johnsmith.haruhi.co.refrigerator.Unit;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import johnsmith.haruhi.co.refrigerator.Model.Unit.Item;
import johnsmith.haruhi.co.refrigerator.R;
import johnsmith.haruhi.co.refrigerator.databinding.CardviewBinding;

/**
 * Created by wj on 16/6/21.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {

    private List<Item> itemList = new ArrayList<>();

    public CardViewAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Item item = itemList.get(position);
        Log.d("onBindViewHolder", "name: " + item.getName());
        holder.getBinding().CVName.setText(item.getName());
        holder.getBinding().CVTime.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        Log.d("getItemCount", "size: " + itemList.size());
        return itemList.size();
    }

    public void remove(int position){
        Item item = itemList.get(position);
        if (itemList.contains(item)) {
            itemList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        private CardviewBinding binding;

        public CardViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public CardviewBinding getBinding() {
            return binding;
        }
    }

}
