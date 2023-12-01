package com.example.medihelp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MedicineListAdapter extends ArrayAdapter<String> {
    private Context context;

    public MedicineListAdapter(Context context, String[] medicines) {
        super(context, 0, medicines);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_cardview, parent, false);
        }

        String medicineName = getItem(position);

        TextView medicineNameTextView = convertView.findViewById(R.id.medicineNameTextView);
        TextView medicineDescriptionTextView = convertView.findViewById(R.id.medicineDescriptionTextView);

        medicineNameTextView.setText(medicineName);
        medicineDescriptionTextView.setText("Description for " + medicineName);

        return convertView;
    }
}


