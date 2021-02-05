package co.com.ceiba.mobile.pruebadeingreso.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.viewModel.ItemViewModel;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.CustomViewHolder> implements Filterable {

    private List<User> items;
    private List<User> contactListFiltered;
    private Context context;
    private OnUsersListener usersListener;

    public UserRecyclerAdapter(Context context, List<User> items, OnUsersListener usersListener) {
        this.context = context;
        this.items = items;
        this.contactListFiltered = items;
        this.usersListener = usersListener;
    }

    @NonNull
    @Override
    public UserRecyclerAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        UserListItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new CustomViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerAdapter.CustomViewHolder holder, int position) {
        ItemViewModel itemViewModel = new ItemViewModel();
        UserListItemBinding item = holder.binding;
        itemViewModel.setData(items.get(position));
        item.setViewModel(itemViewModel);
        item.btnViewPost.setOnClickListener(v -> usersListener.onItemClick(items.get(position)));
    }

    public void setItems(List<User> items) {
        this.contactListFiltered = items;
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    items = contactListFiltered;
                } else {
                    List<User> filteredList = new ArrayList<>();
                    for (User row : contactListFiltered) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(row);
                        }
                    }

                    items = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = items;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                validateFilter((ArrayList<User>) filterResults.values);
            }
        };
    }

    private void validateFilter(List<User> users){
        items = users;
        notifyDataSetChanged();
        if(users.isEmpty()){
            usersListener.isEmpty(View.VISIBLE);
        }else {
            usersListener.isEmpty(View.INVISIBLE);
        }
    }

    public interface OnUsersListener{
        void onItemClick(User user);
        void isEmpty(int view);
    }


    @Override
    public int getItemViewType(int position) {
        return R.layout.user_list_item;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        private UserListItemBinding binding;

        public CustomViewHolder(@NonNull UserListItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

}
