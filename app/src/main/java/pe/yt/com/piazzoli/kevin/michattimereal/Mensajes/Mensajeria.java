package pe.yt.com.piazzoli.kevin.michattimereal.Mensajes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.yt.com.piazzoli.kevin.michattimereal.R;

/**
 * Created by Yomaira on 03/01/2017.
 */

public class Mensajeria extends AppCompatActivity {


    private RecyclerView rv;
    private Button bTEnviarMensaje;
    private EditText eTEscribirMensaje;
    private List<MensajeDeTexto> mensajeDeTextos;
    private MensajeriaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajeria);
        mensajeDeTextos = new ArrayList<>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        bTEnviarMensaje = (Button) findViewById(R.id.bTenviarMensaje);
        eTEscribirMensaje = (EditText) findViewById(R.id.eTEsribirMensaje);

        rv = (RecyclerView) findViewById(R.id.rvMensajes);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setStackFromEnd(true);
        rv.setLayoutManager(lm);

        for (int i = 0; i < 10; i++) {
            MensajeDeTexto mensajeDeTextoAuxiliar = new MensajeDeTexto();
            mensajeDeTextoAuxiliar.setId("" + i);
            mensajeDeTextoAuxiliar.setMensaje("El emisor numero" + i + "Te ha enviado un mensaje");
            mensajeDeTextoAuxiliar.setTipoMensaje(1);
            mensajeDeTextoAuxiliar.setHoraDelMensaje("10:2" + i);
            mensajeDeTextos.add(mensajeDeTextoAuxiliar);
        }

        for (int i = 0; i < 10; i++) {
            MensajeDeTexto mensajeDeTextoAuxiliar = new MensajeDeTexto();
            mensajeDeTextoAuxiliar.setId("" + i);
            mensajeDeTextoAuxiliar.setMensaje("El receptor numero "+i+"Te ha enviado un mensaje");
            mensajeDeTextoAuxiliar.setTipoMensaje(2);
            mensajeDeTextoAuxiliar.setHoraDelMensaje("10:2" + i);
            mensajeDeTextos.add(mensajeDeTextoAuxiliar);
        }

        adapter = new MensajeriaAdapter(mensajeDeTextos,this);
        rv.setAdapter(adapter);

        bTEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateMensaje(eTEscribirMensaje.getText().toString());
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setScrollbarChat();

    }

    public void CreateMensaje(String mensaje){
        MensajeDeTexto mensajeDeTextoAuxiliar = new MensajeDeTexto();
        mensajeDeTextoAuxiliar.setId("0");
        mensajeDeTextoAuxiliar.setMensaje(mensaje);
        mensajeDeTextoAuxiliar.setTipoMensaje(1);
        mensajeDeTextoAuxiliar.setHoraDelMensaje("10:20");
        mensajeDeTextos.add(mensajeDeTextoAuxiliar);
        adapter.notifyDataSetChanged();
        eTEscribirMensaje.setText("");
        setScrollbarChat();
    }

    @Override
    protected void onPause() {
        super.onPause();
        setScrollbarChat();
    }

    public void setScrollbarChat(){
        rv.scrollToPosition(adapter.getItemCount()-1);
    }

}
