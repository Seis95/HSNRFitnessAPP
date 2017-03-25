package com.example.denni.hsnrfitnessapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;



public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    final private Adapter.listener listener;
    private Context context;
    private List<FitnessElemente> ElementeList;
    private List<FitnessElemente> Elemente;

    @Override
    public int getItemCount() {
        return 0;
    }

    public Adapter(List<FitnessElemente> fitnessElementeList, Adapter.listener Listener) {
        this.listener = Listener;
        this.Elemente = fitnessElementeList;
        ElementeList = Elemente;

    }

    public List<FitnessElemente> getList() {

        return Elemente;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup v, int t) {
        context = v.getContext();
        int layout = R.layout.item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, v, false);
        Holder viewHolder = new Holder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(ElementeList.get(position));
    }



    public interface listener {
        void onListItemClick(int i);
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView tv_name;
        TextView tv_current;
        TextView tv_höchst;
        public Holder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_current = (TextView) itemView.findViewById(R.id.tv_current);
            tv_höchst = (TextView) itemView.findViewById(R.id.tv_höchst);
            itemView.setOnClickListener(this);


        }

        void bind(FitnessElemente fitnessElemente) {
            tv_name.setText(fitnessElemente.getName());
            tv_current.setText(String.valueOf(fitnessElemente.getAktuellerwert()));
            tv_höchst.setText(String.valueOf(fitnessElemente.getHöchstwert()));

        }

        @Override
        public void onClick(View v) {
            int Position = getAdapterPosition();
            listener.onListItemClick(Position);
        }
    }


}
