package com.example.medihelp;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MedicineActivity extends AppCompatActivity {
    private ListView listView;
    private String[] medicineList = {
            "Folic acid", "Kenalog", "Ferrous sulfate", "Ferrous gluconate", "Integra", "Integra Plus", "Fusion Plus", "Leucovorin", "Integra F", "Ploy-iron forte", "Ferrex 150",
            "Restora RX", "Mutigen", "Ferralet 90", "Ferocen"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        listView = findViewById(R.id.medicineListView);
        MedicineListAdapter adapter = new MedicineListAdapter(this, medicineList);
        listView.setAdapter(adapter);
    }
}

