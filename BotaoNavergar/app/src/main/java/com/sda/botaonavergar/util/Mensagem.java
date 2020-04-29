package com.sda.botaonavergar.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;
import com.sda.botaonavergar.R;


public class Mensagem {

        
        private ProgressDialog mProgressDialog;
        private AlertDialog alerta;
        private Context ctx;
        private int retorno = 0;


        public void showProgressDialog(Context ctx) {
            this.ctx = ctx;

            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(ctx);
                mProgressDialog.setCancelable(false);
                mProgressDialog.setMessage("Loading...");
            }

            mProgressDialog.show();
        }

        public void mensagemSnackbar(View v, String txt) {
            Snackbar.make(v, txt, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }

        public void hideProgressDialog() {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }

        public void mensagenCurta(Context ctx, String texto) {
            Toast.makeText(ctx, texto, Toast.LENGTH_SHORT).show();
        }

        public void mensagenLonga(Context ctx, String texto) {
            Toast.makeText(ctx, texto, Toast.LENGTH_LONG).show();
        }

        public AlertDialog criaAlertDialog(Context ctx, DialogInterface dialog, int item) {
            final CharSequence[] items = {"NOVO", "EDITAR", "APAGAR"};
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle(R.string.opcoes);
            builder.setItems(items, (DialogInterface.OnClickListener) ctx);
            return builder.create();
        }

        public AlertDialog criaDialogConfirmacao(DialogInterface.OnClickListener listener, Context ctx, String msg) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setMessage(msg);
            builder.setPositiveButton(R.string.sim, listener);
            builder.setNegativeButton(R.string.nao, listener);
            return builder.create();
        }

        public int msgConfirmacao(String titulo, final String msg, Context ctx) {
            retorno = 0;
            AlertDialog.Builder confirma = new AlertDialog.Builder(ctx);
            confirma.setTitle(titulo);
            confirma.setMessage(msg);
            confirma.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    retorno = i;
                }
            }).show();

            confirma.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    retorno = i;
                }
            });
            return retorno;
        }

        public void mensagemSanckbar(View v, String msg) {
            Snackbar.make(v, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();

        }






}
