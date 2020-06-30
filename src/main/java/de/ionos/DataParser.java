package de.ionos;

public interface DataParser<I, K> {
  K readData(I stream, String... filters);
}
