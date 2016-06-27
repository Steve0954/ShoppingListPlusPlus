package com.udacity.firebase.shoppinglistplusplus.ui.activeLists;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingList;

/**
 * Created by Steve on 5/19/2016.
 */
public class ActiveListAdapter extends FirebaseListAdapter<ShoppingList> {

    /**
     * Public constructor that initializes private instance variables when adapter is created
     */

        public ActiveListAdapter(Activity activity, Class<ShoppingList> modelClass, int modelLayout,
                                  Query ref) {
            super(activity, modelClass, modelLayout, ref);
            this.mActivity = activity;
        }

    @Override
    protected void populateView(View view, ShoppingList list) {

        /**
         * Grab the needed TextView and strings
         */
        TextView textViewListName = (TextView) view.findViewById(R.id.text_view_list_name);
        TextView textViewCreatedByUser = (TextView) view.findViewById(R.id.text_view_created_by_user);

        /* Set the list name and owner */
        textViewListName.setText(list.getListName());
        textViewCreatedByUser.setText(list.getOwner());

    }
}
