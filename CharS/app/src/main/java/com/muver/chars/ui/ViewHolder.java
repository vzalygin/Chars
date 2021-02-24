package com.muver.chars.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.muver.chars.R;
import com.muver.chars.util.EncodingType;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView type;
    private TextView key;

    private ViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        type = itemView.findViewById(R.id.type);
        key = itemView.findViewById(R.id.key);
    }

    public void bind(String name, String type, String key) {
        this.name.setText(name);
        this.key.setText(key);
        this.type.setText(type);
    }

    static ViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }
}
