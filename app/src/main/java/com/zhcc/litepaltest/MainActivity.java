package com.zhcc.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCreateDatabase = (Button) findViewById(R.id.create_database);
        btnCreateDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });

        Button btnAddData = (Button) findViewById(R.id.add_data);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                book.setName("haha");
                book.setAuthor("tom");
                book.setPages(424);
                book.setPrice(23.22);
                book.setPress("unknow");
                book.save();
            }
        });

        Button btnUpdate = (Button) findViewById(R.id.update_data);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                book.setPrice(99);
                book.setPress("Anchor");
                book.updateAll("name=? and author=?", "haha", "tom");
            }
        });

        Button btnDelete = (Button) findViewById(R.id.delete_data);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class, "pages>?", "400");
            }
        });

        Button btnQuery = (Button) findViewById(R.id.query_data);
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> bookList = LitePal.findAll(Book.class);
                for (Book book : bookList) {
                    Log.d("mainActivity",book.getName()+" "+book.getAuthor()+" "+book.getPages()+" "+book.getPrice()+" "+book.getPress());

                }
            }
        });
    }
}
