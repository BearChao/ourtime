package org.zynick.ourtime.ourtime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.zynick.ourtime.ourtime.getTimeNow;


public class MainActivity extends ActionBarActivity {
   //private String[] string = new String[]{"first","second","third","fourth","fifth"};
    private ListView listView;
    private static final String TAG = "OURTIME";
    private EventParser parser;
    private List<event> events;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       listView = (ListView)findViewById(R.id.lv_event);//获取ListView
        try{
            InputStream is = getResources().openRawResource(R.raw.events);
            parser = new PullEventParser();
            events = parser.parse(is);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();}
        ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String, Object>>();//添加适配器
        //在数组中存放数据
        /*******************************************************************/
            for(event event:events){
                HashMap<String,Object> map = new HashMap<String,Object>();
                map.put("item_title",event.getName());
                map.put("item_text",event.getDate());
                map.put("item_image",R.drawable.ic_launcher);
                listItem.add(map);
            }
        /*******************************************************************/
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItem,R.layout.listview_layout,new String[]{
                "item_image","item_title","item_text"
        },new int[]{
                R.id.item_image,R.id.item_title,R.id.item_text
        });
        listView.setAdapter(simpleAdapter);

        button = (Button)findViewById(R.id.btn_add_event);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
