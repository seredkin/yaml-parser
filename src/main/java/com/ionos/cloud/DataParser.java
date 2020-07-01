package com.ionos.cloud;

public interface DataParser<I, K> {
  K readData(I stream, String... filters);
}
