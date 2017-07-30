package com.example.suneet.onroadwheels;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by suneet on 30/7/17.
 */

public class SearchViewRecyclerViewAdapter extends RecyclerView.Adapter<SearchViewRecyclerViewAdapter.ViewHolder> {
    ArrayList<EmergencyContact> emergencyContacts;
    Context c;
    User u;
    public SearchViewRecyclerViewAdapter(ArrayList<EmergencyContact> emergencyContacts,Context context,User user)
    {
        this.emergencyContacts=emergencyContacts;
        c=context;
        u=user;
    }
    @Override
    public SearchViewRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(c).inflate(R.layout.search_screen_item, parent, false));

    }

    @Override
    public void onBindViewHolder(SearchViewRecyclerViewAdapter.ViewHolder holder, int position) {
        final EmergencyContact emergencyContact = emergencyContacts.get(position);
        holder.mobileNO.setText(emergencyContact.getMobileNo());
        holder.relation.setText(emergencyContact.getRelation());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + emergencyContact.getMobileNo()));
                if (ActivityCompat.checkSelfPermission(c, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                c.startActivity(i);
            }
        });
        holder.msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i=new Intent(Intent.ACTION_SENDTO,Uri.parse("smsTo:"+emergencyContact.getMobileNo()));
                i.putExtra("sms_body","Reach Me Fast!Your Family Member is in Emergency -OnRoadWheels");
                c.startActivity(i);*/
                Uri smsUri = Uri.parse("tel:"+emergencyContact.getMobileNo());
                Intent intent = new Intent(Intent.ACTION_VIEW, smsUri);
                intent.putExtra("address",emergencyContact.getMobileNo());
                intent.putExtra("sms_body", "Your Family Member in Emergency! Reach Me Out Fast -Powered by ONWHEELS");
                intent.setType("vnd.android-dir/mms-sms");
                c.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return emergencyContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mobileNO;
        TextView relation;
        ImageView call;
        ImageView msg;
        public ViewHolder(View itemView) {
            super(itemView);
            mobileNO= (TextView) itemView.findViewById(R.id.searchItem);
            relation= (TextView) itemView.findViewById(R.id.searchRelation);
            call= (ImageView) itemView.findViewById(R.id.callButton);
            msg= (ImageView) itemView.findViewById(R.id.messageButton);

        }
    }
}
