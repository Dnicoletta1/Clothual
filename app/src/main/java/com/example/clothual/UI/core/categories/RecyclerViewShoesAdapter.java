package com.example.clothual.UI.core.categories;


import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothual.Model.Clothual;
import com.example.clothual.Model.Image;
import com.example.clothual.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class RecyclerViewShoesAdapter extends RecyclerView.Adapter<RecyclerViewShoesAdapter.ShoesViewHolder> {

    private final List<Clothual> clothualList;
    private final  List<Image> imageList;
    private final ContentResolver contentResolver;

    public RecyclerViewShoesAdapter(List<Clothual> clothualList, List<Image> imageList, ContentResolver contentResolver){
        this.clothualList = clothualList;
        this.imageList = imageList;
        this.contentResolver = contentResolver;
    }

    @NonNull
    @Override
    public ShoesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clothual_adapter,
                parent, false);
        return  new ShoesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoesViewHolder holder, int position) {
        int image = clothualList.get(position).getIdImage();
        if(imageList.get(position).getID() == image){
            holder.bind(clothualList.get(position), imageList.get(position).getUri());
        }
    }

    @Override
    public int getItemCount() {
        if(clothualList != null){
            return clothualList.size();
        }
        return 0;
    }

    public class ShoesViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView type;
        private final TextView brand;
        private final TextView template;
        private final TextView color;
        private final TextView description;

        public ShoesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewAdapterClothual);
            type = (TextView) itemView.findViewById(R.id.type);
            brand = (TextView) itemView.findViewById(R.id.brand);
            template = (TextView) itemView.findViewById(R.id.template);
            color = (TextView) itemView.findViewById(R.id.color);
            description = (TextView) itemView.findViewById(R.id.descritpion);
        }

        public void bind(Clothual clothual, String uri){
            type.setText(clothual.getType());
            brand.setText(clothual.getBrand());
            template.setText(clothual.getTemplate());
            color.setText(clothual.getColor());
            description.setText(clothual.getDescription());
            try {
                imageView.setImageBitmap(importImageFromMemory(contentResolver, Uri.parse(uri)));
            } catch (FileNotFoundException e) {
                imageView.setImageResource(R.drawable.ic_launcher_background);
            }
        }

        public Bitmap importImageFromMemory(ContentResolver contentResolver, Uri imageUri) throws FileNotFoundException {
            InputStream inputStream = contentResolver.openInputStream(imageUri);
            return BitmapFactory.decodeStream(inputStream);
        }

    }
}
