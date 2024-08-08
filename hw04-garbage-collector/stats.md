# Benchmark Results

| Heap Size | State             | Time (ms) | Time (s) |
|-----------|-------------------|-----------|----------|
| 128m      | Original          | 13791     | 13       |
| 256m      | Original          | 13355     | 13       |
| 512m      | Original          | 12985     | 12       |
| 1024m     | Original          | 13352     | 13       |
| 2048m     | Original          | 14453     | 14       |
| 4096m     | Original          | 37604     | 37       |
| 8192m     | Original          | 36960     | 36       |
| 128m      | 1. Integer to int | 4349      | 4        |
| 256m      | 1. Integer to int | 3817      | 3        |
| 512m      | 1. Integer to int | 4285      | 4        |
| 2048m     | 1. Integer to int | 4452      | 4        |
| 8192m     | 1. Integer to int | 7747      | 7        |

## Changes

### 1. Integer to int

Replace Integer with int