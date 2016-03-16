package demo.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wilddog.client.DataSnapshot;
import com.wilddog.client.ValueEventListener;
import com.wilddog.client.Wilddog;
import com.wilddog.client.WilddogError;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private LinearLayout ll;
    private Button button;
    private EditText edit;
    private DanMuLayout danmu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //text = (TextView) findViewById(R.id.text);
        ll = (LinearLayout) findViewById(R.id.ll);
        final Wilddog ref = new Wilddog("https://test-caizi.wilddogio.com/");
        edit = (EditText) findViewById(R.id.edit);
        button = (Button) findViewById(R.id.button);
        danmu = (DanMuLayout) findViewById(R.id.danmu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.setValue(edit.getText().toString());
            }
        });

        ref.addValueEventListener(new ValueEventListener(){
            public void onDataChange(DataSnapshot snapshot){
                System.out.println(snapshot.getValue()); //打印结果 "hello world!!!"
                //text.setText(snapshot.getValue().toString());
                danmu.addText(snapshot.getValue().toString(),MainActivity.this);
            }

            public void onCancelled(WilddogError error){
                if(error != null){
                    System.out.println(error.getCode());
                }
            }

        });
    }
}
