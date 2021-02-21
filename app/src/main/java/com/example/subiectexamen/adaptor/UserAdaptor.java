package com.example.subiectexamen.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.subiectexamen.R;
import com.example.subiectexamen.model.User;

import java.util.List;

public class UserAdaptor extends ArrayAdapter<User> {

    private List<User> users;
    private int resource;

    public UserAdaptor(@NonNull Context context, int resource, @NonNull List<User> users) {
        super(context, resource, users);
        this.users = users;
        this.resource = resource;
    }

    private static class ViewHolder {
        TextView timeTv;
        TextView usernameTv;
        TextView gen;
        TextView functie;
        TextView password;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        User user = getItem(position);
        ViewHolder viewHolder;
        View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(resource, parent, false);

            viewHolder.timeTv = convertView.findViewById(R.id.time);
            viewHolder.gen = convertView.findViewById(R.id.functie);
            viewHolder.functie = convertView.findViewById(R.id.gen);
            viewHolder.usernameTv = convertView.findViewById(R.id.username);
            viewHolder.password = convertView.findViewById(R.id.password);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.timeTv.setText(String.valueOf(user.getTime()));
        viewHolder.gen.setText(user.getGen());
        viewHolder.functie.setText(user.getFunctie());
        viewHolder.usernameTv.setText(user.getUsername());
        viewHolder.password.setText(user.getPassword());

        return result;
    }
}
