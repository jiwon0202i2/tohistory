package com.test.tohistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.tohistory.R;
import com.test.tohistory.toy;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private ToysAdapter mToysAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<toy> toys, List<String> keys){
        mContext = context;
        mToysAdapter = new ToysAdapter(toys, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mToysAdapter);

    }

    class ToyItemView extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private TextView mContent;

        private String key;

        public ToyItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.toy_list_item, parent,false));

            mTitle = (TextView) itemView.findViewById(R.id.title);
            mContent = (TextView) itemView.findViewById(R.id.content);

        }
        public  void bind(toy toy, String key){
            mTitle.setText(toy.getTitle());
            mContent.setText(toy.getContent());
            this.key = key;
        }
    }
    class ToysAdapter extends RecyclerView.Adapter<ToyItemView>{
        private List<toy> mToyList;
        private List<String> mKeys;

        public ToysAdapter(List<toy> mToyList, List<String> mKeys){
            this.mToyList = mToyList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public ToyItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ToyItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ToyItemView holder, int position) {
            holder.bind(mToyList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
