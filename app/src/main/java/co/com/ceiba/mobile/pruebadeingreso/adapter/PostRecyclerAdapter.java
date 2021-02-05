package co.com.ceiba.mobile.pruebadeingreso.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.databinding.PostListItemBinding;
import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.viewModel.ItemPostViewModel;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.CustomViewHolder> {

    private List<Post> items;
    private Context context;

    public PostRecyclerAdapter(Context context, List<Post> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public PostRecyclerAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PostListItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new CustomViewHolder(itemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull PostRecyclerAdapter.CustomViewHolder holder, int position) {
        ItemPostViewModel itemViewModel = new ItemPostViewModel();
        PostListItemBinding item = holder.binding;
        itemViewModel.setData(items.get(position));
        item.setViewModel(itemViewModel);
    }

    public void setItems(List<Post> items) {
        this.items = items;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        return R.layout.post_list_item;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        private PostListItemBinding binding;

        public CustomViewHolder(@NonNull PostListItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

}
