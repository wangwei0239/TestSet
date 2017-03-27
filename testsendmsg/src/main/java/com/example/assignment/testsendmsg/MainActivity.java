package com.example.assignment.testsendmsg;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendMail();
//                sendMsg();
//        findEmail();
        findNumber();
            }
        });
    }

    public void sendMail(){
        Intent data=new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:sunhappy951472006@163.com"));
        data.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
        data.putExtra(Intent.EXTRA_TEXT, "这是内容");
        this.startActivity(data);
    }

    public void sendMsg(){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:王为,18516579067,"));
        intent.putExtra("sms_body", "你好");
        this.startActivity(intent);
    }

    public void findNumber(){
        Cursor emails = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null, null);
        int emailIndex = 0;
        int nameIndex = 0;
        if(emails.getCount() > 0) {
            emailIndex = emails.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
            nameIndex = emails.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        }
        String resultContent = "";
        while(emails.moveToNext()) {
            String email = emails.getString(emailIndex);
            String name = emails.getString(nameIndex);
            resultContent += name + ":" + email+"\n";
        }
        ((TextView)findViewById(R.id.tv)).setText(resultContent);

    }

    public void findEmail(){
        Cursor emails = this.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,null,null, null);
        int emailIndex = 0;
        int nameIndex = 0;
        if(emails.getCount() > 0) {
            emailIndex = emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);
            nameIndex = emails.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        }
        String resultContent = "";
        while(emails.moveToNext()) {
            String email = emails.getString(emailIndex);
            String name = emails.getString(nameIndex);
            resultContent += name + ":" + email+"\n";
        }
        ((TextView)findViewById(R.id.tv)).setText(resultContent);

    }
}

