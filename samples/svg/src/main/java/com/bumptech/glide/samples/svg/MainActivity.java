package com.bumptech.glide.samples.svg;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class MainActivity extends Activity {
  private ImageView imageView1;
  private ImageView imageView2;
  private ImageView imageView3;
  private Button button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    imageView1 = findViewById(R.id.imageView);
    imageView2 = findViewById(R.id.imageView2);
    imageView3 = findViewById(R.id.imageView3);
    button = findViewById(R.id.button);
    button.setOnClickListener(v -> {
      Glide.with(MainActivity.this).asGif()
          .load("https://qcloudimg.tencent-cloud.cn/raw/0557d8080490016d04031acb0c4f4889.gif")
          .into(imageView1);
      Glide.with(MainActivity.this)
          .load("https://qcloudimg.tencent-cloud.cn/raw/7c2e2852e91a3d0bc2ea38880ac306ff.jpg")
          .into(imageView2);
      Glide.with(MainActivity.this)
          .load("https://qcloudimg.tencent-cloud.cn/raw/7c2e2852e91a3d0bc2ea38880ac306ff.jpg")
          .into(imageView3);
    });
  }
}
