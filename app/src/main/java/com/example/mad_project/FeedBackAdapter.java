package com.example.mad_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FeedBackAdapter extends ArrayAdapter<FeedBack> {

    private Context context;
    private int resource;
    List<FeedBack> feedbacks;

    FeedBackAdapter(Context context, int resource, List<FeedBack> feedbacks){
        super(context,resource,feedbacks);
        this.context = context;
        this.resource = resource;
        this.feedbacks = feedbacks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView title = row.findViewById(R.id.title);
        TextView description = row.findViewById(R.id.description);


        FeedBack feedBack = feedbacks.get(position);
        title.setText(feedBack.getTitle());
        description.setText(feedBack.getDescription());

        return row;
    }
}
