package com.example.chris.memegenerator.catergory;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.chris.memegenerator.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  Admin on 12/22/2017.
 */

public class CategoryRCAdapter extends RecyclerView.Adapter<CategoryRCAdapter.ViewHolder> {
    List<MemesCategory> memesCategoryList=new ArrayList<>();

    public CategoryRCAdapter(List<MemesCategory> memesCategoryList) {
        this.memesCategoryList = memesCategoryList;
    }

    @Override
    public CategoryRCAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_interest_pick, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoryRCAdapter.ViewHolder holder, final int position) {
holder.tvCategoryname.setText(memesCategoryList.get(position).getCategory());
//in some cases, it will prevent unwanted situations
        holder.cbCategory.setOnCheckedChangeListener(null);

        //if true, your checkbox will be selected, else unselected

holder.cbCategory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Log.d("Check", "onCheckedChanged: "+b+" "+ holder.tvCategoryname.getText());
    }
});
    }

    @Override
    public int getItemCount() {
        return memesCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvCategoryname;
        private final CheckBox cbCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCategoryname = itemView.findViewById(R.id.tvCategoryName);
            cbCategory = itemView.findViewById(R.id.cbCatCheck);
        }
    }
}
