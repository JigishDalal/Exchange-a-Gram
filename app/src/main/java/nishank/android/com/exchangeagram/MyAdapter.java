package nishank.android.com.exchangeagram;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dell on 4/8/2018.
 */

class MyAdapter extends android.support.v7.widget.RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList personNames;
    Context context;
    public MyAdapter(Context context, ArrayList personNames) {
        this.personNames=personNames;
        this.context=context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.story_list_item,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        String getName= (String) personNames.get(position);
        myViewHolder.name.setText(getName);
    }

    @Override
    public int getItemCount() {
        return personNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public MyViewHolder(View itemView){
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.personName);
        }
    }

}
