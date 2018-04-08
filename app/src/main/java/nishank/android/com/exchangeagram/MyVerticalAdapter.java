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

class MyVerticalAdapter extends android.support.v7.widget.RecyclerView.Adapter<MyVerticalAdapter.MyVerticalViewHolder> {
    Context context;
    ArrayList personNames;
    ArrayList personPost;


    public MyVerticalAdapter(Context context, ArrayList personNames,ArrayList personPost)
    {
        this.context=context;
        this.personNames=personNames;
        this.personPost=personPost;
    }

    @Override
    public MyVerticalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.user_post_item,parent,false);
        return new MyVerticalViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyVerticalViewHolder myViewHolder, int position) {
        String getName= (String) personNames.get(position);
        String getPost=(String)personPost.get(position);
        myViewHolder.display_name.setText(getName);
        myViewHolder.comment_user_name.setText(getName);
        myViewHolder.comment_user_post.setText(getPost);
    }

    @Override
    public int getItemCount() {
        return personNames.size();
    }

    public class MyVerticalViewHolder extends RecyclerView.ViewHolder {
        public TextView display_name;
        public TextView comment_user_name;
        public TextView comment_user_post;

        public MyVerticalViewHolder(View itemView){
            super(itemView);
            display_name=(TextView)itemView.findViewById(R.id.display_name);
            comment_user_name=(TextView)itemView.findViewById(R.id.username);
            comment_user_post=(TextView)itemView.findViewById(R.id.comment);
        }
    }

}