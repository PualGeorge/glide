package com.bumptech.glide.load.model;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Logs;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.signature.ObjectKey;
import com.bumptech.glide.util.ByteBufferUtil;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Loads {@link java.nio.ByteBuffer}s using NIO for {@link java.io.File}.
 */
public class ByteBufferFileLoader implements ModelLoader<File, ByteBuffer> {

  @Override
  public LoadData<ByteBuffer> buildLoadData(File file, int width, int height,
      Options options) {
    return new LoadData<>(new ObjectKey(file), new ByteBufferFetcher(file));
  }

  @Override
  public boolean handles(File file) {
    return true;
  }

  /**
   * Factory for {@link com.bumptech.glide.load.model.ByteBufferFileLoader}.
   */
  public static class Factory implements ModelLoaderFactory<File, ByteBuffer> {

    @Override
    public ModelLoader<File, ByteBuffer> build(Context context,
        MultiModelLoaderFactory multiFactory) {
      return new ByteBufferFileLoader();
    }

    @Override
    public void teardown() {
      // Do nothing.
    }
  }

  private static class ByteBufferFetcher implements DataFetcher<ByteBuffer> {

    private final File file;

    public ByteBufferFetcher(File file) {
      this.file = file;
    }

    @Override
    public void loadData(Priority priority, DataCallback<? super ByteBuffer> callback) {
      ByteBuffer result = null;
      try {
        result = ByteBufferUtil.fromFile(file);
      } catch (IOException e) {
        if (Logs.isEnabled(Log.DEBUG)) {
          Logs.log(Log.DEBUG, "Failed to obtain ByteBuffer for file", e);
        }
      }
      callback.onDataReady(result);
    }

    @Override
    public void cleanup() {
      // Do nothing.
    }

    @Override
    public void cancel() {
      // Do nothing.
    }

    @Override
    public Class<ByteBuffer> getDataClass() {
      return ByteBuffer.class;
    }

    @Override
    public DataSource getDataSource() {
      return DataSource.LOCAL;
    }
  }
}