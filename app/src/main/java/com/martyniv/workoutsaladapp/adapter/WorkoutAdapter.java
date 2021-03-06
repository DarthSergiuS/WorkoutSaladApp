package com.martyniv.workoutsaladapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.martyniv.workoutsaladapp.R;
import com.martyniv.workoutsaladapp.model.WorkoutItem;

import java.util.List;



public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutHolder> {

    private List<WorkoutItem> data;
    private LayoutInflater inflater;

    private ItemClickCallBack itemClickCallBack;

    public interface ItemClickCallBack{
        void onItemClick(int p);
        void onButtonClick(int p);
    }

    public void setItemClickCallBack(final ItemClickCallBack itemClickCallBack){
        this.itemClickCallBack = itemClickCallBack;
    }

    public WorkoutAdapter(List<WorkoutItem> data, Context c){
        this.inflater =LayoutInflater.from(c);
        this.data = data;

    }

    @Override
    public WorkoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.list_item,parent,false);
        return new WorkoutHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkoutHolder holder, int position) {
        WorkoutItem item =data.get(position);
        switch (item.getType()){
            case "chest":holder.cardView.setBackgroundResource(R.color.chest);
                break;
            case "biceps":holder.cardView.setBackgroundResource(R.color.biceps);
                break;
            case "triceps":holder.cardView.setBackgroundResource(R.color.triceps);
                break;
            case "back":holder.cardView.setBackgroundResource(R.color.back);
                break;

        }
        holder.title.setText(item.getItemTitle());
        holder.subTitle.setText(item.getType());
        holder.icon.setImageResource(android.R.drawable.ic_menu_add);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class WorkoutHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title;
        private TextView subTitle;
        private ImageView icon;
        private CardView cardView;
        private Button load;
        private View container;

        WorkoutHolder(View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.lbl_item_text);
            subTitle = (TextView)itemView.findViewById(R.id.lbl_item_sub_title);
            icon = (ImageView)itemView.findViewById(R.id.img_item_icon);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
            container = itemView.findViewById(R.id.cont_item_root);
            container.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cont_item_root :
                    itemClickCallBack.onItemClick(getAdapterPosition());
                    break;

            }
        }
    }

}
