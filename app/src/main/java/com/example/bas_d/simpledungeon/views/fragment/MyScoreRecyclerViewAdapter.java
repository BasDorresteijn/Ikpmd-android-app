package com.example.bas_d.simpledungeon.views.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bas_d.simpledungeon.R;
import com.example.bas_d.simpledungeon.dummy.DummyContent.DummyItem;
import com.example.bas_d.simpledungeon.model.Score;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link ScoreFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyScoreRecyclerViewAdapter extends RecyclerView.Adapter<MyScoreRecyclerViewAdapter.ViewHolder> {

    private List<Score> mValues;
    private final ScoreFragment.OnListFragmentInteractionListener mListener;

    private FirebaseDatabase database;

    public MyScoreRecyclerViewAdapter(ScoreFragment.OnListFragmentInteractionListener listener) {
        setupFirebase();
        mValues = new ArrayList<>();
        mListener = listener;
    }

    private void setupFirebase() {
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Score> scores = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    scores.add(data.getValue(Score.class));
                }
                Collections.sort(scores, new Comparator<Score>() {
                    @Override
                    public int compare(Score s1, Score s2) {
                        if (s1.getScore() > s2.getScore()) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                });
                for (Score score : scores) {
                    Log.d("SimpleDungeon", String.valueOf(score.getScore()));
                }
                mValues = scores;
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("SimpleDungeon", "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_score, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText(String.valueOf(mValues.get(position).getScore()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Score mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
