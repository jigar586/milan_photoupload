package com.example.rp_singh007.jsontutorial;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView lv;
    ArrayList<Actors> actorsList;
//    SharedPreferences sharedpreferences;
//    public static final String MyPREFERENCES = "myActors";
//    public static final String Name = "nameKey";
//    public static final String Height = "heightKey";
//    public static final String Children = "childrenKey";
//    public static final String Country = "countryKey";
//    public static final String Description = "descriptionKey";
//    public static final String Spouse = "spouseKey";
//


    ActorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageLoader.getInstance().init(UILConfig());



        actorsList = new ArrayList<Actors>();
        new JSONAsyncTask().execute("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors");

        ListView listview = (ListView) findViewById(R.id.list);

        adapter = new ActorAdapter(getApplicationContext(), R.layout.row, actorsList);

        //sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position,
                                    long id) {


                ImageView imageView;
                imageView = (ImageView)findViewById(R.id.ivImage);
                String imageUrl1 = "http://microblogging.wingnity.com/JSONParsingTutorial/brad.jpg";
                ImageLoader.getInstance().displayImage(imageUrl1, imageView);

                ImageView imageView2;
                imageView2 = (ImageView)findViewById(R.id.ivImage);
                String imageUrl2 = "http://microblogging.wingnity.com/JSONParsingTutorial/cruise.jpg";
                ImageLoader.getInstance().displayImage(imageUrl2, imageView2);

                ImageView imageView3;
                imageView3 = (ImageView)findViewById(R.id.ivImage);
                String imageUrl3 = "http://microblogging.wingnity.com/JSONParsingTutorial/johnny.jpg";
                ImageLoader.getInstance().displayImage(imageUrl3, imageView3);

                ImageView imageView4;
                imageView4 = (ImageView)findViewById(R.id.ivImage);
                String imageUrl4 = "http://microblogging.wingnity.com/JSONParsingTutorial/jolie.jpg";
                ImageLoader.getInstance().displayImage(imageUrl4, imageView4);


                ImageView imageView5;
                imageView5 = (ImageView)findViewById(R.id.ivImage);
                String imageUrl5 = "http://microblogging.wingnity.com/JSONParsingTutorial/tom.jpg";
                ImageLoader.getInstance().displayImage(imageUrl5, imageView5);

                ImageView imageView6;
                imageView6 = (ImageView)findViewById(R.id.ivImage);
                String imageUrl6 = "http://microblogging.wingnity.com/JSONParsingTutorial/will.jpg";
                ImageLoader.getInstance().displayImage(imageUrl6, imageView6);

                TextView n = (TextView) v.findViewById(R.id.tvName);
                String name1 = n.getText().toString();



                TextView h = (TextView) v.findViewById(R.id.tvHeight);
                String hgt = h.getText().toString();


                TextView c = (TextView) v.findViewById(R.id.tvChildren);
                String child = c.getText().toString();


                TextView cn = (TextView) v.findViewById(R.id.tvCountry);
                String coun = cn.getText().toString();



                TextView d = (TextView) v.findViewById(R.id.tvDescriptionn);
                String des = d.getText().toString();

                TextView sp = (TextView) v.findViewById(R.id.tvSpouse);
                String spo = sp.getText().toString();

                File folder = getCacheDir();
                File myfile = new File(folder, "mydata.txt");
                FileOutputStream fout = null;
                try {
                    fout = new FileOutputStream(myfile);
                    fout.write(name1.getBytes());
                    fout.write(hgt.getBytes());
                    fout.write(child.getBytes());
                    fout.write(coun.getBytes());
                    fout.write(des.getBytes());
                    fout.write(spo.getBytes());

                    Toast.makeText(MainActivity.this, name1 + "was written successfully" + myfile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fout != null) {
                        try {
                            fout.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
               /* String filePath = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + File.separator + "your_image_name.png";
                Bitmap bmp = BitmapFactory.decodeFile(filePath);
                imageView.setImageBitmap(bmp);

                // when clicked on listview item (For Shared preferences)

                TextView n = (TextView) v.findViewById(R.id.tvName);
                String name1 = n.getText().toString();

                TextView h = (TextView) v.findViewById(R.id.tvHeight);
                String hgt = h.getText().toString();

                TextView c = (TextView) v.findViewById(R.id.tvChildren);
                String child = c.getText().toString();

                TextView cn = (TextView) v.findViewById(R.id.tvCountry);
                String coun = cn.getText().toString();

                TextView d = (TextView) v.findViewById(R.id.tvDescriptionn);
                String des = d.getText().toString();

                TextView sp = (TextView) v.findViewById(R.id.tvSpouse);
                String spo = sp.getText().toString();*/

                Toast.makeText(getApplicationContext(), actorsList.get(position).getName(), Toast.LENGTH_LONG).show();

                // For Shared preferences
               /* SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name, name1);
                editor.putString(Height, hgt);
                editor.putString(Children, child);
                editor.putString(Country, coun);
                editor.putString(Description, des);
                editor.putString(Spouse, spo);
                editor.commit();*/


            }


        });



    }

    public class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("actors");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);

                        Actors actor = new Actors();

                        actor.setName(object.getString("name"));
                        actor.setDescription(object.getString("description"));
                        actor.setDob(object.getString("dob"));
                        actor.setCountry(object.getString("country"));
                        actor.setHeight(object.getString("height"));
                        actor.setSpouse(object.getString("spouse"));
                        actor.setChildren(object.getString("children"));
                        actor.setImage(object.getString("image"));

                        actorsList.add(actor);
                    }
                    return true;
                }

                //------------------>>

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            adapter.notifyDataSetChanged();
            if (result == false)
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }

    }

    private ImageLoaderConfiguration UILConfig(){
        //        /** To make the image fill the width and keep height ratio.**/
        //        <ImageView
        //        android:layout_width="fill_parent" //fill_width #1
        //        android:layout_height="wrap_content" //fill_width #2
        //        android:id="@+id/ivImage"
        //        android:src="@android:drawable/gallery_thumb"
        //        android:scaleType="fitCenter"   //fill_width #3
        //        android:adjustViewBounds="true" //fill_width #4
        //                />
        final DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)  //cache #1
                .cacheOnDisk(true) //cache #2
                .showImageOnLoading(android.R.drawable.stat_sys_download)
                .showImageForEmptyUri(android.R.drawable.ic_dialog_alert)
                .showImageOnFail(android.R.drawable.stat_notify_error)
                .considerExifParams(true) //cache #3
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED) //fill_width #5
                .build();

        ////cache #4
        //add <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/> to manifest
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        //end of cache 4
        return config;
    }

}
