package com.udacity.firebase.shoppinglistplusplus.ui.activeListDetails;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.client.ServerValue;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingList;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingListItem;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.HashMap;

/**
 * Created by Steve on 6/27/2016.
 */
public class ActiveListItemAdapter extends FirebaseListAdapter<ShoppingListItem> {
    /* Added 2.19 */
    private ShoppingList mShoppingList;
    private String mListId;

    public ActiveListItemAdapter(Activity activity, Class<ShoppingListItem> modelClass, int modelLayout,
                                 Query ref, String listId) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
        this.mListId = listId;
    }

    /**
     * Public method that is used to pass shoppingList object when it is loaded in ValueEventListener
     */

    public void setShoppingList(ShoppingList shoppingList) {
        this.mShoppingList = shoppingList;
        this.notifyDataSetChanged();
    }

    @Override
    protected void populateView(View view, final ShoppingListItem item, int position){

        /**
         * Grab the needed TextView and Strings
         */

        ImageButton buttonRemoveItem = (ImageButton) view.findViewById(R.id.button_remove_item);
        TextView textViewMealItemName = (TextView) view.findViewById(R.id.text_view_active_list_item_name);

        textViewMealItemName.setText(item.getItemName());

        /**
         * Get the id of the item to remove - Added 2.19
         */

        final String itemToRemoveId = this.getRef(position).getKey();


        /**
         * Set the on click listener for "Remove list item" button
         */

        buttonRemoveItem.setOnClickListener((new View.OnClickListener() {
            @Override
            public  void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mActivity, R.style.CustomTheme_Dialog)
                        .setTitle(mActivity.getString(R.string.remove_item_option))
                        .setMessage(mActivity.getString(R.string.dialog_message_are_you_sure_remove_item))
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            /* Added in 2.19 */
                                removeItem(itemToRemoveId);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public  void onClick(DialogInterface dialog, int which){
                                /* Dismiss the dialog */
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }));
    }

    private void removeItem(String itemId){
        /* Added in 2.19 */
        Firebase firebaseRef = new Firebase(Constants.FIREBASE_URL);

        /* Make a map for the removal */
        HashMap<String, Object> updatedRemoveItemMap = new HashMap<String, Object>();

        /* Remove the item by passing null */
        updatedRemoveItemMap.put("/" + Constants.FIREBASE_LOCATION_SHOPPING_LIST_ITEMS + "/"
                + mListId + "/" + itemId, null);

        /* Make the timestamp for the last changed */
        HashMap<String, Object> changedTimestampMap = new HashMap<>();
        changedTimestampMap.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

        /* Add the updated timestamp */
        updatedRemoveItemMap.put("/" + Constants.FIREBASE_LOCATION_ACTIVE_LISTS +
                "/" + mListId + "/" + Constants.FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED, changedTimestampMap);

        /* Do the update */
        firebaseRef.updateChildren(updatedRemoveItemMap);

    }
}
