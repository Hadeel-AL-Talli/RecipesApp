package com.example.recipesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {
    int [ ] arr;
    public CategoriesAdapter(int []arr){
        this.arr = arr;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_view,parent,false);
    MyViewHolder myViewHolder = new MyViewHolder(v);
    return myViewHolder;
    }




    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     holder.imageView.setImageResource(arr[position]);

     switch (position){
         case 0 : holder.textView.setText("breakfast"); break;
         case 1 : holder.textView.setText("chicken");  break;
         case 2 : holder.textView.setText("cookies");break;
         case 3: holder.textView.setText("pasta"); break;
         case 4 : holder.textView.setText("dinner");break;
         case 5 : holder.textView.setText("seafood");break;
         case 6 : holder.textView.setText("steak"); break;

     }
    }

    @Override
    public int getItemCount() {
        return arr.length;
    }



  public class MyViewHolder extends RecyclerView.ViewHolder{
      ImageView imageView;
      TextView textView;

      public MyViewHolder(@NonNull View itemView) {
          super(itemView);
          imageView = itemView.findViewById(R.id.imageView);
          textView = itemView.findViewById(R.id.TextView);

      }
  }
}

