package com.sda.botaonavergar.user;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Update;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.turno.Turno;
import com.sda.botaonavergar.turno.TurnoAdapter;
import com.sda.botaonavergar.turno.TurnoDao;
import com.sda.botaonavergar.util.Utilidades;

import java.util.ArrayList;

/**
 * made by sda
 */
public class UserListaActivity extends AppCompatActivity {

    private Utilidades msg;
    private Context ctx;

    private ArrayList<User> users = new ArrayList<>();
    private RecyclerView rv;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dois_lista_activity);

        iniciaComponentes();

        rv = findViewById(R.id.myRecyclerDois);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new UserAdapter(ctx, users);



        FloatingActionButton fab = findViewById(R.id.fab_rodape_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buscar();

    }

    private void iniciaComponentes(){
        TextView tv = findViewById(R.id.titulo);
        tv.setText(getResources().getString(R.string.usu_rio));
        tv.setTextSize(40);

        msg = new Utilidades();
        ctx = this;
    }

    private void buscar(){
        UserDao dao = new UserDao(ctx);
        dao.openDB();
        users.clear();
        Cursor cs = dao.buscar();
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            String snome = cs.getString(1);
            String  scargo = cs.getString(2);
            User user = new User(id, snome, scargo);
            users.add(user);
        }
        if (!(users.size() < 1)){
            rv.setAdapter(adapter);
        }
        dao.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        buscar();
    }
}
