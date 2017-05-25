package com.example.y.progmobgo_yoshi_dharman;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.plus.PlusShare;

import java.io.InputStream;
import java.util.Random;

/**
 * Created by Y on 5/20/2017.
 */

public class Menu extends AppCompatActivity {

    Button btnRand;
    Button btnVisit;
    TextView txtHasil;
    ImageView img;
    private Context mContext;


    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        btnRand = (Button)findViewById(R.id.btnRand);
        btnVisit = (Button)findViewById(R.id.btnVisit);
        txtHasil = (TextView)findViewById(R.id.txtHasil);
        img = (ImageView)findViewById(R.id.img);
        img.setImageURI(null);

        txtHasil.setText("Download Google+ terlebih dahulu agar tidak error!!!");

        ActivityCompat.requestPermissions(Menu.this,
                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

        btnVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PlusShare.Builder builder = new PlusShare.Builder(Menu.this);
                builder.setText("Ayo Ke UKDW");
                builder.setType("text/plain");

                builder.addCallToAction(
                        "VISIT",
                        Uri.parse("http://www.ukdw.ac.id"),
                        "/pages/create");
                builder.setContentUrl(Uri.parse("http://www.ukdw.ac.id"));
                builder.setContentDeepLinkId("/pages/",
                        null, null, null);

                startActivityForResult(builder.getIntent(), 0);


            }
        });

        btnRand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int random = r.nextInt(101);

                txtHasil.setText(String.valueOf(random));
                String hasil = "";

                Uri imageRand = null;

                Bitmap bmhappy = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.happy);
                Bitmap bmnormal = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.normal);
                Bitmap bmsad = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.sad);

                if(random >= 0 && random <= 33){
                    hasil = "Sepertinya hari ini saya sedang sial";
                    imageRand  = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bmsad, null, null));
                    Uri imgUri=Uri.parse("android.resource://"+getApplicationContext().getPackageName()+"/"+R.drawable.sad);
                    img.setImageURI(null);
                    img.setImageURI(imgUri);
                } else if(random >= 34 && random <= 66){
                    hasil = "Hari ini nampaknya biasa saja";
                    imageRand  = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bmnormal, null, null));
                    Uri imgUri=Uri.parse("android.resource://"+getApplicationContext().getPackageName()+"/"+R.drawable.normal);
                    img.setImageURI(null);
                    img.setImageURI(imgUri);
                } else{
                    hasil = "Saya merasa beruntung hari ini";
                    imageRand  = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bmhappy, null, null)); //("android.resource://"+getApplicationContext().getPackageName()+"/"+R.drawable.happy);
                            //"android.resource://"+getApplicationContext().getPackageName()+"/"+R.drawable.happy);
                    //Uri imgUri=Uri.parse(MediaStore.Images.Media.insertImage();
                    Uri imgUri = Uri.parse("android.resource://"+getApplicationContext().getPackageName()+"/"+R.drawable.happy);
                    img.setImageURI(null);
                    img.setImageURI(imgUri);
                }

//                PlusShare.Builder builder = new PlusShare.Builder(Menu.this);
//                builder.setText(hasil);
//                builder.setType("text/plain");
//                builder.addStream(imageRand);
//
//                builder.addCallToAction(
//                        "VISIT",
//                        Uri.parse("http://www.ukdw.ac.id"),
//                        "/pages/create");
//                builder.setContentUrl(Uri.parse("http://www.ukdw.ac.id"));
//                builder.setContentDeepLinkId("/pages/",
//                        null, null, null);

//                startActivityForResult(builder.getIntent(), 0);




//                Intent shareIntent = ShareCompat.IntentBuilder.from(Menu.this)
//                        .setText("")
//                        .setType("image/*")
//                        .addStream(imageRand)
//                        .getIntent()
//                        .setPackage("com.example.y.progmobgo_yoshi_dharman");
//                startActivity(shareIntent);





//Versi 1-------
                Intent shareIntent = new PlusShare.Builder(Menu.this)
                        .setText(hasil)
                        .setType("image/*")
                        .addStream(imageRand)
//                        .setContentUrl(Uri.parse("http://ukdw.ac.id"))
//                        .setContentDeepLinkId("http://ukdw.ac.id")
                        .getIntent();

                startActivityForResult(shareIntent, 0);

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(Menu.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
