package johnsmith.haruhi.co.refrigerator.Unit;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import johnsmith.haruhi.co.refrigerator.Model.Unit.Item;
import johnsmith.haruhi.co.refrigerator.R;
import johnsmith.haruhi.co.refrigerator.databinding.CardviewBinding;

/**
 * Created by wj on 16/6/21.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {

    private List<Item> itemList = new ArrayList<>();
    private Context context;
    private DeleteListener listener;

    public CardViewAdapter(Context context, List<Item> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    public interface DeleteListener {
        void onDeleteClick(String name, String id);
    }

    public void setListener(DeleteListener deleteListener) {
        this.listener = deleteListener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, final int position) {
        holder.getBinding().CVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item = itemList.get(position);
                if (itemList.contains(item)) {
                    listener.onDeleteClick(item.getName(), item.getId());
                    itemList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, itemList.size());
                }
            }
        });
        Item item = itemList.get(position);
        setIcon(holder.getBinding().CVIV, item);
        holder.getBinding().CVSL.setShowMode(SwipeLayout.ShowMode.PullOut);
        holder.getBinding().CVName.setText(item.getName());
        holder.getBinding().CVTime.setText(item.getTime());
    }

    private void setIcon(ImageView imageView, Item item) {
        String day[] = item.getTime().split(" ")[0].split("/");
        String second[] = item.getTime().split(" ")[1].split(":");
        Calendar thatDay = Calendar.getInstance();
        thatDay.set(Calendar.YEAR, Integer.valueOf(day[0]));
        thatDay.set(Calendar.MONTH, Integer.valueOf(day[1]) - 1);
        thatDay.set(Calendar.DAY_OF_MONTH, Integer.valueOf(day[2]));
        thatDay.set(Calendar.HOUR_OF_DAY, Integer.valueOf(second[0]));
        thatDay.set(Calendar.MINUTE, Integer.valueOf(second[1]));
        thatDay.set(Calendar.SECOND, Integer.valueOf(second[2]));
        Calendar today = Calendar.getInstance();
        long days = (today.getTimeInMillis() - thatDay.getTimeInMillis()) / (24 * 60 * 60 * 1000);
        if (days >= 14) {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_sentiment_dissatisfied_36_w));
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorRed));
        } else if (days >= 3) {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_sentiment_neutral_36_w));
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorYello));
        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_sentiment_satisfied_36_w));
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorGreen));
        }
    }

    @Override
    public int getItemCount() {
        Log.d("getItemCount", "size: " + itemList.size());
        return itemList.size();
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
