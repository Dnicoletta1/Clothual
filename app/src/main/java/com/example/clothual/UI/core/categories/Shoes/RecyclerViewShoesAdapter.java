package com.example.clothual.UI.core.categories.Shoes;


import android.app.Application;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothual.Model.Clothual;
import com.example.clothual.Model.Image;
import com.example.clothual.R;
import com.example.clothual.UI.core.categories.CategoryModel;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class RecyclerViewShoesAdapter extends RecyclerView.Adapter<RecyclerViewShoesAdapter.ShoesViewHolder> {

    private final List<Clothual> clothualList;
    private final  List<Image> imageList;
    private final ContentResolver contentResolver;
    private OnItemClickListener onItemClickListener;
    private Application application;
    public interface OnItemClickListener{
        //void onShoesClick(Clothual clothual);
        void buttonDelete(String notify);
    }

    public RecyclerViewShoesAdapter(List<Clothual> clothualList, List<Image> imageList, ContentResolver contentResolver,
                                    OnItemClickListener onItemClickListener, Application application){
        this.clothualList = clothualList;
        this.imageList = imageList;
        this.contentResolver = contentResolver;
        this.onItemClickListener = onItemClickListener;
        this.application = application;
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

    public class ShoesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ImageView imageView;

        private final TextView brand;
        private final TextView template;
        private final TextView color;
        private final TextView description;
        private final Button delite;
        private final CategoryModel model;

        public ShoesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewAdapterClothual);
            brand = itemView.findViewById(R.id.brand);
            template = itemView.findViewById(R.id.template);
            color = itemView.findViewById(R.id.color);
            description = itemView.findViewById(R.id.descritpion);
            delite = itemView.findViewById(R.id.delite);
            model = new CategoryModel(application);
            itemView.setOnClickListener(this);
            delite.setOnClickListener(this);

        }

        public void bind(Clothual clothual, String uri){
            brand.setText(clothual.getBrand());
            template.setText(clothual.getTemplate());
            color.setText(clothual.getColor());
            description.setText(clothual.getDescription());
            try {
                imageView.setImageBitmap(importImageFromMemory(contentResolver, Uri.parse(uri)));
            } catch (FileNotFoundException e) {
            }
        }

        public Bitmap importImageFromMemory(ContentResolver contentResolver, Uri imageUri) throws FileNotFoundException {
            InputStream inputStream = contentResolver.openInputStream(imageUri);
            return BitmapFactory.decodeStream(inputStream);
        }

        @Override
        public void onClick(View view) {
           /* if(view.getId() == R.id.delite){
                String string = clothualList.get(getAdapterPosition()).getBrand() + " " +
                        clothualList.get(getAdapterPosition()).getTemplate();
                model.deleteImage(imageList.get(getAdapterPosition()));
                model.deleteClothual(clothualList.get(getAdapterPosition()));
                imageList.remove(getAdapterPosition());
                clothualList.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                onItemClickListener.buttonDelete(string);
            }

            */
            switch (view.getId()){
                case R.id.delite:
                    String string = clothualList.get(getAdapterPosition()).getBrand() + " " +
                            clothualList.get(getAdapterPosition()).getTemplate();
                    model.deleteImage(imageList.get(getAdapterPosition()));
                    model.deleteClothual(clothualList.get(getAdapterPosition()));
                    imageList.remove(getAdapterPosition());
                    clothualList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    onItemClickListener.buttonDelete(string);
                    break;
                default:
                    break;
            }
        }
    }
}
